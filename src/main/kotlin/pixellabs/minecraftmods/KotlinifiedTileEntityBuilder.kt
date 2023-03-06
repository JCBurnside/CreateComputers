package pixellabs.minecraftmods

import com.jozufozu.flywheel.api.MaterialManager
import com.jozufozu.flywheel.backend.instancing.InstancedRenderRegistry
import com.jozufozu.flywheel.backend.instancing.blockentity.BlockEntityInstance
import com.jozufozu.flywheel.util.NonNullSupplier
import com.simibubi.create.foundation.data.CreateTileEntityBuilder
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BlockEntityBuilder
import com.tterrag.registrate.builders.BuilderCallback
import com.tterrag.registrate.util.OneTimeEventReceiver
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.common.util.NonNullFunction
import net.minecraftforge.common.util.NonNullPredicate
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.DIST
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT
import java.util.function.BiFunction
import kotlin.reflect.KFunction
import com.tterrag.registrate.util.nullness.NonNullFunction as NonNullFunction1
import com.tterrag.registrate.util.nullness.NonNullSupplier as NonNullSupplier1

class KotlinifiedTileEntityBuilder<T:BlockEntity,P>
(
    owner: AbstractRegistrate<*>?,
    parent: P, name: String?, callback: BuilderCallback?,
    val factory: BlockEntityFactory<T>,
    var renderNormally: NonNullPredicate<T>
) : CreateTileEntityBuilder<T, P>(owner, parent, name, callback, factory) {
    private var renderer : NonNullSupplier1<NonNullFunction1<BlockEntityRendererProvider.Context,BlockEntityRenderer<in T>>>? = null;
    override fun instance(
        instanceFactory: NonNullSupplier1<BiFunction<MaterialManager, T, BlockEntityInstance<in T>>>?,
        renderNormally: NonNullPredicate<T>?
    ): KotlinifiedTileEntityBuilder<T, P> {
        super.instance(instanceFactory, renderNormally)
        if(renderNormally!=null) {
            this.renderNormally = renderNormally
        }
        return this
    }

    override fun instance(instanceFactory: NonNullSupplier1<BiFunction<MaterialManager, T, BlockEntityInstance<in T>>>?) : KotlinifiedTileEntityBuilder<T,P> {
        this.instance(instanceFactory,renderNormally)
        return this
    }

    override fun registerInstance() {
        OneTimeEventReceiver.addListener(MOD_BUS,FMLClientSetupEvent::class.java) {
            val factory= factory as? NonNullSupplier<BiFunction<MaterialManager,T,BlockEntityInstance<in T>>>;
            if(factory!=null) {
                InstancedRenderRegistry.configure(entry)
                    .factory(factory.get())
                    .skipRender { !renderNormally.test(it) }
                    .apply()
            }
        }
    }
    public fun renderer(renderer: ((BlockEntityRendererProvider.Context) -> BlockEntityRenderer<in T>)): KotlinifiedTileEntityBuilder<T, P> {
        if(this.renderer==null){
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT) { Runnable(this::registerRenderer) }
        }
        this.renderer = com.tterrag.registrate.util.nullness.NonNullSupplier { NonNullFunction1(renderer) }
        return this
    }

    override fun validBlock(block: com.tterrag.registrate.util.nullness.NonNullSupplier<out Block>): KotlinifiedTileEntityBuilder<T, P> {
        super.validBlock(block)
        return this
    }
    override fun registerRenderer() {
        OneTimeEventReceiver.addListener(MOD_BUS,FMLClientSetupEvent::class.java) {
            if(this.renderer!=null) {
                var renderer = this.renderer!!.get()
                BlockEntityRenderers.register(entry,Test<T> { renderer.apply(it) })
            }
        }
    }
}
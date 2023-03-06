package pixellabs.minecraftmods

import com.simibubi.create.foundation.data.CreateRegistrate
import com.simibubi.create.foundation.data.CreateTileEntityBuilder
import com.tterrag.registrate.AbstractRegistrate
import com.tterrag.registrate.builders.BlockEntityBuilder
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraftforge.eventbus.api.IEventBus

object Registrate : CreateRegistrate(CreateComputers.ID) {

    public fun <T: BlockEntity, P> te(parent:P, name : String, factory : BlockEntityBuilder.BlockEntityFactory<T>) = this.entry(name) { cb ->
        KotlinifiedTileEntityBuilder(
            this,
            parent,
            name,
            cb,
            factory
        ) { true }
    } as KotlinifiedTileEntityBuilder<T, P>



    public fun <T: BlockEntity> te(name: String, factory: BlockEntityBuilder.BlockEntityFactory<T>) = this.te(this.self(),name,factory)
}
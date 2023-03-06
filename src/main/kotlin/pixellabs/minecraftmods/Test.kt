package pixellabs.minecraftmods

import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.world.level.block.entity.BlockEntity

class Test<T : BlockEntity>(val provider : (BlockEntityRendererProvider.Context) -> BlockEntityRenderer<in T>) : BlockEntityRendererProvider<T>  {
    override fun create(ctx: BlockEntityRendererProvider.Context): BlockEntityRenderer<T> = provider(ctx) as BlockEntityRenderer<T>
}
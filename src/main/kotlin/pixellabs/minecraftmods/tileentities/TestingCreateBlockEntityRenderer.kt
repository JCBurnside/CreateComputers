package pixellabs.minecraftmods.tileentities

import com.jozufozu.flywheel.core.PartialModel
import com.mojang.blaze3d.vertex.PoseStack
import com.simibubi.create.AllBlockPartials
import com.simibubi.create.content.contraptions.base.KineticTileEntity
import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer
import com.simibubi.create.foundation.render.CachedBufferer
import com.simibubi.create.foundation.render.SuperByteBuffer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.BlockState

public class TestingCreateBlockEntityRenderer(context: BlockEntityRendererProvider.Context) : KineticTileEntityRenderer(
    context
) {
    override fun getRotatedModel(te: KineticTileEntity?, state: BlockState?): SuperByteBuffer {
        return CachedBufferer.partial(AllBlockPartials.DRILL_HEAD,state)
    }
}
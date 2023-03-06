package pixellabs.minecraftmods.tileentities

import com.jozufozu.flywheel.api.MaterialManager
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance
import net.minecraft.world.level.block.state.BlockState

class TestingCreateBlockEntityInstance(modelManager: MaterialManager, tile: CreateBlockEntity)
    : SingleRotatingInstance(modelManager,tile)
{

    override fun getRenderedBlockState(): BlockState {
        return shaft()
    }
}
package pixellabs.minecraftmods.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Material
import pixellabs.minecraftmods.Registration

class TestingAPIBlock : Block(
    Properties.of(Material.METAL).strength(10.0F)
), EntityBlock
{
    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return Registration.TESTING_API_BLOCK_ENTITY.create(pos,state)
    }
}
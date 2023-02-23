package pixellabs.minecraftmods.block

import net.minecraft.core.BlockPos
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Material
import pixellabs.minecraftmods.Registration
import pixellabs.minecraftmods.tileentities.TestingAPITileEntity
import thedarkcolour.kotlinforforge.forge.registerObject

class TestingAPIBlock : Block(
    Properties.of(Material.METAL).strength(10.0F)
), EntityBlock
{
    companion object {
        val INSTANCE by Registration.BLOCK_REGISTRY.registerObject("testing_api_block", ::TestingAPIBlock)

        val ITEM by Registration.ITEM_REGISTRY.registerObject("testing_api_block") {
            BlockItem(INSTANCE, Item.Properties().tab(Registration.TAB))
        }
    }
    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return TestingAPITileEntity.INSTANCE.create(pos,state)
    }
}
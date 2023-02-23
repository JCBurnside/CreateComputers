package pixellabs.minecraftmods

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import pixellabs.minecraftmods.block.TestingAPIBlock

class CreateComputersTab : CreativeModeTab(CreateComputers.ID + ".TabName") {
    override fun makeIcon(): ItemStack {
        return ItemStack(TestingAPIBlock.ITEM)
    }
}

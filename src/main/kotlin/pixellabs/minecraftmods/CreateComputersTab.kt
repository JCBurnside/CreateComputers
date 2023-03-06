package pixellabs.minecraftmods

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

class CreateComputersTab : CreativeModeTab(CreateComputers.ID + ".TabName") {
    override fun makeIcon(): ItemStack {
        return Registration.TESTING_CREATE_SHAFT.asStack()
    }
}

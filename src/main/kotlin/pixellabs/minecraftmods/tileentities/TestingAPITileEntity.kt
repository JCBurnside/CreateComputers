package pixellabs.minecraftmods.tileentities

import dan200.computercraft.api.peripheral.IPeripheral
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import pixellabs.minecraftmods.Registration
import pixellabs.minecraftmods.TestingPeripheral
import dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional


class TestingAPITileEntity(private val pos : BlockPos,private var state : BlockState)
    : BlockEntity(Registration.TESTING_API_BLOCK_ENTITY,pos,state)
{

    private val peripheral = TestingPeripheral(this)
    private var peripheralCap : LazyOptional<IPeripheral>? = null
    override fun <T : Any?> getCapability(cap: Capability<T>): LazyOptional<T> {
        return if(cap == CAPABILITY_PERIPHERAL) {
            if(peripheralCap == null) {
                peripheralCap = LazyOptional.of { peripheral }
            }
            peripheralCap!!.cast()
        } else {
            super.getCapability(cap)
        }
    }
}
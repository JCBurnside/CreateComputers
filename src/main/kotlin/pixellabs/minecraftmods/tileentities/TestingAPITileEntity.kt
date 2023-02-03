package pixellabs.minecraftmods.tileentities

import dan200.computercraft.api.peripheral.IPeripheral
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import pixellabs.minecraftmods.Registration
import pixellabs.minecraftmods.TestingPeripheral
import dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL
import net.minecraft.core.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import org.apache.logging.log4j.Level
import pixellabs.minecraftmods.CreateComputers


class TestingAPITileEntity(private val pos : BlockPos,private var state : BlockState)
    : BlockEntity(Registration.TESTING_API_BLOCK_ENTITY,pos,state)
{

    private val peripheral = TestingPeripheral(this)
    private var peripheralCap : LazyOptional<IPeripheral>? = null
    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if(cap == CAPABILITY_PERIPHERAL) {
            if(peripheralCap == null) {
                peripheralCap = LazyOptional.of { peripheral }
            }
            peripheralCap!!.cast()
        } else {
            super.getCapability(cap,side)
        }
    }
}
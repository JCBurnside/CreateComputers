package pixellabs.minecraftmods.tileentities
import com.jozufozu.flywheel.api.instance.DynamicInstance
import com.simibubi.create.content.contraptions.base.KineticTileEntity
import com.simibubi.create.content.contraptions.goggles.IHaveGoggleInformation
import com.simibubi.create.content.contraptions.relays.encased.ShaftInstance
import dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import pixellabs.minecraftmods.peripherals.CreatePeripheral

public class CreateBlockEntity(typeIn: BlockEntityType<*>?, pos: BlockPos?, state: BlockState?)
    : KineticTileEntity(typeIn, pos, state), IHaveGoggleInformation {
    private val peripheral = CreatePeripheral(this)
    private val peripheralCap = LazyOptional.of { peripheral }
    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if(cap == CAPABILITY_PERIPHERAL) {
            peripheralCap.cast()
        } else {
            super.getCapability(cap, side)
        }
    }

    override fun onSpeedChanged(previousSpeed: Float) {
        super.onSpeedChanged(previousSpeed)
        peripheral.fireNewSpeed(getSpeed())
    }
}

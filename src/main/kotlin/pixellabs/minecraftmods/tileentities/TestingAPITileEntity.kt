package pixellabs.minecraftmods.tileentities

import com.google.common.collect.Sets
import dan200.computercraft.api.peripheral.IPeripheral
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import pixellabs.minecraftmods.Registration
import pixellabs.minecraftmods.TestingPeripheral
import dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL
import net.minecraft.core.Direction
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import pixellabs.minecraftmods.block.TestingAPIBlock
import thedarkcolour.kotlinforforge.forge.registerObject


class TestingAPITileEntity(private val pos : BlockPos,private var state : BlockState)
    : BlockEntity(INSTANCE,pos,state)
{
    companion object {
        val INSTANCE by Registration.TE_REGISTRY.registerObject("testing_api_block_entity") {
            BlockEntityType(::TestingAPITileEntity, Sets.newHashSet(TestingAPIBlock.INSTANCE) as Set<Block>?, null)
        }
    }

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
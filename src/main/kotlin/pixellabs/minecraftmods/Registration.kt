package pixellabs.minecraftmods

import com.google.common.collect.Sets
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import pixellabs.minecraftmods.block.TestingAPIBlock
import pixellabs.minecraftmods.tileentities.TestingAPITileEntity
import thedarkcolour.kotlinforforge.forge.registerObject
object Registration {

    val TAB = CreateComputersTab()
    val BLOCK_REGISTRY : DeferredRegister<Block> = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateComputers.ID)
    val ITEM_REGISTRY : DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, CreateComputers.ID)
    val TE_REGISTRY : DeferredRegister<BlockEntityType<*>> = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CreateComputers.ID)
// the returned ObjectHolderDelegate can be used as a property delegate
// this is automatically registered by the deferred registry at the correct times




    fun register(bus: IEventBus) {
        TE_REGISTRY.register(bus)
        BLOCK_REGISTRY.register(bus)

        ITEM_REGISTRY.register(bus)
    }
}
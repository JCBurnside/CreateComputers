package pixellabs.minecraftmods

import com.google.common.collect.Sets
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import pixellabs.minecraftmods.block.TestingAPIBlock
import pixellabs.minecraftmods.tileentities.TestingAPITileEntity
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.registerObject

object Registration {
    val TAB = CreateComputersTab()
    val BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateComputers.ID)
    val ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CreateComputers.ID)
    val TE_REGSISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CreateComputers.ID)
    // the returned ObjectHolderDelegate can be used as a property delegate
    // this is automatically registered by the deferred registry at the correct times


    val TESTING_API_BLOCK by BLOCK_REGISTRY.registerObject("testing_api_block",::TestingAPIBlock)
    val TESTING_API_BLOCK_ENTITY by TE_REGSISTRY.registerObject("testing_api_block_entity") {
        BlockEntityType(::TestingAPITileEntity, Sets.newHashSet(TESTING_API_BLOCK) as Set<Block>?,null)
    }
    val TESTINT_API_BLOCK_ITEM by ITEM_REGISTRY.registerObject("testing_api_block") {
        BlockItem(TESTING_API_BLOCK,Item.Properties().tab(TAB))
    }

    fun register(bus :IEventBus) {
        TE_REGSISTRY.register(bus)
        BLOCK_REGISTRY.register(bus)

        ITEM_REGISTRY.register(bus)
    }
}
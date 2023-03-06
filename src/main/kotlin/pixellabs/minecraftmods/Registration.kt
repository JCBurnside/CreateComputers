package pixellabs.minecraftmods

import com.jozufozu.flywheel.api.MaterialManager
import com.jozufozu.flywheel.backend.instancing.blockentity.BlockEntityInstance
import com.simibubi.create.content.AllSections
import com.simibubi.create.content.contraptions.relays.encased.ShaftInstance
import com.simibubi.create.content.contraptions.relays.encased.ShaftRenderer
import com.simibubi.create.foundation.block.BlockStressDefaults
import com.simibubi.create.foundation.data.SharedProperties
import com.tterrag.registrate.util.nullness.NonNullFunction
import com.tterrag.registrate.util.nullness.NonNullSupplier
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.material.MaterialColor
import pixellabs.minecraftmods.block.CreateTestBlock
import pixellabs.minecraftmods.tileentities.CreateBlockEntity
import pixellabs.minecraftmods.tileentities.TestingCreateBlockEntityInstance
import pixellabs.minecraftmods.tileentities.TestingCreateBlockEntityRenderer
import java.util.function.BiFunction

object Registration {


    val TAB = CreateComputersTab()

    init {
        CreateComputers.CREATE_REGISTRE.creativeModeTab { TAB }
        Registrate.creativeModeTab { TAB }
    }
// the returned ObjectHolderDelegate can be used as a property delegate
// this is automatically registered by the deferred registry at the correct times

    val TESTING_CREATE_SHAFT = Registrate
        .block<CreateTestBlock>("testing_shaft_block", CreateTestBlock::test_block)
        .initialProperties(SharedProperties::softMetal)
        .properties { p-> p.color(MaterialColor.COLOR_BLACK) }
        .transform(BlockStressDefaults.setNoImpact())
        .transform { it.tag(BlockTags.MINEABLE_WITH_PICKAXE)}
        .simpleItem()
        .register()

    val TESTING_CREATE_SHAFT_ENTITY = Registrate
        .te("testing_shaft_entity", ::CreateBlockEntity)
        .renderer(::TestingCreateBlockEntityRenderer)
        .instance(NonNullSupplier.of{ ::ShaftInstance as BiFunction<MaterialManager,CreateBlockEntity,BlockEntityInstance<in CreateBlockEntity>> })
        .validBlocks(TESTING_CREATE_SHAFT)
        .register()
    fun register() {
        CreateComputers.CREATE_REGISTRE.addToSection(TESTING_CREATE_SHAFT,AllSections.KINETICS)
    }
}


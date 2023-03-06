package pixellabs.minecraftmods.block

import com.simibubi.create.content.contraptions.base.DirectionalAxisKineticBlock
import com.simibubi.create.content.contraptions.base.IRotate
import com.simibubi.create.content.contraptions.relays.encased.AbstractEncasedShaftBlock
import com.simibubi.create.foundation.block.ITE
import net.minecraft.core.Direction
import net.minecraft.core.Direction.Axis
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import pixellabs.minecraftmods.Registration
import pixellabs.minecraftmods.tileentities.CreateBlockEntity

public class CreateTestBlock(properties: Properties)
    : AbstractEncasedShaftBlock(properties), ITE<CreateBlockEntity> {

    companion object {
        @JvmStatic
        public fun test_block(properties: Properties) : CreateTestBlock = CreateTestBlock(properties)
    }

    override fun getTileEntityClass(): Class<CreateBlockEntity> = CreateBlockEntity::class.java

    override fun getTileEntityType(): BlockEntityType<out CreateBlockEntity> = Registration.TESTING_CREATE_SHAFT_ENTITY.get()

}
package pixellabs.minecraftmods

import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.peripheral.IComputerAccess
import dan200.computercraft.api.peripheral.IPeripheral
import net.minecraft.Util
import net.minecraft.network.chat.TextComponent
import net.minecraftforge.server.ServerLifecycleHooks
import pixellabs.minecraftmods.tileentities.TestingAPITileEntity


class TestingPeripheral(private val te : TestingAPITileEntity) : IPeripheral {

    private val connectedComputers = mutableListOf<IComputerAccess>()

    override fun attach(computer: IComputerAccess) {
        connectedComputers.add(computer)
    }

    override fun detach(computer: IComputerAccess) {
        connectedComputers.remove(computer)
    }

    override fun equals(other: IPeripheral?): Boolean {
        return this == other
    }

    override fun getType(): String = "testing"

    @LuaFunction
    fun sendMessage(msg : String?) {
        ServerLifecycleHooks.getCurrentServer().playerList.players.forEach {
            it.sendMessage(TextComponent("WOW{}".format(msg)), Util.NIL_UUID)
        }
    }

    @LuaFunction(mainThread = true)
    fun isRaining() : Boolean {
        return te.level!!.getRainLevel(0.0F) > 0
    }


}
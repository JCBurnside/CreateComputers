package pixellabs.minecraftmods.peripherals

import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.peripheral.IComputerAccess
import dan200.computercraft.api.peripheral.IPeripheral
import pixellabs.minecraftmods.tileentities.CreateBlockEntity

class CreatePeripheral(val te : CreateBlockEntity) : IPeripheral {
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

    override fun getType(): String = "testing_create"

    @LuaFunction
    fun getRpm() : Float = te.speed

    fun fireNewSpeed(speed : Float) {
        connectedComputers.forEach { it.queueEvent("speed_change",-speed) }
    }
}
package pixellabs.minecraftmods

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist
import com.simibubi.create.foundation.data.CreateRegistrate
import thedarkcolour.kotlinforforge.forge.FORGE_BUS

@Mod(CreateComputers.ID)
object CreateComputers {

    const val ID = "createcomputers"
    val CREATE_REGISTRE = CreateRegistrate.create(ID)

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(ID)
    init {
        LOGGER.log(Level.INFO, "Hello world!")

        // Register the KDeferredRegister to the mod-specific event bus
        Registrate.registerEventListeners(MOD_BUS)
        CREATE_REGISTRE.registerEventListeners(MOD_BUS)
        Registration.register()//legit only here to force the object to load.
        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })

        println(obj)
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }
}
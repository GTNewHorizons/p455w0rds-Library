package p455w0rdslib.util;

import net.minecraft.server.MinecraftServer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class MCUtils {

    public static boolean isSSP() {
        return MinecraftServer.getServer().isSinglePlayer();
    }

    public static boolean isSMP() {
        return !isSSP();
    }

    public static boolean isClient() {
        return FMLCommonHandler.instance().getSide() == Side.CLIENT;
    }

    public static boolean isServer() {
        return !isClient();
    }
}

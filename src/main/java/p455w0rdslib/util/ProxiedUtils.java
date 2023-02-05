package p455w0rdslib.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import p455w0rdslib.P455w0rdsLib;

public class ProxiedUtils {
    public static World getWorld() {
        return P455w0rdsLib.PROXY.getWorld();
    }

    public static EntityPlayer getPlayer() {
        return P455w0rdsLib.PROXY.getPlayer();
    }

    public static MinecraftServer getServer() {
        return (MinecraftServer)P455w0rdsLib.PROXY.getServer();
    }
}

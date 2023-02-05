package p455w0rdslib.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {

    public World getWorld() {
        return null;
    }

    public EntityPlayer getPlayer() {
        return null;
    }

    public Object getServer() {
        return FMLCommonHandler.instance().getMinecraftServerInstance();
    }
}

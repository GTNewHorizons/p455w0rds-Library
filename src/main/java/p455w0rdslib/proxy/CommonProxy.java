package p455w0rdslib.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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

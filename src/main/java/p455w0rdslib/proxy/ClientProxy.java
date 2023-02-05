package p455w0rdslib.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
    public World getWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    public EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public Object getServer() {
        return super.getServer();
    }
}

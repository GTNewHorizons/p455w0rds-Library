package p455w0rdslib.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

    @Override
    public World getWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }
}

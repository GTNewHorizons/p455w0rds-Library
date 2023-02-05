package p455w0rdslib.util;

import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;

public class PlayerTextureUtils {
    public static void setCape(AbstractClientPlayer player, ResourceLocation texture) {
        player.func_152121_a(Type.CAPE, texture);
    }

    public static void setSkin(AbstractClientPlayer player, ResourceLocation texture) {
        player.func_152121_a(Type.SKIN, texture);
    }

    public static boolean hasCape(AbstractClientPlayer player) {
        return player.func_152122_n();
    }
}

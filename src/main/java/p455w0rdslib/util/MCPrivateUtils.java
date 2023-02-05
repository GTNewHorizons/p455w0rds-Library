package p455w0rdslib.util;

import java.lang.invoke.MethodHandle;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;

public class MCPrivateUtils {
    private static final MethodHandle GET_DEF_PACKS = ReflectionUtils.findFieldGetter(Minecraft.class, "defaultResourcePacks", "field_110449_ao");

    public static void addResourcePack(IResourcePack pack) {
        try {
            List<Object> packList = GET_DEF_PACKS.invoke(Minecraft.getMinecraft());
            packList.add(pack);
        } catch (Throwable var3) {
            var3.printStackTrace();
        }

    }
}

package p455w0rdslib.util;

import net.minecraft.entity.item.EntityItem;

public class EntityItemUtils {

    public static boolean canPickup(EntityItem item) {
        return item.delayBeforeCanPickup <= 0;
    }

    public static String getThrowerName(EntityItem item) {
        return item.func_145800_j();
    }

    public static String getOwnerName(EntityItem item) {
        return item.func_145798_i();
    }

    public static void setThrowerName(EntityItem item, String thrower) {
        item.func_145799_b(thrower);
    }

    public static void setOwnerName(EntityItem item, String owner) {
        item.func_145797_a(owner);
    }
}

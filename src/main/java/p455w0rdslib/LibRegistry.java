package p455w0rdslib;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LibRegistry {

    private static Map<UUID, String> NAME_REGISTRY = new HashMap<>();
    private static Map<String, UUID> UUID_REGISTRY = new HashMap<>();

    public static Map<UUID, String> getNameRegistry() {
        return NAME_REGISTRY;
    }

    public static Map<String, UUID> getUUIDRegistry() {
        return UUID_REGISTRY;
    }

    public static String getPlayerName(UUID uuid) {
        return NAME_REGISTRY.get(uuid);
    }

    public static boolean registerName(UUID uuid, String name) {
        boolean hasChanged = false;
        if (!NAME_REGISTRY.containsKey(uuid)) {
            NAME_REGISTRY.put(uuid, name);
            hasChanged = true;
        }

        if (!UUID_REGISTRY.containsKey(name)) {
            UUID_REGISTRY.put(name, uuid);
        }

        return hasChanged;
    }

    public static void registerUUID(String name, UUID uuid) {
        registerName(uuid, name);
    }

    public static void clearNameRegistry() {
        NAME_REGISTRY = new HashMap<>();
        UUID_REGISTRY = new HashMap<>();
    }
}

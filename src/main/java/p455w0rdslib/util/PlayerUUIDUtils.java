package p455w0rdslib.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.apache.commons.io.IOUtils;

import p455w0rdslib.LibGlobals;
import p455w0rdslib.LibRegistry;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import com.google.common.io.Resources;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class PlayerUUIDUtils {

    public static String getPlayerName(final UUID uuid) throws InterruptedException, ExecutionException {
        return (String) LibGlobals.THREAD_POOL.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                return PlayerUUIDUtils.fetchPlayerName(uuid);
            }
        }).get();
    }

    public static UUID getPlayerUUID(final String name) throws InterruptedException, ExecutionException {
        return (UUID) LibGlobals.THREAD_POOL.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                return PlayerUUIDUtils.fetchPlayerUUID(name);
            }
        }).get();
    }

    public static EntityPlayer getPlayerFromWorld(World world, UUID player) {
        return player != null && world != null ? world.func_152378_a(player) : null;
    }

    private static String fetchPlayerName(UUID uuid) throws IOException {
        if (LibRegistry.getNameRegistry().containsKey(uuid)) {
            return LibRegistry.getNameRegistry().get(uuid);
        }
        String USERNAME_API_URL;
        if (!MCUtils.isSMP() && MCUtils.isClient()
                && ProxiedUtils.getWorld() != null
                && ProxiedUtils.getPlayer() != null) {
            USERNAME_API_URL = ProxiedUtils.getPlayer().getCommandSenderName();
            LibRegistry.registerName(uuid, USERNAME_API_URL);
            return USERNAME_API_URL;
        }
        USERNAME_API_URL = "https://api.mojang.com/user/profiles/%s/names";
        CharMatcher DASH_MATCHER = CharMatcher.is('-');
        String uuidString = DASH_MATCHER.removeFrom(uuid.toString());
        BufferedReader reader = Resources
                .asCharSource(new URL(String.format(USERNAME_API_URL, uuidString)), StandardCharsets.UTF_8)
                .openBufferedStream();

        String nameObj;
        try {
            JsonReader json = new JsonReader(reader);
            json.beginArray();
            String name = null;
            long when = 0L;

            while (json.hasNext()) {
                nameObj = null;
                long timeObj = 0L;
                json.beginObject();

                while (json.hasNext()) {
                    String key = json.nextName();
                    switch (key) {
                        case "changedToAt":
                            timeObj = json.nextLong();
                            break;
                        case "name":
                            nameObj = json.nextString();
                            break;
                        default:
                            json.skipValue();
                            break;
                    }
                }

                json.endObject();
                if (nameObj != null && timeObj >= when) {
                    name = nameObj;
                }
            }

            json.endArray();
            json.close();
            name = name == null ? "" : name;
            LibRegistry.registerName(uuid, name);
            nameObj = name;
        } finally {
            IOUtils.closeQuietly(reader);
        }

        return nameObj;

    }

    private static UUID fetchPlayerUUID(String name) {
        if (LibRegistry.getUUIDRegistry().containsKey(name)) {
            return LibRegistry.getUUIDRegistry().get(name);
        } else if (!MCUtils.isSMP() && MCUtils.isClient()
                && ProxiedUtils.getWorld() != null
                && ProxiedUtils.getPlayer() != null) {
                    UUID uuid = ProxiedUtils.getPlayer().getUniqueID();
                    LibRegistry.registerUUID(name, uuid);
                    return uuid;
                } else {
                    if (!Strings.isNullOrEmpty(name)) {
                        try {
                            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setRequestProperty("Content-Type", "application/json");
                            connection.setUseCaches(false);
                            connection.setDoInput(true);
                            connection.setDoOutput(true);
                            JsonObject profile = (JsonObject) (new JsonParser())
                                    .parse(new InputStreamReader(connection.getInputStream()));
                            UUID uuid = UUID.fromString(fullUUID(profile.get("id").toString()));
                            LibRegistry.registerUUID(name, uuid);
                            return uuid;
                        } catch (Exception e) {}
                    }

                    return null;
                }
    }

    private static String fullUUID(String uuid) {
        uuid = cleanUUID(uuid);
        uuid = uuid.substring(0, 8) + "-"
                + uuid.substring(8, 12)
                + "-"
                + uuid.substring(12, 16)
                + "-"
                + uuid.substring(16, 20)
                + "-"
                + uuid.substring(20, 32);
        return uuid;
    }

    private static String cleanUUID(String uuid) {
        return uuid.replaceAll("[^a-zA-Z0-9]", "");
    }
}

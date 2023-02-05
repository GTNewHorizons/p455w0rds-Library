package p455w0rdslib.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import com.google.common.io.Resources;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import p455w0rdslib.LibGlobals;
import p455w0rdslib.LibRegistry;

public class PlayerUUIDUtils {
    public static String getPlayerName(final UUID uuid) throws InterruptedException, ExecutionException {
        return (String)LibGlobals.THREAD_POOL.submit(new Callable<Object>() {
            public Object call() throws Exception {
                return PlayerUUIDUtils.fetchPlayerName(uuid);
            }
        }).get();
    }

    public static UUID getPlayerUUID(final String name) throws InterruptedException, ExecutionException {
        return (UUID)LibGlobals.THREAD_POOL.submit(new Callable<Object>() {
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
            return (String)LibRegistry.getNameRegistry().get(uuid);
        } else {
            String USERNAME_API_URL;
            if (!MCUtils.isSMP() && MCUtils.isClient() && ProxiedUtils.getWorld() != null && ProxiedUtils.getPlayer() != null) {
                USERNAME_API_URL = ProxiedUtils.getPlayer().getCommandSenderName();
                LibRegistry.registerName(uuid, USERNAME_API_URL);
                return USERNAME_API_URL;
            } else {
                USERNAME_API_URL = "https://api.mojang.com/user/profiles/%s/names";
                CharMatcher DASH_MATCHER = CharMatcher.is('-');
                String uuidString = DASH_MATCHER.removeFrom(uuid.toString());
                BufferedReader reader = Resources.asCharSource(new URL(String.format(USERNAME_API_URL, uuidString)), StandardCharsets.UTF_8).openBufferedStream();
                Throwable var5 = null;

                String nameObj;
                try {
                    JsonReader json = new JsonReader(reader);
                    json.beginArray();
                    String name = null;
                    long when = 0L;

                    while(json.hasNext()) {
                        nameObj = null;
                        long timeObj = 0L;
                        json.beginObject();

                        while(json.hasNext()) {
                            String key = json.nextName();
                            byte var15 = -1;
                            switch(key.hashCode()) {
                            case -1650802462:
                                if (key.equals("changedToAt")) {
                                    var15 = 1;
                                }
                                break;
                            case 3373707:
                                if (key.equals("name")) {
                                    var15 = 0;
                                }
                            }

                            switch(var15) {
                            case 0:
                                nameObj = json.nextString();
                                break;
                            case 1:
                                timeObj = json.nextLong();
                                break;
                            default:
                                json.skipValue();
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
                } catch (Throwable var23) {
                    var5 = var23;
                    throw var23;
                } finally {
                    if (reader != null) {
                        if (var5 != null) {
                            try {
                                reader.close();
                            } catch (Throwable var22) {
                                var5.addSuppressed(var22);
                            }
                        } else {
                            reader.close();
                        }
                    }

                }

                return nameObj;
            }
        }
    }

    private static UUID fetchPlayerUUID(String name) {
        if (LibRegistry.getUUIDRegistry().containsKey(name)) {
            return (UUID)LibRegistry.getUUIDRegistry().get(name);
        } else if (!MCUtils.isSMP() && MCUtils.isClient() && ProxiedUtils.getWorld() != null && ProxiedUtils.getPlayer() != null) {
            UUID uuid = ProxiedUtils.getPlayer().getUniqueID();
            LibRegistry.registerUUID(name, uuid);
            return uuid;
        } else {
            if (!Strings.isNullOrEmpty(name)) {
                try {
                    URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setUseCaches(false);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    JsonObject profile = (JsonObject)(new JsonParser()).parse(new InputStreamReader(connection.getInputStream()));
                    UUID uuid = UUID.fromString(fullUUID(profile.get("id").toString()));
                    LibRegistry.registerUUID(name, uuid);
                    return uuid;
                } catch (Exception var5) {
                }
            }

            return null;
        }
    }

    private static String fullUUID(String uuid) {
        uuid = cleanUUID(uuid);
        uuid = uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
        return uuid;
    }

    private static String cleanUUID(String uuid) {
        return uuid.replaceAll("[^a-zA-Z0-9]", "");
    }
}

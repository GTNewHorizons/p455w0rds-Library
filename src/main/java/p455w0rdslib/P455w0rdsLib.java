package p455w0rdslib;

import p455w0rdslib.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;

@Mod(
        modid = LibGlobals.MODID,
        name = LibGlobals.NAME,
        version = LibGlobals.VERSION,
        acceptedMinecraftVersions = "[1.7.10]")
public class P455w0rdsLib {

    @SidedProxy(clientSide = LibGlobals.CLIENT_PROXY, serverSide = LibGlobals.SERVER_PROXY)
    public static CommonProxy PROXY;
    @Instance(LibGlobals.MODID)
    public static P455w0rdsLib INSTANCE;
}

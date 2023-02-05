package p455w0rdslib;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LibGlobals {

    public static final String MODID = "p455w0rdslib";
    public static final String VERSION = Tags.VERSION;
    public static final String NAME = "p455w0rd's Library";
    public static final String SERVER_PROXY = "p455w0rdslib.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "p455w0rdslib.proxy.ClientProxy";
    public static final String DEPENDENCIES = "required-after:p455w0rdslib";
    public static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(
            0,
            2,
            1L,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());

}

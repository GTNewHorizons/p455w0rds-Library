package p455w0rdslib.util;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper.UnableToAccessFieldException;
import cpw.mods.fml.relauncher.ReflectionHelper.UnableToFindMethodException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static <T> MethodHandle findMethod(Class<T> clazz, String[] methodNames, Class<?>... methodTypes) {
        Method method = ReflectionHelper.findMethod(clazz, (Object)null, methodNames, methodTypes);

        try {
            return MethodHandles.lookup().unreflect(method);
        } catch (IllegalAccessException var5) {
            throw new UnableToFindMethodException(methodNames, var5);
        }
    }

    public static MethodHandle findFieldGetter(Class<?> clazz, String... fieldNames) {
        Field field = ReflectionHelper.findField(clazz, fieldNames);

        try {
            return MethodHandles.lookup().unreflectGetter(field);
        } catch (IllegalAccessException var4) {
            throw new UnableToAccessFieldException(fieldNames, var4);
        }
    }

    public static MethodHandle findFieldSetter(Class<?> clazz, String... fieldNames) {
        Field field = ReflectionHelper.findField(clazz, fieldNames);

        try {
            return MethodHandles.lookup().unreflectSetter(field);
        } catch (IllegalAccessException var4) {
            throw new UnableToAccessFieldException(fieldNames, var4);
        }
    }
}

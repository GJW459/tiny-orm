package com.gjw.orm.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author wei
 * 通过类加载器获取resource的辅助类
 */
public class Resources {

    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    private static InputStream getResourceAsStream(String resource) throws IOException {
        Optional<InputStream> inputStreamOptional = Optional.ofNullable(Arrays.stream(getClassLoaders())
                .map(classLoader -> classLoader.getResourceAsStream(resource))
                .filter(Objects::nonNull)
                .findFirst().orElseThrow(() -> new IOException("Could not find resource " + resource)));
        return inputStreamOptional.get();

    }

    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
    }

    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}

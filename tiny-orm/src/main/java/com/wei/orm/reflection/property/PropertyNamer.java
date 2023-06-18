package com.wei.orm.reflection.property;

import java.util.Locale;

/**
 * @author wei
 * 属性命名器
 */
public class PropertyNamer {

    private PropertyNamer() {
    }

    /**
     * 方法转换为属性
     *
     * @param name
     * @return
     */
    public static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")) {
            name = name.substring(3);
        } else {
            throw new RuntimeException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
        }
        // 如果只有一个字母 转换为小写
        // 如果大于一个字母 第二个字母非大写 转化为小写
        if (name.length() == 1 || (name.length() > 1) && !Character.isUpperCase(name.charAt(1))) {
            name = name.substring(0, 1).toUpperCase(Locale.ENGLISH) + name.substring(1);
        }
        return name;
    }

    /**
     * 开头判断 get set is
     * @param name
     * @return
     */
    public static boolean isProperty(String name) {
        return name.startsWith("get") || name.startsWith("set") || name.startsWith("is");
    }

    /**
     * 是否为 getter
     */
    public static boolean isGetter(String name) {
        return name.startsWith("get") || name.startsWith("is");
    }

    /**
     * 是否为 setter
     */
    public static boolean isSetter(String name) {
        return name.startsWith("set");
    }
}

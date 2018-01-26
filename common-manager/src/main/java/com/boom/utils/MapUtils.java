package com.boom.utils;

import java.util.HashMap;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/25
 * @Time 10:41
 * @Description MapBuilder工厂类
 */
public class MapUtils {

    public static <K,V> MapBuilder<K,V> builder() {
        return new MapBuilder<K,V>();
    }

}

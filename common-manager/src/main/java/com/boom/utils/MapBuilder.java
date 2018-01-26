package com.boom.utils;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/25
 * @Time 10:06
 * @Description Map<String,Object>对象构建工具
 */
public class MapBuilder<Key,Val> extends HashMap<Key,Val> {

    public MapBuilder<Key,Val> putVal(Key key,Val val) {
        super.put(key,val);
        return this;
    }
}

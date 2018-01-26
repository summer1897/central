package com;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * Created by Intellij IDEA
 *
 * @Projcet central
 * @Author summer
 * @Date 2018/1/15
 * @Time 16:21
 * @Description
 */
public interface SuperMapper<T> extends BaseMapper<T> {

    Long queryTotal();

}

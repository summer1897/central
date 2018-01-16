package com.boom.comparators;


import com.boom.service.dto.Node;

import java.util.Comparator;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午1:29
 * @Description {@link com.boom.service.dto.Node}比较器
 */
public class NodeComparator implements Comparator<Node> {

    /**
     * 排序标识，1表示升序，-1表示降序
     */
    private int sort;

    public NodeComparator(int sort) {
        this.sort = sort;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return this.sort * (node1.getSort() - node2.getSort());
    }
}

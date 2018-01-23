package com.boom.utils;
import com.alibaba.fastjson.JSON;
import com.boom.comparators.NodeComparator;
import com.boom.constant.Constant;
import com.boom.domain.Permission;
import com.boom.service.dto.Node;
import com.google.common.collect.Maps;
import com.summer.base.utils.BeanCloneUtils;
import com.summer.base.utils.ObjectUtils;
import com.summer.base.utils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/14 下午1:08
 * @Description 构建树形菜单工具
 */
public class TreeNode {

    private static final Logger log = LoggerFactory.getLogger(TreeNode.class);

    private List<Node> nodes;
    private Node root = null;

    private int sort = Constant.ASC;

    public TreeNode(List<Permission> permissions) {
        if (ObjectUtils.isNotEmpty(permissions)) {
            this.nodes = BeanCloneUtils.deepClone(permissions,Permission.class,Node.class);
        }
        root = new Node();
    }

    public TreeNode(List<Permission> permissions,int sort) {
        this(permissions);
        this.sort = sort;
    }

    public Node buildTree() {

        Map<Long, Node> nodeMap = Maps.newHashMap();
        if (ObjectUtils.isNotEmpty(nodes)) {
            nodeMap = PropertyUtils.extractPropertyFromDomainToMap(nodes, "id", Long.class);
        }
        if (ObjectUtils.isNotEmpty(nodeMap)) {
            for (Long nodeId : nodeMap.keySet()) {
                Node node = nodeMap.get(nodeId);
                Long parentId = ObjectUtils.isNotNull(node) ? node.getParentId() : null;
                if (ObjectUtils.isNull(parentId) || 0 == parentId) {
                    root.addChild(node);
                } else {
                    Node parent = nodeMap.get(parentId);
                    if (ObjectUtils.isNotNull(parent)) {
                        parent.addChild(node);
                    } else {
                        log.debug("parent id is: {},{}",parentId, JSON.toJSONString(node,true));
                        System.out.println("parent id is: " + parentId);
                        System.out.println("node id is: " + JSON.toJSONString(node,true));
                    }
                }
            }
        }

        if (0 != this.sort) {
            this.nodeSort(root,new NodeComparator(sort));
        }

        return root;
    }

    private void nodeSort(Node rootNode,NodeComparator nodeComparator) {
        if (ObjectUtils.isNull(nodeComparator) || ObjectUtils.isNull(rootNode)) {
            return;
        }
        List<Node> children = rootNode.getChildren();
        if (ObjectUtils.isNotEmpty(children)) {
            Collections.sort(children,nodeComparator);
            for (Node child : children) {
                nodeSort(child,nodeComparator);
            }
        }
    }

}

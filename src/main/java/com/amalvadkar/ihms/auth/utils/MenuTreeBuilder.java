package com.amalvadkar.ihms.auth.utils;

import com.amalvadkar.ihms.auth.MenuNode;
import com.amalvadkar.ihms.common.entities.MenuEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuTreeBuilder {

    public static List<MenuNode> buildMenuTree(List<MenuEntity> menuEntities) {
        // Create a map of id to MenuNode for quick lookup
        Map<Long, MenuNode> nodeMap = menuEntities.stream()
                .collect(Collectors.toMap(
                        MenuEntity::getId,
                        MenuTreeBuilder::convertToMenuNode
                ));

        // Create the tree structure
        List<MenuNode> rootNodes = new ArrayList<>();
        for (MenuNode node : nodeMap.values()) {
            if (node.getParentId() == null) {
                rootNodes.add(node);
            } else {
                MenuNode parent = nodeMap.get(node.getParentId());
                if (parent != null) {
                    parent.addChild(node);
                }
            }
        }
        return rootNodes;
    }

    private static MenuNode convertToMenuNode(MenuEntity menuEntity) {
        return new MenuNode(
                menuEntity.getId(),
                menuEntity.getName(),
                menuEntity.getRoute(),
                menuEntity.getParentId()
        );
    }
}


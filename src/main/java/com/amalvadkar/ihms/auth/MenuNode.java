package com.amalvadkar.ihms.auth;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MenuNode {
    private Long id;
    private String name;
    private String route;
    private Long parentId;
    private List<MenuNode> children;

    public MenuNode(Long id, String name, String route, Long parentId) {
        this.id = id;
        this.name = name;
        this.route = route;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    public void addChild(MenuNode child) {
        this.children.add(child);
    }
}
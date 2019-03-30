package com.example.pro.down.entity;

public enum Stick {
    YES(1, "置顶"), NO(2, "未置顶");
    private Integer type;
    private String name;

    Stick(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(Integer type) {
        for (Stick s: Stick.values()) {
            if (s.type.equals(type)) {
                return s.name;
            }
        }
        return null;
    } 
}

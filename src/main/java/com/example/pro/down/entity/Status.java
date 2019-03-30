package com.example.pro.down.entity;

public enum Status {
    ENABLE(1, "可用"), DISABLE(2, "禁用");

    private Integer type;
    private String typeName;

    Status(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public static String getTypeName(Integer type) {
        for (Status s : Status.values()) {
            if (s.type.equals(type)) {
                return s.typeName;
            }
        }
        return null;
    }
}
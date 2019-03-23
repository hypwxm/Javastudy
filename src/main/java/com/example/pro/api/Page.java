package com.example.pro.api;

import java.util.List;

public class Page<T> {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;
    private List<T> data;
}
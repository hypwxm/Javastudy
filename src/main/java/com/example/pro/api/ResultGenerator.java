package com.example.pro.api;

import java.util.HashMap;
import java.util.Map;

public final class ResultGenerator {
    public static ApiResult result(String msg, Integer status) {
        ApiResult apiResult = ApiResult.instance();
        apiResult.setMsg(msg);
        apiResult.setStatus(status);
        return apiResult;
    }

    public static ApiResult result(String msg, Integer status, Object data) {
        ApiResult apiResult = ApiResult.instance();
        apiResult.setMsg(msg);
        apiResult.setStatus(status);
        apiResult.setResult(data);
        return apiResult;
    }

    public static ApiResult errorResult(String msg, Integer status) {
        return result(msg, status);
    }

    public static ApiResult successResult(Object data) {
        return result("操作成功", 200, data);
    }

    public static ApiResult successPage(Object data, Integer total, Integer pageSize, Integer pageIndex) {
        Map<String, Object> pages = new HashMap<String, Object>();

        pages.put("pageSize", pageSize);
        pages.put("pageIndex", pageIndex);
        pages.put("total", total);
        pages.put("data", data);
        return successResult(pages);
    }
}
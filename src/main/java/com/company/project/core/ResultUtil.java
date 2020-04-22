package com.company.project.core;

/**
 * @Author tangmf
 * @Date 2020/4/21 13:48
 * @Description 响应结果生成工具
 */
public class ResultUtil {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result success() {
        return ResultUtil.success(null, DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result success(Object data) {
        return ResultUtil.success(data, DEFAULT_SUCCESS_MESSAGE);
    }

    private static Result success(Object data, String message) {
        Result result = new Result();
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static Result fail(String message) {
        return ResultUtil.fail(ResultCode.FAIL, null, message);
    }

    public static Result fail(ResultCode code, String message) {
        return ResultUtil.fail(code, null, message);
    }

    public static Result fail(ResultCode code, Object data) {
        return ResultUtil.fail(code, data, "");
    }

    private static Result fail(ResultCode code, Object data, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }
}

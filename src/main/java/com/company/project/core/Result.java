package com.company.project.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @Author tangmf
 * @Date 2020/4/21 13:48
 * @Description 统一API响应结果封装
 */
@Data
public class Result<T> {
	// 时间戳
	private long timestamp = System.currentTimeMillis();

	// 返回码
	private int code;

	// 消息
	private String message;

	// 数据
	private T data;

	public Result setCode(ResultCode resultCode) {
		this.code = resultCode.getCode();
		return this;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}

	public Result setData(T data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

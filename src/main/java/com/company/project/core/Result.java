package com.company.project.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * @Author tangmf
 * @Date 2020/4/21 13:48
 * @Description 统一API响应结果封装
 */
@Data
public class Result {
	// 时间戳
	private long timestamp = System.currentTimeMillis();

	// 消息
	private String message;

	// 返回码
	private ResultCode code = ResultCode.SUCCESS;

	// 数据
	private Object data;

	public int getCode() {
		return code.getCode();
	}

	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}

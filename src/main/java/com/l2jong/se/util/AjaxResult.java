package com.l2jong.se.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class AjaxResult {
	public boolean result; // Result True/False
	public int code; // Result Code :: 0:True, Other:False
	public String message; // Result Message
	public Map<String, Object> data;

	public AjaxResult() {
		this.result = true;
		this.code = 0;
		this.message = "";
		this.data = new HashMap<>();
	}

	public AjaxResult(boolean result, int code, String message) {
		this.result = result;
		this.code = code;
		this.message = message;
		this.data = new HashMap<>();
	}
}

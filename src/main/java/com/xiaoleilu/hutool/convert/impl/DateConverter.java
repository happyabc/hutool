package com.xiaoleilu.hutool.convert.impl;

import java.util.Calendar;
import java.util.Date;

import com.xiaoleilu.hutool.convert.AbstractConverter;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * 日期转换器
 * 
 * @author Looly
 *
 */
public class DateConverter extends AbstractConverter {

	/** 日期格式化 */
	private String format;

	/**
	 * 获取日期格式
	 * 
	 * @return 设置日期格式
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 设置日期格式
	 * 
	 * @param format 日期格式
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public Class<Date> getTargetType() {
		return Date.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Date convertInternal(Object value) {
		// Handle Calendar
		if (value instanceof Calendar) {
			return ((Calendar) value).getTime();
		}

		// Handle Long
		if (value instanceof Long) {
			//此处使用自动拆装箱
			return new Date((Long)value);
		}

		final String valueStr = convertToStr(value);
		return StrUtil.isBlank(format) ? DateUtil.parse(valueStr) : DateUtil.parse(valueStr, format);
	}

}
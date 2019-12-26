package com.github.ext.mybatis.paginator.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.ext.mybatis.paginator.domain.PagedList;

/**
 * @author miemiedev
 */
public class PagedListJsonMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	public PagedListJsonMapper() {
		SimpleModule module = new SimpleModule("PagedListJsonModule", new Version(1, 0, 0, null, null, null));
		module.addSerializer(PagedList.class, new PagedListJsonSerializer(this));
		registerModule(module);
	}
}

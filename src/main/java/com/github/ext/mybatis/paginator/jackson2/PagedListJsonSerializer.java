package com.github.ext.mybatis.paginator.jackson2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.ext.mybatis.paginator.domain.PagedList;
import com.github.ext.mybatis.paginator.domain.Paginator;

/**
 * @author miemiedev
 */
public class PagedListJsonSerializer extends JsonSerializer<PagedList> {
	ObjectMapper mapper;

	public PagedListJsonSerializer() {
		mapper = new ObjectMapper();
	}

	public PagedListJsonSerializer(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void serialize(PagedList value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		Paginator paginator = value.getPaginator();
		map.put("totalCount", paginator.getTotalCount());
		map.put("totalPages", paginator.getTotalPages());
		map.put("pageNumber", paginator.getCurrPage());
		map.put("pageSize", paginator.getPageSize());
		map.put("rows", new ArrayList(value));

		map.put("startRow", paginator.getStartRow());
		map.put("endRow", paginator.getEndRow());

		map.put("offset", paginator.getOffset());

		map.put("slider", paginator.getSlider());

		map.put("prevPage", paginator.getPrevPage());
		map.put("nextPage", paginator.getNextPage());

		map.put("isFirstPage", paginator.isFirstPage());
		map.put("isLastPage", paginator.isLastPage());
		map.put("hasPrevPage", paginator.hasPrevPage());
		map.put("hasNextPage", paginator.hasNextPage());

		mapper.writeValue(jgen, map);
	}
}

package com.liuyf.demos.json.jackson.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -4134327016241390278L;

	public CustomObjectMapper() {
		super();
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			
			@Override
			public void serialize(Object obj, JsonGenerator gen, SerializerProvider pro) throws IOException, JsonProcessingException {
				gen.writeString("");
			}
		});
	}

}

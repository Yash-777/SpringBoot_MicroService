package com.github.yash777.User.controller.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yash777.User.entity.AddressTableEntity;

public class AA_Test {
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		String json = "{\"id\":null,\"userUniqueId\":\"GITHUB-777\",\"country\":\"India\",\"state\":\"TS\",\"city\":\"HYD\",\"fullAddress\":null,\"present\":0}";
		com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
		boolean canSerialize = mapper.canSerialize(AddressTableEntity.class);
		System.out.println("canSerialize:"+canSerialize);
		//String writeValueAsString = mapper.writeValueAsString(AddressTableEntity.class);
		AddressTableEntity obj = mapper.readValue(json, AddressTableEntity.class);
		System.out.println("Obj:"+obj);
	}
}

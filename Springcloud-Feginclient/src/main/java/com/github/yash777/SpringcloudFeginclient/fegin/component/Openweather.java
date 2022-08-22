package com.github.yash777.SpringcloudFeginclient.fegin.component;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//https://openweathermap.org/current#name
@org.springframework.cloud.openfeign.FeignClient(name="openweathermap", url = "https://api.openweathermape.org", fallback = OpenweatherFallback.class)
public interface Openweather {
	//https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=
	//{"cod":401, "message": "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."}
	static final String apiKey = "211198ca54e7d89";
	
	
	// http://api.openweathermap.org/geo/1.0/direct?q=London&limit=5&appid={API key}
	@RequestMapping(method = RequestMethod.GET, value = "/data/2.5/weather?lat={lat}&lon={lon}&appid={APIkey}", consumes = "application/json")
	String getReport(@PathVariable("lat") String lat, @PathVariable("lon") String lon, @PathVariable("APIkey") String aPIkey);
}

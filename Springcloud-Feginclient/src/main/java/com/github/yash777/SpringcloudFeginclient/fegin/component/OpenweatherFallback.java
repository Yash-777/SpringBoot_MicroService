package com.github.yash777.SpringcloudFeginclient.fegin.component;

/**
 * Feign Hystrix Fallbacks: Hystrix supports the notion of a fallback: a default code path that is executed when they circuit is open or there is an error.
 * To enable fallbacks for a given @FeignClient set the fallback attribute to the class name that implements the fallback.
 * You also need to declare your implementation as a Spring bean.
 * 
 * Fallback class must implement the interface annotated by @FeignClient
 * @author yashwanth
 *
 */
public class OpenweatherFallback implements Openweather {

	@Override
	public String getReport(String lat, String lon, String aPIkey) {
		System.out.println("Fal");
		return "Due to some error exicuted fallback method";
	}

}

package com.github.yash777.SpringcloudFeginclient.fegin.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//http://localhost:8077/FeignClient/feign/msg
//http://localhost:8077/FeignClient/feign/gitrepo/OpenFeign/contributors

//http://localhost:8077/FeignClient/feign/weatherReport
@RestController
@RequestMapping("/feign")
public class FeignRestClientController {
	@Autowired
	private FeignRepoClient client;
	
	@Autowired
	private Openweather weather;
	
	@GetMapping("/msg")
	public String getMsg()
	{
		return "hi";
	}
	
	@GetMapping("/gitrepo/OpenFeign/contributors")
	public List<ContributorDTO> getContributors()
	{
		return client.contributors("OpenFeign", "feign");
	}
	
	@GetMapping("/gituserinfo")
	public List<GitHubUserDTO> getUserInfo()
	{
		return client.userInfo("Yash-777");
	}
	
	
	//feign.FeignException$Unauthorized: [401 Unauthorized] during [GET] to [https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid]
	//[Openweather#getReport(String,String,String)]: [{"cod":401, "message": "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."}]
	@GetMapping("/weatherReport")
	public String getWeatherReport()
	{
		return weather.getReport("35", "139", weather.apiKey);
	}
}

package com.github.yash777.SpringcloudFeginclient.fegin.component;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Param;
import feign.RequestLine;

//https://docs.github.com/en/rest/overview/endpoints-available-for-github-apps
//https://api.github.com/repos/OpenFeign/feign/contributors

//java.lang.IllegalStateException: Either 'name' or 'value' must be provided in @FeignClient

// java.lang.IllegalStateException: Service id not legal hostname (GitRepo_Feign)  - name = "GitRepo_Feign"
@org.springframework.cloud.openfeign.FeignClient
	(name="parent", url = "https://api.github.com", configuration = CustomFeignConfig.class)// <groupId>io.github.openfeign</groupId><artifactId>parent</artifactId>
//@Headers("Accept: application/json")  //FeignClient - configuration = CustomFeignConfig.class
public interface FeignRepoClient {
	//@RequestLine("GET /repos/{owner}/{repo}/contributors") // [feign-core-11.8.jar:na]
	//List<ContributorDTO> contributors(@Param("owner") String owner, @Param("repo") String repo);

	@RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/contributors", consumes = "application/json")
	List<ContributorDTO> contributors(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
	
	//https://api.github.com/users/Yash-777
	@RequestMapping(method = RequestMethod.GET, value = "/users/{user}", consumes = "application/json")
	List<GitHubUserDTO> userInfo(@PathVariable("user") String user);
}

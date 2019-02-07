package com.cloud.webservice.web;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.webservice.dto.posts.PostsSaveRequestDto;
import com.cloud.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsService postsService;
	private Environment env;
	
	@GetMapping("/hello")
	public String hello() {
		return "HelloWorld";
	}
	
	@PostMapping("/posts")
	public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
		return postsService.save(dto);
	}
	
	@GetMapping("/profile")
	public String getProfile () {
		return Arrays.stream(env.getActiveProfiles())
				.filter(e -> "set1".equals(e) || "set2".equals(e))
				.findFirst()
				.orElse("");
	}

}

package com.cloud.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloud.webservice.domain.posts.Posts;
import com.cloud.webservice.domain.posts.PostsRepository;
import com.cloud.webservice.dto.posts.PostsSaveRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void DtoData_store_postsTable() {
		//given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("gounewbie@gmail.com")
				.content("테스트")
				.title("테스트 타이틀")
				.build();
		
		//when
		postsService.save(dto);
		
		//then
		Posts posts = postsRepository.findAll().get(0);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
	}
}

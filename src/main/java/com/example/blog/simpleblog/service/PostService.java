package com.example.blog.simpleblog.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.simpleblog.dto.PostDto;
import com.example.blog.simpleblog.exception.PostNotFoundException;
import com.example.blog.simpleblog.model.Post;
import com.example.blog.simpleblog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PostRepository postRepository;
	
	public void createPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		User username = authService.getCurrentUser().orElseThrow(()-> new IllegalArgumentException("No User logged in"));
		post.setUsername(username.getUsername());
		post.setCreatedOn(Instant.now());
		postRepository.save(post);
	}

	public List<PostDto> showAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());	
	}
	
	private PostDto mapFromPostToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setUsername(post.getUsername());
		return postDto;
	}
	
	private Post mapFromDtoToPost(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setUsername(postDto.getUsername());
		return post;
	}

	public PostDto getSinglePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("No post found for Id "+id));
		return mapFromPostToDto(post);
	}
}

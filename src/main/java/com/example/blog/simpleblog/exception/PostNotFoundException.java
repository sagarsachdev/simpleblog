package com.example.blog.simpleblog.exception;

public class PostNotFoundException extends RuntimeException{

	public PostNotFoundException(String message) {
		super(message);
	}
}

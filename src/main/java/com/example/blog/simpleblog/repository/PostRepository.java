package com.example.blog.simpleblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.simpleblog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}

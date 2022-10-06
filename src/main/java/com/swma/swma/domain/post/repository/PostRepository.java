package com.swma.swma.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.swma.swma.domain.post.entity.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	Page<Post> findAllByOrderByIdDesc(Pageable page);
}

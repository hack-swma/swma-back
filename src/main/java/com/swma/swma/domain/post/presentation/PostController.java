package com.swma.swma.domain.post.presentation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.swma.swma.domain.post.presentation.dto.request.PostRequest;
import com.swma.swma.domain.post.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

	private final PostService postService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long savePost(@RequestBody @Valid PostRequest request) {
		return postService.savePost(request);
	}

	@PatchMapping("/{id}")
	public Long updatePost(@PathVariable("id") Long postId, @RequestBody @Valid PostRequest request) {
		return postService.updatePost(postId, request);
	}

}

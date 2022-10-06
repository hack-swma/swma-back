package com.swma.swma.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swma.swma.domain.post.entity.Post;
import com.swma.swma.domain.post.exception.PostForbiddenException;
import com.swma.swma.domain.post.exception.PostNotFoundException;
import com.swma.swma.domain.post.presentation.dto.request.PostRequest;
import com.swma.swma.domain.post.repository.PostRepository;
import com.swma.swma.global.UserUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final UserUtils userUtils;
	private final PostRepository postRepository;

	@Transactional
	public Long savePost(PostRequest request) {
		return postRepository.save(Post.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.member(request.getMember())
				.startDate(request.getStartDate())
				.endDate(request.getEndDate())
			.build()).getId();
	}
	
}

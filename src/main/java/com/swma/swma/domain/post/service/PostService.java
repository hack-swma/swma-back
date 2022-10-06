package com.swma.swma.domain.post.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swma.swma.domain.post.entity.Post;
import com.swma.swma.domain.post.exception.PostForbiddenException;
import com.swma.swma.domain.post.exception.PostNotFoundException;
import com.swma.swma.domain.post.presentation.dto.request.PostRequest;
import com.swma.swma.domain.post.presentation.dto.response.MainPageResponse;
import com.swma.swma.domain.post.presentation.dto.response.PostResponse;
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
				.user(userUtils.currentUser())
			.build()).getId();
	}

	@Transactional
	public Long updatePost(Long postId, PostRequest request) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> PostNotFoundException.EXCEPTION);

		if(!post.getUser().getUserId().equals(userUtils.currentUserId())) throw PostForbiddenException.EXCEPTION;

		post.update(request.getTitle(), request.getContent(), request.getMember(), request.getStartDate(), request.getEndDate());

		return postRepository.save(post).getId();
	}

	public void deletePost(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> PostNotFoundException.EXCEPTION);

		if(!post.getUser().getUserId().equals(userUtils.currentUserId())) throw PostForbiddenException.EXCEPTION;

		postRepository.delete(post);
	}

	public MainPageResponse getMainPage(Pageable page) {
		Page<Post> post = postRepository.findAllByOrderByIdDesc(page);

		return MainPageResponse.builder()
			.totalPages(post.getTotalPages())
			.img(userUtils.currentUser().getImg())
			.postResponses(post.stream().map(this::ofMainPageResponse).collect(Collectors.toList()))
			.build();
	}

	public PostResponse getPost(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> PostNotFoundException.EXCEPTION);
		return PostResponse.builder()
			.name(post.getUser().getName())
			.img(post.getUser().getImg())
			.id(post.getUser().getId())
			.country(post.getUser().getCountry())
			.region(post.getUser().getRegion())
			.startDate(post.getStartDate())
			.endDate(post.getEndDate())
			.title(post.getTitle())
			.member(post.getMember())
			.content(post.getContent())
			.build();
	}

	private MainPageResponse.PostResponse ofMainPageResponse(Post post) {
		return MainPageResponse.PostResponse.builder()
			.postId(post.getId())
			.title(post.getTitle())
			.startDate(post.getStartDate())
			.endDate(post.getEndDate())
			.country(post.getUser().getCountry())
			.name(post.getUser().getName())
			.img(post.getUser().getImg())
			.member(post.getMember())
			.build();
	}

}

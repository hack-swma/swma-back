package com.swma.swma.domain.post.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.swma.swma.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 30)
	private String title;

	@Column(nullable = false, length = 1000)
	private String content;

	private int member;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Post(String title, String content, int member, LocalDate startDate, LocalDate endDate, User user) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

	public void update(String title, String content, int member, LocalDate startDate, LocalDate endDate) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}

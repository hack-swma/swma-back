package com.swma.swma.domain.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.swma.swma.domain.user.entity.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
	List<Language> findAllByUserId(Long userId);
}

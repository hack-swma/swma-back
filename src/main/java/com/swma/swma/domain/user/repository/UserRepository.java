package com.swma.swma.domain.user.repository;

import com.swma.swma.domain.user.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByUserId(String userId);
	Optional<User> findUserById(Long id);
	Page<User> findAllByCountryOrderByIdDesc(String country, Pageable page);
	Page<User> findAllByOrderByIdDesc(Pageable page);
    Boolean existsUserByUserId(String userId);
	List<User> findUserByNameContaining(String name);
}
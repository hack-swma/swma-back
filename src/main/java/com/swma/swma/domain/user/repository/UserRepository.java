package com.swma.swma.domain.user.repository;

import com.swma.swma.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByUserId(String userId);
}

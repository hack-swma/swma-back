package com.swma.swma.domain.user.repository;

import com.swma.swma.domain.user.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByUserId(Long userId);
}

package com.eyetyrantdesign.collector.models.data;

import com.eyetyrantdesign.collector.models.DieCast;
import com.eyetyrantdesign.collector.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DieCastRepository extends CrudRepository<DieCast, Integer> {
  @Query("FROM DieCast WHERE user_id = :userId")
  Iterable<DieCast> findAllById(@Param("userId") Integer userId);

}

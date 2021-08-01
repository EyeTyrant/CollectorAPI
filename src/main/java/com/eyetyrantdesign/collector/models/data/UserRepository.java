package com.eyetyrantdesign.collector.models.data;

import com.eyetyrantdesign.collector.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByUserName(String userName);
}

package com.hungle.mbf.learnspring.repository;

import com.hungle.mbf.learnspring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {

}

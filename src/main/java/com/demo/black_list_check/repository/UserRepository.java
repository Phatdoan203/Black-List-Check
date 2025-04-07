package com.demo.black_list_check.repository;

import com.demo.black_list_check.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByRefreshToken(String refreshToken);
}

package com.demo.black_list_check.repository;

import com.demo.black_list_check.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Integer> {
    BlackList findByFullName(String fullName);
    BlackList findByAddress(String address);
    BlackList findByDob(String dob);
}

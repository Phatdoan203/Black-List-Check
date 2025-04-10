package com.demo.black_list_check.repository;

import com.demo.black_list_check.entity.BlackList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Integer> {
    List<BlackList> findByFullNameAndAddress(String fullName, String address);
    Page<BlackList> findAll(Pageable pageable);
//    Optional<BlackList> findByIdentity_card(String identity_card);
//    Optional<BlackList> findByCitizen_identification_card(String citizen_identification_card);
    //    Optional<List<BlackList>> findByAddress(String address);
//    Optional<List<BlackList>> findByDob(String dob);
//
//    Optional<List<BlackList>> findByFullNameAndAddress(String fullName, String address);

}

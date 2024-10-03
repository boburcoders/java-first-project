package com.company.first_project.repository;

import com.company.first_project.module.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {


    Optional<Users> findByIdAndDeletedAtIsNull(Integer userId);

    Users getUsersById(Integer card);
    List<Users> findAllByDeletedAtIsNull();

    Boolean existsByIdAndDeletedAtIsNull(Integer userId);
    Boolean existsByEmailAndDeletedAtIsNull(String email);
}

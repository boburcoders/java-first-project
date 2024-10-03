package com.company.first_project.repository;

import com.company.first_project.module.Card;
import com.company.first_project.module.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByIdAndDeletedAtIsNull(Integer cardId);


//    List<Card> findAllByDeletedAtIsNullAndUserId(Users users);

    List<Card> findAllByDeletedAtIsNull();

    List<Card> findAllByUserIdAndDeletedAtIsNull(Integer userId);

}

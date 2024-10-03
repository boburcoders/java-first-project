package com.company.first_project.repository;

import com.company.first_project.module.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    Optional<Products> findByIdAndDeletedAtIsNull(Integer prodId);
    List<Products> findAllByUserIdAndDeletedAtIsNull(Integer userId);
    List<Products> findAllByDeletedAtIsNull();
}

package com.hungle.mbf.learnspring.repository;

import com.hungle.mbf.learnspring.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Integer> {

}

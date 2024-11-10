package org.example.repository;

import org.example.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer  > {
    @Query(value = "SELECT c FROM LoginEntity c WHERE email=?1") //This is JPQL
    LoginEntity findByEmail(String email);
    @Query(value = "SELECT COUNT(c)>0 FROM LoginEntity c WHERE email=?1") //This is JPQL
    Boolean existsByEmail(String email);
}

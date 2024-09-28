package org.example.repository;

import org.example.entity.CartItemsEntity;
import org.example.entity.CartItemsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItemsEntity, Long> {
}

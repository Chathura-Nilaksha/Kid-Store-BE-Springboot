package org.example.repository;

import org.example.entity.CardDataEntity;
import org.example.entity.CartItemsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDataRepository extends JpaRepository<CardDataEntity, Long> {
}

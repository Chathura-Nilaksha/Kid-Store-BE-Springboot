package org.example.repository;

import org.example.entity.CartItemsListEntity;
import org.example.entity.CustomerAndOrderDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAndOrderDataRepository extends JpaRepository<CustomerAndOrderDataEntity, Long> {
}

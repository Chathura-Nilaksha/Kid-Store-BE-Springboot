package org.example.repository;

import org.example.entity.CustomerAndOrderDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerAndOrderDataRepository extends JpaRepository<CustomerAndOrderDataEntity, Long> {
    @Query(value = "SELECT c FROM CustomerAndOrderDataEntity c WHERE c.cartItemsListEntity.orderCode=?1")
    Optional<CustomerAndOrderDataEntity> findByForeignKeyOfOderCode(Integer orderCode);
}

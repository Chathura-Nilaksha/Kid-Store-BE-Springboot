package org.example.repository;

import org.example.entity.OrderBillingAddressDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBillingAddressDataRepository extends JpaRepository<OrderBillingAddressDataEntity, Long> {

}

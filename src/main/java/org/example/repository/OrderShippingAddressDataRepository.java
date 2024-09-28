package org.example.repository;

import org.example.entity.OrderShippingAddressDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderShippingAddressDataRepository extends JpaRepository<OrderShippingAddressDataEntity, Long> {

}

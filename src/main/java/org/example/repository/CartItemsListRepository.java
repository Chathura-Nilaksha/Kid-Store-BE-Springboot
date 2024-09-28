package org.example.repository;

import org.example.entity.CartItemsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsListRepository extends JpaRepository<CartItemsListEntity, Integer> {
    public List<Integer> findOrderCodeByCustomerCodeFromDB(Integer customerCodeToViewPreviousOrders);
}

package org.example.repository;

import org.example.entity.CartItemsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemsListRepository extends JpaRepository<CartItemsListEntity, Integer> {
    @Query(value= "SELECT c.orderCode FROM CartItemsListEntity c WHERE c.customerCodeFromDB=?1")
        //check ok ? of above query for firefox API calling
    List<Integer> findOrderCodeByCustomerCodeFromDB(Integer customerCodeToViewPreviousOrders);
}

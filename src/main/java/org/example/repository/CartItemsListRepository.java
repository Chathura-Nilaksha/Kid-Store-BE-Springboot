package org.example.repository;

import org.example.entity.CartItemsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CartItemsListRepository extends JpaRepository<CartItemsListEntity, Integer> {
    @Query(value= "SELECT c.orderCode FROM CartItemsListEntity c WHERE c.customerCodeFromDB=?1")
        //check ok ? of above query for firefox API calling
    List<Integer> findOrderCodeByCustomerCodeFromDB(Integer customerCodeToViewPreviousOrders);

    @Query(value = "SELECT c.grandTotal FROM CartItemsListEntity c WHERE c.orderCode=?1")
    Double findGrandTotalByOrderCode(Integer orderCode);

    @Query(value = "SELECT c.entityCreatingTime FROM CartItemsListEntity c WHERE c.orderCode=?1")
    LocalDateTime findLocalDateTimeByOrderCode(Integer orderCode);
}

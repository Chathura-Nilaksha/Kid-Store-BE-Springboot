package org.example.repository;

import org.example.entity.CardDataEntity;
import org.example.entity.CartItemsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardDataRepository extends JpaRepository<CardDataEntity, Long> {
    @Query(value = "SELECT c.cardType FROM CardDataEntity c WHERE c.cartItemsListEntity.orderCode=?1")
    String findCardTypeByOrderCode(Integer orderCode);
    /*    or like below 2 lines.
     @Query(value = "SELECT c.cardType FROM CardDataEntity c WHERE c.cartItemsListEntity.orderCode=:oc ")
     String findCardTypeByOrderCode( @Param(value = "oc") Integer orderCode ); */

}

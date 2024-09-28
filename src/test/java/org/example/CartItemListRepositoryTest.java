package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.CartItemsEntity;
import org.example.entity.CartItemsListEntity;
import org.example.repository.CartItemsListRepository;
import org.example.repository.CartItemsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.List;

@SpringBootTest
@Slf4j
public class CartItemListRepositoryTest {
    @Autowired
    CartItemsListRepository cartItemsListRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;

    @Test
    public void saveCartItemListWithCartItemsTest(){
        CartItemsListEntity cartItemsListEntity = new CartItemsListEntity();
        cartItemsListEntity.setOrderCode(7);
        CartItemsListEntity saveDB = cartItemsListRepository.save(cartItemsListEntity);

        CartItemsEntity cartItemsEntity = new CartItemsEntity();
        cartItemsEntity.setCartItemsListEntity(saveDB);
        cartItemsEntity.setId(1);
        cartItemsEntity.setName("shirt");
        cartItemsEntity.setQuantity(4);
        cartItemsEntity.setPrice(403.50);
        cartItemsRepository.save(cartItemsEntity);

        CartItemsEntity cartItemsEntity2 = new CartItemsEntity();
        cartItemsEntity2.setCartItemsListEntity(saveDB);
        cartItemsEntity2.setId(2);
        cartItemsEntity2.setName("s");
        cartItemsEntity2.setQuantity(5);
        cartItemsEntity2.setPrice(503.50);
        cartItemsRepository.save(cartItemsEntity2);
    }

    @Test
    public void getAllCartItemsListWithCartItems(){
        List<CartItemsListEntity> cartItemsListEntities =
                                    cartItemsListRepository.findAll();
        cartItemsListEntities.forEach(cartItemsListEntity ->
                log.info(String.valueOf(cartItemsListEntity.getEntityList().size()))
                );
    }
}

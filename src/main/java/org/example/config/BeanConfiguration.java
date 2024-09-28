package org.example.config;

import org.example.dto.CartItems;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper makeModelMapperObject(){
        return new ModelMapper();
    }

//    @Bean
//    public CartItems makeCartItemsObject(){
//        return new CartItems();
//    }



}

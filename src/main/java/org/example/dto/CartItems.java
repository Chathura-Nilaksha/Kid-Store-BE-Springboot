package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartItems {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;

//    public CartItems getItems(){
//        return CartItems;
//    }
}

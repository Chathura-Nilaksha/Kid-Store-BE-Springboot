package org.example.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemNo;

    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "orderCode", nullable = false)
    private CartItemsListEntity cartItemsListEntity;
}

package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="card_data")
public class CardDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String ccName;
    private String ccNumber;
    private String ccExpiration;
    private String cccvv;
    private String cardType;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@OneToOne(mappedBy = "cardDataEntity")
    @OneToOne
    @JoinColumn(name = "fk_order_code", nullable = false)
    private CartItemsListEntity cartItemsListEntity;
}

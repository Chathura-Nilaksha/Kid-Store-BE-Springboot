package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.java.DateJavaType;
import org.springframework.data.auditing.CurrentDateTimeProvider;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_item_list")

//@TypeDef(
//        name = "CartItemsEntityType",
//        typeClass = JsonType.class // or some other type
//)
//public class CartEntity {
//    @Type(type = "CartItemsEntityType")
//    private List<CartItemsEntity> cartItems;
//}


//@TypeDef(
//        name = "CartItemsEntityType",
//        typeClass = JsonType.class // or some other type
//)

public class CartItemsListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderCode")
    private Integer orderCode;

    // Better put the data for the orderCode as need when customer checking his previous orders.
    @CreationTimestamp
    @Column(updatable = false) // To ensure the creation date doesn't change after insertion
    private LocalDateTime entityCreatingTime;

    // customer identifying code
    private Integer customerCodeFromDB;
    private Double grandTotal;

    //@Type(type = "CartItemsEntityType")
    @OneToMany(mappedBy = "cartItemsListEntity", fetch = FetchType.EAGER ,cascade = CascadeType.ALL, orphanRemoval = true) //
    private List<CartItemsEntity> entityList;



    //@OneToMany(mappedBy = "cartItemsListEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne(mappedBy ="cartItemsListEntity", cascade=CascadeType.ALL, orphanRemoval = true)
    private OrderBillingAddressDataEntity orderBillingAddressDataEntity;

    //@OneToMany(mappedBy = "cartItemsListEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne(mappedBy ="cartItemsListEntity", cascade=CascadeType.ALL, orphanRemoval = true)
    private OrderShippingAddressDataEntity orderShippingAddressDataEntity;

//    //@OneToMany(mappedBy = "cartItemsListEntity", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OneToOne(mappedBy ="cartItemsListEntity", cascade=CascadeType.ALL, orphanRemoval = true)
//    private CardDataEntity cardDataEntity;
//    --finally decided remove this from here and send card data only to 3rd party--

}

//to give this ID to other related entities.
//    @Id
//    private Integer orderCode;  // Same ID as CartItemsListEntity
//
//    @OneToOne
//    @MapsId  // Use the ID from CartItemsListEntity
//    @JoinColumn(name = "id")
//    private CartItemsListEntity cartItemsListEntity;

//from cht for above(to give this ID to other related entities)
//@Id
//private Long id;  // Same ID as PrimaryEntity
//
//    @OneToOne
//    @MapsId  // Use the ID from PrimaryEntity
//    @JoinColumn(name = "id")
//    private PrimaryEntity primaryEntity;
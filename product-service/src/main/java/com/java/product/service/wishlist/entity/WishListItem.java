package com.java.product.service.wishlist.entity;

import com.java.product.service.product.entity.Product;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_user_product_wishlist", columnNames = {"wishlist_id", "product_id"})
        }
)
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID wishlistItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", nullable = false)
    private WishList wishList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}


package com.java.product.service.wishlist.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID wishlistId;

    @Column(nullable = false, unique = true)
    private UUID userId;

    @Builder.Default
    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishListItem> items = new ArrayList<>();

    public void addItem(WishListItem item) {
        this.items.add(item);
        item.setWishList(this);
    }
}
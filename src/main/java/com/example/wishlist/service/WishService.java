package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WishService {

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public List<Wish> getWishesByWishlistId(int wishlistId) {
        return wishRepository.getWishesByWishlistId(wishlistId);
    }

    public Optional<Wish> getWishById(int wishId) {
        return wishRepository.getWishById(wishId);
    }

    public Wish createWish(String wishTitle,
                           BigDecimal price,
                           String description,
                           String url,
                           int wishlistId) {
        Wish wish = new Wish();
        wish.setWishTitle(wishTitle);
        wish.setPrice(price);
        wish.setDescription(description);
        wish.setUrl(url);
        wish.setWishlistId(wishlistId);

        return wishRepository.createWish(wish);
    }

    public void updateWish(int wishId,
                           String wishTitle,
                           int price,
                           String description,
                           String url,
                           int wishlistId) {
        Wish wish = new Wish();
        wish.setWishId(wishId);
        wish.setWishTitle(wishTitle);
        wish.setPrice(price);
        wish.setDescription(description);
        wish.setUrl(url);
        wish.setWishlistId(wishlistId);

        wishRepository.updateWish(wish);
    }

    public void deleteWish(int wishId) {
        wishRepository.deleteWish(wishId);
    }
}

package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import com.example.wishlist.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    private WishListRepository wishListRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
    }

    public List<Wish> getAllWishes(){
        return wishListRepository.getAllWishes();
    }

    public List<WishList> getUserWishlists(int userId) {
        return wishListRepository.getUserWishlists(userId);
    }
    public WishList createWishlist(int userId, WishList wishList) {
        wishList.setUserId(userId);
        return wishListRepository.createWishlist(wishList);
    }

    public WishList getWishlist(int userId, int wishlistId) {
        return wishListRepository.getWishlist(userId, wishlistId);
    }

    public WishList updateWishlist(int userId, int wishlistId, WishList wishList) {
        wishList.setUserId(userId);
        return wishListRepository.updateWishlist(wishList);
    }

    public void deleteWishlist(int userId, int wishlistId) {
        wishListRepository.deleteWishlist(userId, wishlistId);
    }
    public boolean existsById(int userId) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count != null && count > 0;
    }

}

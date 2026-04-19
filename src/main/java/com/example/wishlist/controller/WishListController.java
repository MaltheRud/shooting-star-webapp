package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import com.example.wishlist.service.WishListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shootingstar")
public class WishListController {
    private final WishListService wishListService;


    @Autowired
    public WishListController(WishListService wservice){
        this.wishListService = wservice;

    }

// SHOWALL - user specific
    @GetMapping("/users/{userId}/wishlists")
    public ResponseEntity<List<WishList>> getUserWishlists(@PathVariable int userId){
        List<WishList> wishLists = wishListService.getUserWishlists(userId);
        return new ResponseEntity<>(wishLists,HttpStatus.OK);
    }
// Create new wishlist
        @PostMapping("/users/{userId}/wishlists")
    public ResponseEntity<WishList> createWishlist(@PathVariable int userId, @RequestBody WishList wishList){
        WishList createdWishList = wishListService.createWishlist(userId,wishList);
        return new ResponseEntity<>(createdWishList,HttpStatus.CREATED);

}
@GetMapping("/homepage")
    public String homepage() {
        return "<h1>Ønskeskyen Clone: Online!</h1><p>The skeleton is alive.</p>";
    }

    @GetMapping("/wishes")
    public ResponseEntity<List<Wish>> getAllWishes(){
        List<Wish> wishes = wishListService.getAllWishes();
        return new ResponseEntity<>(wishes, HttpStatus.OK);
    }
@PostMapping("/{wishlistId}/update")
    public String updateWishlist(@PathVariable int wishlistId, @RequestParam String title, HttpSession session) {
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        return "redirect:/login";
    }
    WishList wishList = wishListService.getWishlist(userId, wishlistId);
    if (wishList == null) {
        return "redirect:/wishlists";
    }

    wishList.setTitle(title);
    wishListService.updateWishlist(userId, wishlistId, wishList);
    return "redirect:/wishlists/" + wishlistId;
}}




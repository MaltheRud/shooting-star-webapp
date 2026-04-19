package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import com.example.wishlist.service.WishListService;
import com.example.wishlist.service.WishService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/wishlists/{wishlistId}/wishes")
public class WishController {

    private final WishService wishService;
    private final WishListService wishListService;

    public WishController(WishService wishService, WishListService wishListService) {
        this.wishService = wishService;
        this.wishListService = wishListService;
    }

    @PostMapping
    public String createWish(@PathVariable int wishlistId,
                             @RequestParam String wishTitle,
                             @RequestParam(required = false) int price,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String url,
                             HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Optional<WishList> optionalWishList = wishListService.getWishlistById(wishlistId);
        if (optionalWishList.isEmpty() || optionalWishList.get().getUserId() != userId) {
            return "redirect:/wishlists";
        }

        wishService.createWish(wishTitle, price, description, url, wishlistId);
        return "redirect:/wishlists/" + wishlistId;
    }

    @GetMapping("/{wishId}/edit")
    public String showEditWishForm(@PathVariable int wishlistId,
                                   @PathVariable int wishId,
                                   HttpSession session,
                                   Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Optional<WishList> optionalWishList = wishListService.getWishlistById(wishlistId);
        Optional<Wish> optionalWish = wishService.getWishById(wishId);

        if (optionalWishList.isEmpty() || optionalWish.isEmpty()) {
            return "redirect:/wishlists";
        }

        if (optionalWishList.get().getUserId() != userId || optionalWish.get().getWishlistId() != wishlistId) {
            return "redirect:/wishlists";
        }

        model.addAttribute("wishlist", optionalWishList.get());
        model.addAttribute("wish", optionalWish.get());
        return "wish-edit";
    }

    @PostMapping("/{wishId}/update")
    public String updateWish(@PathVariable int wishlistId,
                             @PathVariable int wishId,
                             @RequestParam String wishTitle,
                             @RequestParam(required = false) int price,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String url,
                             HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Optional<WishList> optionalWishList = wishListService.getWishlistById(wishlistId);
        Optional<Wish> optionalWish = wishService.getWishById(wishId);

        if (optionalWishList.isEmpty() || optionalWish.isEmpty()) {
            return "redirect:/wishlists";
        }

        if (optionalWishList.get().getUserId() != userId || optionalWish.get().getWishlistId() != wishlistId) {
            return "redirect:/wishlists";
        }

        wishService.updateWish(wishId, wishTitle, price, description, url, wishlistId);
        return "redirect:/wishlists/" + wishlistId;
    }

    @PostMapping("/{wishId}/delete")
    public String deleteWish(@PathVariable int wishlistId,
                             @PathVariable int wishId,
                             HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Optional<WishList> optionalWishList = wishListService.getWishlistById(wishlistId);
        Optional<Wish> optionalWish = wishService.getWishById(wishId);

        if (optionalWishList.isPresent()
                && optionalWish.isPresent()
                && optionalWishList.get().getUserId() == userId
                && optionalWish.get().getWishlistId() == wishlistId) {
            wishService.deleteWish(wishId);
        }

        return "redirect:/wishlists/" + wishlistId;
    }
}


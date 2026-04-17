package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WishListRepository {

    private JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish>getAllWishes(){
        String sql = "SELECT * FROM wish";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Wish.class));
    }
    public List<WishList> getUserWishlists(int userId) {
        String sql = "SELECT wishlist_id,user_id FROM wishlist WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WishList.class), userId);
    }
    public WishList createWishlist(WishList wishList) {
        String sql = "INSERT INTO wishlist(title,user_id VALUES(?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishList.getTitle());
            ps.setInt(2, wishList.getUserId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            wishList.setWishlistID(keyHolder.getKey().intValue());
        }
        return wishList;
)
    }

    public WishList getWishlist(int userId, int wishlistId) {
    }

    public void deleteWishlist(int userId, int wishlistId) {
    }
}

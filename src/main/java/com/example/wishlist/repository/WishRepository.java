package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Wish> getWishesByWishlistId(int wishlistId) {
        String sql = """
                SELECT wish_id,
                       wish_title, 
                       price,
                       description,
                       url,
                       wishlist_id
                FROM wish
                WHERE wishlist_id = ?
                ORDER BY wish_id
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Wish.class), wishlistId);
    }

}

package org.boot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.boot.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
    private final JdbcTemplate jdbcTemplate;
    private static final String QUERY = "select * from product where product_id=?";

    @GetMapping("/get")
    public ResponseEntity<Product> getProduct(@RequestParam Integer id) {
        Product product = jdbcTemplate.queryForObject(QUERY, new BeanPropertyRowMapper<>(Product.class), id);
        log.info(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProducts() {
        log.info("getProducts");
        List<Product> products = jdbcTemplate.query("select * from product", new BeanPropertyRowMapper<>(Product.class));
        return ResponseEntity.ok(products);
    }


}

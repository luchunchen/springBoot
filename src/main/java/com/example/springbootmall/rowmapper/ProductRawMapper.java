package com.example.springbootmall.rowmapper;

import com.example.springbootmall.constant.ProductCategroy;
import com.example.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRawMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));

        String categroyStr = resultSet.getString("category");
        ProductCategroy categroy = ProductCategroy.valueOf(categroyStr);
        product.setCategory(categroy);

        //也可以用1行解決
//      product.setCategory(ProductCategroy.valueOf(resultSet.getString("category")));




        product.setImageUrl(resultSet.getString("image_url"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreatedDate(resultSet.getTimestamp("created_date"));
        product.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return product;
    }
}

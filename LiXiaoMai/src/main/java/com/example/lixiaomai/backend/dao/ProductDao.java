package com.example.lixiaomai.backend.dao;

import com.example.lixiaomai.backend.entity.Product;
import com.example.lixiaomai.backend.tools.DatabaseUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    private final QueryRunner runner = DatabaseUtils.getRunner();

    public List<Product> getAllProductBySId() {
        try {
            Connection conn = DatabaseUtils.getConnection();
            String sql = "SELECT * FROM PRODUCT";
            return runner.query(conn, sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteProductById(int id) {
        try {
            Connection conn = DatabaseUtils.getConnection();
            String sql = "DELETE FROM PRODUCT WHERE ID = ?";
            return runner.update(conn, sql, id) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProductBySid(int sId) {
        try {
            Connection conn = DatabaseUtils.getConnection();
            String sql = "SELECT * FROM PRODUCT WHERE sId = ?";
            return runner.query(conn, sql, new BeanListHandler<>(Product.class), sId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateProduct(Product product, int pId) {
        try {
            String sql = "UPDATE PRODUCT SET NAME = ?, PRICE = ?, STOCK = ?, TYPE = ?, DESCRIPTION = ?, SID = ? WHERE ID = ?";
            Connection conn = DatabaseUtils.getConnection();
            return runner.update(conn,
                    sql,
                    product.getName(), product.getPrice(), product.getStock(), product.getType(), product.getDescription(), product.getSId(), pId) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addProduct(Product product) {

        try {
            String sql = "INSERT INTO PRODUCT (NAME, PRICE, STOCK,URL,TYPE, DESCRIPTION, SID) VALUES (?,?,?,?,?,?,?)";
            Connection conn = DatabaseUtils.getConnection();
            return runner.update(conn,
                    sql,
                    product.getName(), product.getPrice(), product.getStock(),product.getUrl(), product.getType(), product.getDescription(), product.getSId()) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProductById(int id){
        try {
            Connection conn = DatabaseUtils.getConnection();
            String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
            Product product =  runner.query(conn, sql, new BeanHandler<>(Product.class), id);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getUrlById(int id){
        try {
            Connection conn = DatabaseUtils.getConnection();
            String sql = "SELECT URL FROM PRODUCT WHERE ID = ?";
            String url =  runner.query(conn, sql, new BeanHandler<>(String.class), id);
            return url;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}

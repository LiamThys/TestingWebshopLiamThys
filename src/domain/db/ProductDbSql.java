package domain.db;

import domain.db.DbException;
import domain.db.ProductDb;
import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDbSql implements ProductDb {
    private Properties properties;
    private String url;

    public ProductDbSql(Properties properties){
        try{
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        }catch (Exception e){
            throw new DbException(e.getMessage(),e);
        }
    }


    @Override
    public Product get(int id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE id=?";
        try(
                Connection connection = DriverManager.getConnection(url,properties);
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            result.next();

            String name = result.getString("name");
            String description = result.getString("description");
            double price = result.getDouble("price");
            try{
                product = new Product(id,name,description,price);
            }catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(url,properties);
                Statement statement = connection.createStatement();
                ){
            ResultSet result = statement.executeQuery("SELECT * FROM product");
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");
                try{
                    Product product = new Product(id,name,description,price);
                    products.add(product);
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
        return products;
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO product(name,description,price) VALUES (?,?,?)";
        try(
                Connection connection = DriverManager.getConnection(url,properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1,product.getName());
            statement.setString(2,product.getDescription());
            statement.setDouble(3,product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
        if (product == null) {
            throw new DbException("No product given");
        }
        try (
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            //still returns error fix needed
            statement.setString(1,product.getName());
            statement.setString(2,product.getDescription());
            statement.setDouble(3,product.getPrice());
            statement.setInt(4,product.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("query failed", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE id=?";
        try(
                Connection connection = DriverManager.getConnection(url,properties);
                PreparedStatement statement = connection.prepareStatement(sql);
                ){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
    }

    @Override
    public int getNumbeOfProducts() {
        try(
                Connection connection = DriverManager.getConnection(url,properties);
                Statement statement = connection.createStatement();
        ){
            String sql = "SELECT COUNT (*) FROM products";
            ResultSet result = statement.executeQuery(sql);
            return result.getInt("count");
        }catch (SQLException e){
            throw new DbException(e.getMessage(),e);
        }
    }
}

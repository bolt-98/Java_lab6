package laba_6;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws SQLException {

    }
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_java_lab6","root","root");
        return connection;
    }
    public static void  insertProduct (Product p ) throws SQLException {//додати продукт в таблицю
        Connection conn= getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO product(price, name, timeToFalse,) VALUES('"+p.getPrice()+"', '"+p.getName()+"','"+p.getTimeToFalse()+"')");
        statement.execute();
        conn.close();
    }
    public static void insertBasket(Basket b) throws SQLException {//додати корзни та продукт в корзину
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("insert into basket(nameBayer, color)  values('"+b.getName_buyer()+"','"+b.getColor()+"')");
        ResultSet rs = statement.executeQuery("SELECT idbasket FROM basket WHERE nameBayer='" + b.getName_buyer() + "';");
        rs.next();
        int id_for_products = rs.getInt("idbasket");
        for(Product p : b.getPdr()) {
            statement.execute("INSERT INTO product(price, name, timeToFalse) VALUES('"+p.getPrice()+"', '"+p.getName()+"','"+p.getTimeToFalse()+"')");
        }
    }
    public static List<Product> getAllProducts() throws SQLException {//отримати всі продуктии
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM product;");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getDouble("price"), rs.getTimestamp("timeToFalse").toLocalDateTime().toLocalDate());
            res.add(p);
        }
        return res;
    }
    public static List<Product> getProductsByOriginId(int id_origin) throws SQLException {//отримати всі продукти по корзини
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM product WHERE basketID=" + id_origin + ";");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getDouble("price"), rs.getTimestamp("timeToFalse").toLocalDateTime().toLocalDate());
            res.add(p);
        }
        return res;
    }
    public static List<Basket> getAllBaskets() throws SQLException {//всі корзини
        List<Basket> res = new ArrayList<Basket>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM basket;");
        while(rs.next()){
            Basket b = new  Basket(getProductsByOriginId(rs.getInt("idbasket")) ,rs.getString("color"), rs.getString("nameBayer"));
            res.add(b);
        }
        return res;
    }
    public static Basket getBasketById(int id) throws SQLException {
        Connection con  = getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT  * FROM basket WHERE idbasket=" + id + ";");
        rs.next();
        Basket b = new Basket(getProductsByOriginId(rs.getInt("idbasket")) ,rs.getString("color"), rs.getString("nameBayer"));
        return b;
    }
    public static void clearAllProducts() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM database_java_lab6.product WHERE idProduct>0");
        conn.close();
    }
    public static void clearAllBaskets() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("DELETE FROM database_java_lab6.basket WHERE idbasket>0;");
        conn.close();
    }
    public static List<Product> getProdutcOfTimeFalse() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM product WHERE  timeToFalse < NOW()");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getDouble("price"), rs.getTimestamp("timeToFalse").toLocalDateTime().toLocalDate());
            products.add(p);
        }
        return products;

    }
    public static List<Product> getProdutcOfTimeTrue() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM product WHERE  timeToFalse > NOW()");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getDouble("price"), rs.getTimestamp("timeToFalse").toLocalDateTime().toLocalDate());
            products.add(p);
        }
        return products;

    }

}

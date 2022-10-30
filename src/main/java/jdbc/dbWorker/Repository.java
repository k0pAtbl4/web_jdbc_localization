package jdbc.dbWorker;

import jdbc.entity.Order;
import jdbc.entity.Product;
import jdbc.entity.ProductInfo;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Repository {
    public Order findByNumber(int number)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<ProductInfo> list = new LinkedList<>();
        String query = "SELECT * FROM shop.order_table WHERE " +
                "order_table.id = " + number;

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        Statement st = cp.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        String orderStr = "";
        String dateStr = "";
        while (resultSet.next()) {
            orderStr = resultSet.getString("order");
            dateStr = resultSet.getString("date");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        String[] orderArray = orderStr.split(",");
        for (int i = 0; i < orderArray.length; i++) {
            String productName = orderArray[i];
            int productAmount = 0;

            query = "SELECT * FROM shop.item_in_order WHERE " +
                    "item_in_order.product = '" + productName + "'";
            resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                productAmount = resultSet.getInt("amount");
            }

            query = "SELECT * FROM shop.product WHERE " +
                    "product.name = '" + productName + "'";
            resultSet = st.executeQuery(query);
            String description = "";
            String cost = "";
            while (resultSet.next()) {
                description = resultSet.getString("description");
                cost = resultSet.getString("cost");
            }

            Product p = new Product(productName, description, (int) Integer.parseInt(cost));
            ProductInfo pi = new ProductInfo(number, p, productAmount);
            list.add(pi);
        }
        return new Order(number, list, date);
    }

    public List<Integer> ordersThatContainProduct(String searchedProduct)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Integer> list = new LinkedList<>();

        String query = "SELECT shop.item_in_order.order_number FROM shop.item_in_order WHERE " +
                "shop.item_in_order.product IN ('" + searchedProduct + "')";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        Statement st = cp.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        while (resultSet.next()) {
            list.add(resultSet.getInt("order_number"));
        }
        return list;
    }

    public List<Integer> ordersThatDontContainProductToday(String searchedProduct)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Integer> list = ordersThatContainProduct(searchedProduct);
        List<Integer> resultList = new LinkedList<>();

        StringBuilder query = new StringBuilder("SELECT order_table.id" + " FROM shop."
                + "order_table WHERE ");
        for (Integer integer : list) {
            query.append("order_table.id <> ").append(integer).append(" AND");
        }
        query.append(" order_table.date BETWEEN " +
                "Addtime(Curdate(), '00:00:00') AND Addtime(Curdate(), '23:59:59')");

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        Statement st = cp.createStatement();
        ResultSet resultSet = st.executeQuery(query.toString());

        while (resultSet.next()) {
            resultList.add(resultSet.getInt("id"));
        }
        return resultList;
    }

    public List<Integer> sumLessThanGivenAmountEqualsToGiven(int sum, int amount)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Integer> result = new LinkedList<>();

        String query = "SELECT item_in_order.order_number" +
                " FROM shop.item_in_order" +
                " JOIN product" +
                " ON item_in_order.product = product.name" +
                " GROUP BY order_number" +
                " HAVING SUM(product.cost * item_in_order.amount) <= " + sum +
                " AND COUNT(DISTINCT item_in_order.id) = " + amount;

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        Statement st = cp.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        while (resultSet.next()) {
            result.add(resultSet.getInt("order_number"));
        }
        return result;
    }

    public void newOrderFromTodayProducts()
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String insertToItemTable = "INSERT INTO item_in_order" +
                "(item_in_order.order_number, item_in_order.product, item_in_order.amount) " +
                "SELECT MAX(shop.order_table.id) + 1 AS order_id, " +
                "item_in_order.id, SUM(item_in_order.amount) / 2 " +
                "FROM item_in_order, shop.order_table " +
                "WHERE item_in_order.id IN " +
                "(SELECT DISTINCT item_in_order.order_number FROM shop.order_table " +
                "WHERE shop.order_table.date " +
                "BETWEEN Addtime(Curdate(), '00:00:00') AND Addtime(Curdate(), '23:59:59')) " +
                "GROUP BY item_in_order.id";
        String insertToOrderTable = "INSERT INTO shop.order_table " +
                "(shop.order_table.id, shop.order_table.order, shop.order_table.date) " +
                "SELECT MAX(shop.order_table.id) + 1, shop.order_table.order, CURDATE() " +
                "FROM shop.order_table";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        PreparedStatement st1 = cp.prepareStatement(insertToItemTable);
        PreparedStatement st2 = cp.prepareStatement(insertToOrderTable);
        st1.executeUpdate();
        st2.executeUpdate();
    }

    public void deleteOrderWithProductOfAmount(String searchedProduct, int amount)
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "DELETE shop.order_table, item_in_order FROM shop.order_table " +
                "JOIN item_in_order ON shop.order_table.id = item_in_order.order_number " +
                "JOIN product ON item_in_order.product IN (product.name) " +
                "WHERE product.name = '" + searchedProduct + "' " +
                "AND item_in_order.amount = " + amount;

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        PreparedStatement st = cp.prepareStatement(query);
        st.executeUpdate();
    }

    public boolean isUser(String password, String userId) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        List<Integer> result = new LinkedList<>();

        String query = "SELECT user.id" +
                " FROM shop.user" +
                " WHERE user.name = '" + userId + "'" +
                " AND user.password = '" + password + "'";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        Statement st = cp.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        while (resultSet.next()) {
            result.add(resultSet.getInt("id"));
        }
        return !result.isEmpty();
    }

    public boolean isAdminUser(String password, String userId) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        List<Integer> result = new LinkedList<>();

        String query = "SELECT user.id" +
                " FROM shop.user" +
                " WHERE user.name = '" + userId + "'" +
                " AND user.password = '" + password + "'" +
                " AND user.admin = 1";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection cp = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shop", "root", "1qaz2wsxzxcZ1");

        Statement st = cp.createStatement();
        ResultSet resultSet = st.executeQuery(query);

        while (resultSet.next()) {
            result.add(resultSet.getInt("id"));
        }
        return !result.isEmpty();
    }

}

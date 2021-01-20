package dao.impl;

import dao.ICustomerDAO;
import model.Customer;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {
    private static final String INSERT_CUSTOMER_SQL = "Insert into Customer(id, name, email, address) values (?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER_BY_ID = "Select * from Customer where id = ?";
    private static final String SELECT_ALL_CUSTOMER = "Select * from Customer";
    private static final String DELETE_USER_BY_ID = "Delete from Customer where id = ?";
    private static final String UPDATE_USER = "Update Customer set name = ?, email = ?, address = ? where id = ?";

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);

        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getAddress());

        preparedStatement.executeUpdate();
    }

    @Override
    public Customer getCustomer(int id) throws SQLException {
        Customer customer = null;

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * from Customer where id = " + id);
        // id : 4;select * from admin

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");

            customer = new Customer(id, name, email, address);
        }


        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() throws SQLException {
        List<Customer> customerList = new ArrayList<Customer>();

        Connection connection = DBConnection.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);

//        ResultSet resultSet = preparedStatement.executeQuery();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_CUSTOMER);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");

            customerList.add(new Customer(id, name, email, address));
        }

        return customerList;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
        preparedStatement.setInt(1, id);

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getEmail());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setInt(4, customer.getId());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public void insertCustomerByProcedure(Customer customer) throws SQLException {
        Connection connection = DBConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call insert_customer(?, ?, ?, ?)}");
        callableStatement.setInt(1, customer.getId());
        callableStatement.setString(2, customer.getName());
        callableStatement.setString(3, customer.getEmail());
        callableStatement.setString(4, customer.getAddress());
//        callableStatement.registerOutParameter(4, Types.VARCHAR);
        callableStatement.executeUpdate();
    }

    private int generateID() throws SQLException{
        Connection connection = DBConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call getID()}");

        ResultSet resultSet = callableStatement.executeQuery();

        return resultSet.getInt(1);
    }
}

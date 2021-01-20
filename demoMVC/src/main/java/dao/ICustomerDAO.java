package dao;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    public void insertCustomer(Customer customer) throws SQLException;

    public Customer getCustomer(int id) throws SQLException;

    public List<Customer> getAllCustomer() throws SQLException;

    public boolean deleteCustomer(int id) throws SQLException;

    public boolean updateCustomer(Customer customer) throws SQLException;

    public void insertCustomerByProcedure(Customer customer) throws SQLException;
}

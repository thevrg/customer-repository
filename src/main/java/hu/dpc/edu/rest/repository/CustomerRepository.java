package hu.dpc.edu.rest.repository;

import hu.dpc.edu.rest.entity.Customer;

import java.util.List;

/**
 * Created by vrg on 09/06/16.
 */
public interface CustomerRepository {
    Long persist(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    String update(Customer customer, String version);

    Customer delete(Long id, String version);
}

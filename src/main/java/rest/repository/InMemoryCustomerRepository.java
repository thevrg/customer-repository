package rest.repository;

import hu.dpc.edu.rest.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by vrg on 08/06/16.
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
    private AtomicLong counter = new AtomicLong(0);
    private ConcurrentMap<Long, Customer> customers = new ConcurrentHashMap<>();

    public InMemoryCustomerRepository() {
    }

    @Override
    public Long persist(Customer customer) {
        Customer managed = new Customer(customer); //Saving the copy
        managed.setId(counter.incrementAndGet());
        customers.put(managed.getId(), managed);
        return managed.getId();
    }

    @Override
    public Customer findById(Long id) {
        final Customer managed = findExistingById(id);
        return new Customer(managed); //Returning with the copy
    }

    private Customer findExistingById(Long id) {
        final Customer managed = customers.get(id);
        if (managed == null) {
            throw new EntityNotFoundException(Customer.class, id);
        }
        return managed;
    }

    @Override
    public List<Customer> findAll() {
        return customers.values().stream()
                .map(mapped -> new Customer(mapped))
                .collect(Collectors.toList());
    }

    void doUpdate(Customer managed, Customer customer) {
        managed.setName(customer.getName());
        managed.setEmail(customer.getEmail());
    }

    Customer doDelete(Long id) {
        return customers.remove(id);
    }


    @Override
    public String update(Customer customer, String version) {
        Customer managed = findExistingByIdForModification(customer.getId(), version);
        doUpdate(managed, customer);
        return managed.getDigest();
    }

    @Override
    public Customer delete(Long id, String version) {
        Customer managed = findExistingByIdForModification(id, version);
        return doDelete(id);
    }

    Customer findExistingByIdForModification(Long id, String version) {
        final Customer managed = findExistingById(id);
        if (!managed.getDigest().equals(version)) {
            throw new OptimisticLockException(Customer.class, id);
        }
        return managed;
    }
}

package hu.dpc.edu.rest.repository;

import hu.dpc.edu.rest.entity.Customer;
import hu.dpc.edu.rest.entity.EntityBase;

import java.util.List;

/**
 * Created by vrg on 09/06/16.
 */
public interface CustomerRepository {

    /**
     * Persists the given customer. The customer's id property is ignored and a new id is generated,
     * which is exposed as the return value.
     *
     * @param customer The customer to be persisted.
     * @return The new id of the customer.
     */
    Long persist(Customer customer);

    /**
     * Finds a customer with the given id
     *
     * @param id The customer id
     * @return the found Customer
     * @throws EntityNotFoundException Thrown if there is no such a customer with the given id.
     */
    Customer findById(Long id) throws EntityNotFoundException;

    /**
     * Returns with all of the customer objects.
     *
     * @return
     */
    List<Customer> findAll();

    /**
     * Updates an existing customer. The method's goal is to replace the state of an internally stored existing customer
     * with the state passed in the customer parameter. The customer is identified by the passed customer's id,
     * therefore it is not possible to change the id of an existing customer. The last known version must be also passed
     * to this method. If there is an existing customer with the given id, but its digest value is not matching the
     * passed {@code version} parameter, then the method will throw an {@link OptimisticLockException}.
     *
     * @param customer The customer, which has the new state to be saved.
     * @param version  The last known digest of the customer.
     * @return The new version number (digest) of the successful update operation.
     * @throws EntityNotFoundException Thrown if there is no such a customer with the given id.
     * @throws OptimisticLockException Thrown if the passed version does not match the currently stored customer's
     *                                 digest. This usually means that meanwhile another process updated the given customer. This mechanism fights
     *                                 against accidentally overwriting previous changes without realizing that there was a previous change.
     * @see EntityBase#getDigest()
     */
    String update(Customer customer, String version) throws EntityNotFoundException, OptimisticLockException;

    /**
     * Deletes the customer with the given id and the given version.
     *
     * @param id
     * @param version
     * @return The removed customer.
     * @throws EntityNotFoundException Thrown if there is no such a customer with the given id.
     * @throws OptimisticLockException Thrown if the passed version does not match the currently stored customer's
     *                                 digest. This usually means that meanwhile another process updated the given customer. This mechanism fights
     *                                 against accidentally deleting a customer which is changed since we've last read the customer.
     */
    Customer delete(Long id, String version) throws EntityNotFoundException, OptimisticLockException;
}

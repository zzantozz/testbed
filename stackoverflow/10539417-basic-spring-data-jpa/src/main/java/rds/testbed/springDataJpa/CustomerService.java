package rds.testbed.springDataJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(Long customerId) {
        return repository.findById(customerId);
    }

    @Override
    @Transactional
    public void createCustomer(Customer customer) {
        repository.save(customer);
    }
}
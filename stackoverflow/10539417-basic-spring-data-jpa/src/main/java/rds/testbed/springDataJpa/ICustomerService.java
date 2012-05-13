package rds.testbed.springDataJpa;

public interface ICustomerService {
    public Customer getCustomer(Long customerId);
    void createCustomer(Customer customerToCreate);
}

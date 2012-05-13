package rds.testbed.springDataJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Resource
@Transactional(readOnly = true)
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findById(Long id);
}

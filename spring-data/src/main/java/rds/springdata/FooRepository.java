package rds.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* Created with IntelliJ IDEA.
* User: ryan
* Date: 2/25/13
* Time: 9:11 PM
*/
@Repository
public interface FooRepository extends JpaRepository<Foo, Long> {}

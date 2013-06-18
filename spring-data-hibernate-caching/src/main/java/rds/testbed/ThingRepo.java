package rds.testbed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;

public interface ThingRepo extends JpaRepository<Thing, Long> {
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Thing findByName(String name);
}

package repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
List<Customer> findByLastName(String lastName);
}

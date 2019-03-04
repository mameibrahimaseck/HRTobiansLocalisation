package be.tobania.localisation.repositories;

import be.tobania.localisation.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findCustomerByName(String name);

    @Modifying
    @Query(value = "truncate table CLIENT", nativeQuery = true)
    void truncateTable();

}

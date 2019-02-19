package be.tobania.localisation.repositories;

import be.tobania.localisation.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Modifying
    @Query(value = "truncate table TOBANIA_EMPLOYEE", nativeQuery = true)
    void truncateMyTable();
}

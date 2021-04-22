package com.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	public Optional<Employee> findByName(String name);

	
	
	@Query(value = "from Employee")
	public List<Employee> selectAllEmployee();

}

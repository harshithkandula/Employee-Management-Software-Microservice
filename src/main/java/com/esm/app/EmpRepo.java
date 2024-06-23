package com.esm.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esm.app.model.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long>{

}

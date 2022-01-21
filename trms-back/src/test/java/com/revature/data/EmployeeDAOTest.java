package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;
import com.revature.data.postgres.EmployeePostgres;

public class EmployeeDAOTest {
	private EmployeeDAO employeeDao = new EmployeePostgres();

	@Test
	public void create()  {
		Employee dataInput = new Employee();
		dataInput.setSupervisor(new Employee());
		int indexOutput = employeeDao.create(dataInput);
		assertNotEquals(0, indexOutput);
	}
	
	@Test
	public void getByIdWhenIdDoesNotExist() {
		int idInput = -1;
		Employee employeeOutput = employeeDao.getById(idInput);
		assertNull(employeeOutput);
	}
	 
	@Test
	public void getByIdWhenIdExists() {
		int idInput = 1;
		Employee employeeOutput = employeeDao.getById(idInput);
		assertEquals(1, employeeOutput);
	}
	
	@Test
	public void getAll() {
		Set<Employee> givenOutput = employeeDao.getAll();
		assertNotNull(givenOutput);
	}
	
	@Test
	public void getByUsername() {
		String usernameInput = "cinworth0";
		Employee employeeOutput = employeeDao.getByUsername(usernameInput);
		assertEquals(usernameInput, employeeOutput.getUsername());
	}
}

package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.postgres.ReimbursementPostgres;

public class ReimbursementDAOTest {
	private ReimbursementDAO reimbursementDao = new ReimbursementPostgres();
	
	@Test
	public void create() {
		Reimbursement dataInput = new Reimbursement();
		int indexOutput = reimbursementDao.create(dataInput);
		assertNotEquals(0, indexOutput);
	}
	
	@Test
	public void getById() {
		int idInput = 5;
		Reimbursement reimbursementOutput = reimbursementDao.getById(idInput);
		assertEquals(idInput, reimbursementOutput.getReqId());
	}
	
	@Test
	public void getAll() {
		Set<Reimbursement> givenOutput = reimbursementDao.getAll();
		assertNotNull(givenOutput);
	}
	
	@Test
	public void getByRequestor() {
		int empIdInput = 3;
		Employee dataInput = new Employee();
		dataInput.setEmpId(empIdInput);
		assertNotEquals(new HashSet<>(),reimbursementDao.getByRequestor(dataInput));
	}
	
	@Test
	public void getByStatus() {
		Status dataInput = new Status();
		assertNotEquals(new HashSet<>(), reimbursementDao.getByStatus(dataInput));
	}
	
	
}

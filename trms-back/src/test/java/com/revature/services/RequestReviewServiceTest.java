package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.ReimbursementDAO;

@ExtendWith(MockitoExtension.class)
public class RequestReviewServiceTest {
	
	@Mock
	private ReimbursementDAO reqDao;
	
	@InjectMocks
	private RequestReviewService revServ = new RequestReviewServiceImpl();
	
	@Test
	public void getPendingReimbursements() {
		Employee mockEmployee = new Employee();
		Set<Reimbursement> mockReimbursement = new HashSet<Reimbursement>();
		
		when(reqDao.getByRequestor(mockEmployee)).thenReturn(mockReimbursement);
		
		assertEquals(revServ.getPendingReimbursements(mockEmployee), mockReimbursement);
	}
}

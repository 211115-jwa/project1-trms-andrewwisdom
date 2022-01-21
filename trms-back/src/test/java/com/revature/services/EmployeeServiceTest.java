package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
	private EventTypeDAO eventTypeDao;
	private GradingFormatDAO gradFormatDao;
	private StatusDAO statusDao;
	private ReimbursementDAO reqDao;
	private CommentDAO commentDao;
	private EmployeeDAO empDao;
	
	@InjectMocks
	private EmployeeService empServ = new EmployeeServiceImpl();
	
	
	@Test
	public void getRequestOptions() {
		EventType mockEventType = new EventType();
		Set<Object> mockEventTypeSet = new HashSet<Object>();
		mockEventTypeSet.add(mockEventType);
		
		GradingFormat mockGradingFormat = new GradingFormat();
		Set<Object> mockGradingFormatSet = new HashSet<Object>();
		mockGradingFormatSet.add(mockGradingFormat);
		
		when(eventTypeDao.getAll()).thenReturn(mockEventTypeSet);
		when(gradFormatDao.getAll()).thenReturn(mockGradingFormatSet);
		
		Map<String,Set<Object>> mockRequestOptions = new HashMap<>();
		mockRequestOptions.put("eventTypes", mockEventTypeSet);
		mockRequestOptions.put("gradingFormats", mockGradingFormatSet);
		
		Map<String,Set<Object>> actualRequestOptions = empServ.getRequestOptions();
		
		assertEquals(actualRequestOptions, mockRequestOptions);
	}
	
	@Test
	public void submitReimbursementRequest() {
		Reimbursement mockReimbursement = new Reimbursement();
		Status mockStatus = new Status();
		
		when(reqDao.create(mockReimbursement)).thenReturn(1);
		when(statusDao.getById(1)).thenReturn(mockStatus);
		
		assertNotNull(empServ.submitReimbursementRequest(mockReimbursement));
	}
	
	@Test
	public void getReimbursementRequests() {
		Employee mockEmployee = new Employee();
		Set<Reimbursement> mockRequests = new HashSet<Reimbursement>();
		
		when(reqDao.getByRequestor(mockEmployee)).thenReturn(mockRequests);
		
		assertEquals(mockRequests, empServ.getReimbursementRequests(mockEmployee));
	}
	
	@Test
	public void getComments() {
		Set<Comment> mockComments = new HashSet<Comment>();
		Reimbursement mockRequest = new Reimbursement();
		Comment mockSingleComment = new Comment();
		Employee mockEmployee = new Employee();
		mockSingleComment.setApprover(mockEmployee);
		
		when(commentDao.getByRequestId(mockRequest.getReqId())).thenReturn(mockComments);
		when(empDao.getById(mockSingleComment.getApprover().getEmpId())).thenReturn(mockEmployee);
		
		assertEquals(empServ.getComments(mockRequest), mockComments);
	}
	
	@Test
	public void addComment() {
		Comment mockComment = new Comment();
		
		when(commentDao.create(mockComment)).thenReturn(1);
		
		assertEquals(1, empServ.addComment(mockComment));
	}
	
	@Test
	public void getEmployeeById() {
		Employee mockEmployee = new Employee();
		
		when(empDao.getById(0)).thenReturn(mockEmployee);
		
		assertEquals(mockEmployee, empServ.getEmployeeById(0));
	}
}

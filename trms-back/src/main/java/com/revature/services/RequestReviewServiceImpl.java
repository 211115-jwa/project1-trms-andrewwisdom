package com.revature.services;

import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.postgres.CommentPostgres;
import com.revature.data.postgres.ReimbursementPostgres;

public class RequestReviewServiceImpl implements RequestReviewService {

	private ReimbursementPostgres requestsDao = new ReimbursementPostgres();
	private CommentPostgres commentsDao = new CommentPostgres();
	
	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		return requestsDao.getByRequestor(approver);
	}

	@Override
	public void approveRequest(Reimbursement request) {
		requestsDao.update(request);
		// when the status_id field of the reimbursement object is updated then the request is considered approved
	}

	@Override
	public void rejectRequest(Reimbursement request) {
		requestsDao.update(request);
		// when the status_id field of the reimbursement object is updated then the request is considered rejected
	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		requestsDao.update(request);
		commentsDao.create(comment);
	}

}

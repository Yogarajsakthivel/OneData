package com.example.demo.service;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.BorrowModel;

public interface BorrowService {
	 List<BorrowModel> getAllBorrows();

	    BorrowModel getBorrowById(Long id);

	    void lendBook(Long memberId, Long bookId, Date borrowedDate, Date dueDate);

	    void returnBook(Long borrowId);

}

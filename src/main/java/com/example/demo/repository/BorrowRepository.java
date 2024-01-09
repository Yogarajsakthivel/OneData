package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.BorrowModel;

public interface BorrowRepository {

	    List<BorrowModel> findAll();

	    BorrowModel findById(Long id);

	    void borrowBook(Long memberId, Long bookId, Date borrowedDate, Date dueDate);

	    void returnBook(Long borrowId);

		void lendBook(Long memberId, Long bookId, Date borrowedDate, Date dueDate);
}

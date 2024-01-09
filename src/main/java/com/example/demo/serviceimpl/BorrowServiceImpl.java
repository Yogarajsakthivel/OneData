package com.example.demo.serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.BorrowModel;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.service.BorrowService;

public class BorrowServiceImpl implements BorrowService {
	 @Autowired
	    private BorrowRepository borrowRepository;

	 @SuppressWarnings("unused")
	@Autowired
	    private BookRepository bookRepository;

	    @Override
	    public List<BorrowModel> getAllBorrows() {
	        return borrowRepository.findAll();
	    }

	    @Override
	    public BorrowModel getBorrowById(Long id) {
	        return borrowRepository.findById(id);
	    }

	    @Override
	    public void lendBook(Long memberId, Long bookId, Date borrowedDate, Date dueDate) {
	        borrowRepository.lendBook(memberId, bookId, borrowedDate, dueDate);
	    }

	    @Override
	    public void returnBook(Long borrowId) {
	        borrowRepository.returnBook(borrowId);
	    }
}

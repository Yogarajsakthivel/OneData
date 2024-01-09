package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BorrowModel;
import com.example.demo.repository.BorrowRepository;

@RestController

@RequestMapping("/api/borrows")

public class BorrowController {


    @Autowired
    private BorrowRepository borrowRepository;

    @GetMapping
    public List<BorrowModel> getAllBorrows() 
    
    {
        return borrowRepository.findAll();
    }

    @GetMapping("/{id}")
    
    public ResponseEntity<BorrowModel> getBorrowById(@PathVariable Long id) {
        BorrowModel borrow = borrowRepository.findById(id);
        return ResponseEntity.ok().body(borrow);
    }

    @PostMapping
    public ResponseEntity<Void> borrowBook(
            @RequestParam Long memberId,
            @RequestParam Long bookId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date borrowedDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dueDate) {
        borrowRepository.borrowBook(memberId, bookId, borrowedDate, dueDate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        borrowRepository.returnBook(id);
        return ResponseEntity.ok().build();
    }
}

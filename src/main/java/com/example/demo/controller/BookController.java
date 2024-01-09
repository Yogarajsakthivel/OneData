package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.BookModel;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookModel> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long id) {
        BookModel bookModel = bookService.getBookById(id);
        return bookModel != null ?
                ResponseEntity.ok(bookModel) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addBook(@RequestBody BookModel bookModel) {
        bookService.addBook(bookModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody BookModel bookModel) {
        BookModel existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            bookService.updateBook(id, bookModel);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        BookModel existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<BookModel> searchBooks(@RequestParam String keyword) {
        return bookService.searchBooks(keyword);
    }
}

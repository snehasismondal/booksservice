package com.bookservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookservice.entity.Books;
import com.bookservice.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	BooksService booksservice;
	
	@GetMapping("/fetch/{id}")
	public Books fetchBookById(@PathVariable("id") String id ){
		
		return booksservice.fetchBookById(id).get();
		
		
	}
	@GetMapping("/fetch")
	public ResponseEntity<List<Books>> fetchAllBooks(){
		
		return ResponseEntity.ok().body(  booksservice.fetchAllBooks());
		
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addBooks(@RequestBody Books books) {
		
		Books resultBook=booksservice.addBooks(books);
		
		return ResponseEntity.ok().body("The Book id :"+resultBook.getIsbn()+" is added to the available list.");
		
		}
	
	@PutMapping("/edit")
	public String editBooks(@RequestBody Books books) {
		Books updateBook= booksservice.editbooks(books);
		return "The Book with ISBN no :"+updateBook.getIsbn()+" got updated.";
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") String id) {
		boolean response=booksservice.deleteBooks(id);
		if (response) {
		return "Book with ISBN No: "+id+" is deleted.";
		}else {
			return "OOPS..Book with ISBN No: "+id+" is Not deleted.";
		}
	}
	
	

}

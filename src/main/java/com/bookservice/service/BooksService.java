package com.bookservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.bookservice.entity.Books;
import com.bookservice.repository.BooksRepository;
import static com.bookservice.config.ActiveMQConfig.ISSUER_EVENT_QUEUE;
import static com.bookservice.config.ActiveMQConfig.ISSUER_FAILED_EVENT_QUEUE;
import static com.bookservice.config.ActiveMQConfig.ISSUER_CANCEL_EVENT_QUEUE;


@Service
public class BooksService {

	@Autowired
	BooksRepository booksrepo;
	@Autowired
	JmsTemplate jmstemplate;
	public Optional<Books> fetchBookById(String id) {
		// TODO Auto-generated method stub
		return booksrepo.findById(id);
	}
	
	public List<Books> fetchAllBooks(){
		return booksrepo.findAll();
	}

	public Books addBooks(Books books) {
		
		return booksrepo.save(books);
	}

	public Books editbooks(Books books)   {
		Books foundBook =booksrepo.findById(books.getIsbn()).get();
		
		foundBook.setIsbn(books.getIsbn());
		foundBook.setTitle(books.getTitle());
		foundBook.setPublishedDate(books.getPublishedDate());
		foundBook.setIssuedCopies(books.getIssuedCopies());
		foundBook.setTotalCopies(books.getTotalCopies());
		foundBook.setAuthor(books.getAuthor());
		booksrepo.save(foundBook);
		
		return foundBook;
	}

	public boolean deleteBooks(String id) {
		booksrepo.deleteById(id);
		
		try {
			booksrepo.findById(id);
			
			return true;
			
		}catch(Exception e){
			return false;
		}
	}
		
		
		@JmsListener(destination=ISSUER_EVENT_QUEUE)
		public void BookIssued (@Payload Books books) {
			try {
			Books foundBook =booksrepo.findById(books.getIsbn()).get();
			
			foundBook.setIsbn(books.getIsbn());
			
			foundBook.setIssuedCopies(foundBook.getIssuedCopies()+books.getIssuedCopies());
			
			booksrepo.save(foundBook);
			}
			catch(Exception ex) {
				jmstemplate.convertAndSend( ISSUER_FAILED_EVENT_QUEUE,books);
			}
			
	}
		
		
		
		@JmsListener(destination=ISSUER_CANCEL_EVENT_QUEUE)
		public void BookCancelled(@Payload Books books) {
			
			Books foundBook =booksrepo.findById(books.getIsbn()).get();
			
			foundBook.setIsbn(books.getIsbn());
			
			foundBook.setIssuedCopies(foundBook.getIssuedCopies()-books.getIssuedCopies());
			
			booksrepo.save(foundBook);
			
			
	}
		
		
	}
	
	

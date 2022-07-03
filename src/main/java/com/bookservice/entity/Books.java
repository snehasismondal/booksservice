package com.bookservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

@Table(name="books")
public class Books {
	@Id
	@Column(name="isbn")
	public String isbn;
	
	@Column(name="title")
	public String title;
	
	@Column(name="published_date")
	public String publishedDate;
	
	@Column(name="total_copies")
	public long totalCopies;
	
	@Column(name="issued_copies")
	public long issuedCopies;
	
	@Column(name="author")
	public String author;
	
}

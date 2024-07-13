package net.datasa.ex1;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="book")
public class BookEntity {
	@Id
	@Column(name = "ISBN", nullable = false, length = 20)
	private String ISBN;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "reg_date")
	private LocalDate reg_date;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "discount")
	private double discount;

}

package net.datasa.ex1;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookDTO {
	
	String ISBN;
	String title;
	String author;
	String publisher;
	LocalDate reg_date;
	
	int price;
	double discount;
	

}

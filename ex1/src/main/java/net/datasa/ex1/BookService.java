package net.datasa.ex1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	public void save(BookDTO dto) {
		BookEntity entity = new BookEntity();
				
		entity.setISBN(dto.getISBN());
		entity.setTitle(dto.getTitle());
		entity.setAuthor(dto.getAuthor());
		entity.setPublisher(dto.getPublisher());
		entity.setReg_date(dto.getReg_date());
		entity.setPrice(dto.getPrice());
		entity.setDiscount(dto.getDiscount());
		
		
		bookRepository.save(entity);
		
	}

	public BookDTO search(String ISBN) {
		// TODO Auto-generated method stub
		
	BookEntity entity = bookRepository.findById(ISBN).orElse(null);
		
		if (entity == null) return null;
		
		BookDTO dto = new BookDTO();
		
		dto.setISBN(entity.getISBN());	
		dto.setTitle(entity.getTitle());
		dto.setAuthor(entity.getAuthor());
		dto.setPublisher(entity.getPublisher());
		dto.setReg_date(entity.getReg_date());
		dto.setPrice(entity.getPrice());
		dto.setDiscount(entity.getDiscount());
		
		return dto;
	}
	
	public List<BookDTO> selectAll() {
		List<BookEntity> entityList = bookRepository.findAll();
		List<BookDTO> dtoList = new ArrayList<>();
		
		for(BookEntity entity : entityList) {
			BookDTO dto = new BookDTO();
			dto.setISBN(entity.getISBN());
			dto.setTitle(entity.getTitle());
			dto.setAuthor(entity.getAuthor());
			dto.setPublisher(entity.getPublisher());
			dto.setReg_date(entity.getReg_date());
			dto.setPrice(entity.getPrice());
			dto.setDiscount(entity.getDiscount());
			
			dtoList.add(dto);
		}
		return dtoList;
	}

	public boolean delete(String ISBN) {
		
		boolean result = bookRepository.existsById(ISBN);
		// id가 존재하는지 여부만 조회
		if (result) {
			bookRepository.deleteById(ISBN);
			//해당되는 id가 없을 경우 아무동작x
		}
		
		return result;
		
	}
	
	


}

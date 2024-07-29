package net.datasa.web4.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.datasa.web4.domain.dto.GuestbookDTO;
import net.datasa.web4.domain.entity.GuestbookEntity;
import net.datasa.web4.repository.GuestbookRepository;

@Service
@RequiredArgsConstructor
public class GuestbookService {

	private final GuestbookRepository guestbookRepository;

	ModelMapper modelMapper = new ModelMapper();

	public void test() {
		/*
		 * GuestbookEntity entity = new GuestbookEntity(); entity.setName("조소이");
		 * entity.setPassword("1234"); entity.setMessage("test");
		 */

		GuestbookEntity entity = GuestbookEntity.builder().name("aaa").password("111").message("글내용").build();

		guestbookRepository.save(entity);

		// new GuestbookEntity(0, "aaa", "111", "글내용", LocalDateTime.now());

	}

	public void save(GuestbookDTO dto) {

		/*
		 * GuestbookEntity entity = new GuestbookEntity();
		 * 
		 * entity.setName(dto.getName()); entity.setPassword(dto.getPassword());
		 * entity.setMessage(dto.getMessage());
		 */

		
		  GuestbookEntity entity = GuestbookEntity.builder() 
				  .name(dto.getName())
				  .password(dto.getPassword()) 
				  .message(dto.getMessage()) 
				  .build();
		
		// GuestbookEntity entity = modelMapper.map(dto, GuestbookEntity.class);

		guestbookRepository.save(entity);
	}

	public List<GuestbookDTO> list() {
		//select * from guestbook order by num desc;
		
		Sort sort = Sort.by(Sort.Direction.DESC, "num");
		
		/*
		 * 정렬 조건이 여러개 일때 
		 * Sort sort = Sort.by( 
		 * 			   Sort.Order.desc("inputdate"),
		 * 			   Sort.Order.desc("num"),
		 * 			   Sort.Order.desc("name")
		 * );
		 */
		
		List<GuestbookEntity> entityList = guestbookRepository.findAll(sort);
		List<GuestbookDTO> dtoList = new ArrayList<>();
		
		for(GuestbookEntity entity : entityList) {
			GuestbookDTO dto = new GuestbookDTO();
			dto.setNum(entity.getNum());
			dto.setName(entity.getName());
			dto.setPassword(entity.getPassword());
			dto.setMessage(entity.getMessage());
			dto.setInputdate(entity.getInputdate());
			
			/*
			 * GuestbookDTO dto = new GuestbookDTO.builder()
			 * 	.num(entity.getNum())
			 * 
			 * 	.build();
			 * 
			*/
			 
			dtoList.add(dto);
		}
		return dtoList;
		
		
		//ArrayList 생성
		//반복문으로 GuestbookEntity객체의 값을 GuestbookDTO 객체 생성해서 저장
		//ArrayList에 생성된 DTO저장
		
		//ArrayList 리턴
	}

	public void delete(Integer num, String password) throws EntityNotFoundException {
		//RuntimeException의 하위 타입이라면 throw~이하 생략해도 잘 실행됨
		
		//전달된 번호로 글 정보 조회 (옵셔널 객체로 리턴됨, 해당내용이 없을 경우 null)
		//글이 없으면 예외 발생 
		GuestbookEntity entity = guestbookRepository.findById(num)
				.orElseThrow(()-> new EntityNotFoundException("해당 글이 없습니다"));
		//메서드를 호출한 곳으로 에러를 떠넘김
		
		//글이 있으면 비밀번호 비교
		if(!password.equals(entity.getPassword())) {
			throw new RuntimeException("비밀번호가 틀립니다");
		}
		
		//비밀번호 틀리면 예외
		
		//맞으면 글 삭제
		//[영속성]레포지토리를 통해 가져온 엔티티의 경우(findbyid) 
		//테이블안의 한 행과 연결되어 있어서 삭제하면 테이블에서도 행이 지워짐
		guestbookRepository.delete(entity);
		
		
		//비밀번호가 틀렸을 때 처리방법
		//1)리턴 2)지우면 true, 못지우면 flase 3)예외처리[베스트]  
		  
	}

	public GuestbookDTO select(Integer num, String password) {
		
		GuestbookEntity entity = guestbookRepository.findById(num)
				.orElseThrow(()-> new EntityNotFoundException("해당 글이 없습니다"));
		//메서드를 호출한 곳으로 에러를 떠넘김
		
		//글이 있으면 비밀번호 비교
		if(!password.equals(entity.getPassword())) {
			throw new RuntimeException("비밀번호가 틀립니다");
		}
		
		
		GuestbookDTO dto = new GuestbookDTO();
		dto.setNum(entity.getNum());
		dto.setName(entity.getName());
		dto.setMessage(entity.getMessage());
		dto.setInputdate(entity.getInputdate());
		
		return dto;
	}

	public void update(GuestbookDTO dto) {
		// TODO Auto-generated method stub
		
		GuestbookEntity entity = guestbookRepository.findById(dto.getNum())
				.orElseThrow(()-> new EntityNotFoundException("해당 글이 없습니다."));
		
		entity.setName(dto.getName());
	    entity.setMessage(dto.getMessage());
	    entity.setInputdate(dto.getInputdate()); // inputdate 업데이트
	  
		guestbookRepository.save(entity);
	}
}

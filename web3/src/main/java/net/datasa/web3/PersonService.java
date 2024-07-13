package net.datasa.web3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 정보 관련 처리를 하는 서비스 클래스
 * 비즈니스 로직
*/

@Service
@RequiredArgsConstructor
//final 혹은 @NotNull이 붙은 필드의 생성자를 자동으로 만들어줌
@Transactional
// 선언적 트랜잭션 처리 
public class PersonService {
	
	private final PersonRepository personRepository;
	
/**
1) DB연결 코드
2) String sql = "insert into person values ("+id+", "+name+");"";
3) DB로 sql 명령 전송	 
*/
	
	//객체를 생성해서 DB에 저장
	public void test() {
		PersonEntity entity = new PersonEntity();
		entity.setId("abced");
		entity.setName("홍길동");
		entity.setAge(30);
		
		entity.setId("jjj");
		entity.setName("김영희");
		entity.setAge(10);
		
		personRepository.save(entity);
	}

	/**
	 * 전달받은 PersonDTO를 데이터베이스에 저장
	 * @param dto 
	 */
	public void save(PersonDTO dto) {
	// TODO Auto-generated method stub
	// DB에 저장하려면 엔티티로 전달받은 값을 옮겨야 함
	// ModelMapper 사용해 자동화가능
	// 예외발생할 경우, 컨트롤러에서 정의하는 것을 권장
		PersonEntity entity = new PersonEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());	
	
		personRepository.save(entity);
		// save : jpa에서 정해진 method
		// 자바쪽에서 보내는 것은 자동 commit
	}

	/**
	 * 
	 * @param id 조회할 아이디(selectForm.html)
	 * @return 데이터베이스에서 조회할 아이디와 일치하는 
	 *         회원정보가 있다면 dto, 없다면 false 
	 */
	public PersonDTO select(String id) {
		
		PersonEntity entity = personRepository.findById(id).orElse(null);
		// null값 처리안하는 경우 .get
		//코드상에서 null에 대한 처리를 어떻게 할건지 강요
		
		if (entity == null) return null;
		
		PersonDTO dto = new PersonDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAge(entity.getAge());
				
		return dto;
	}

	/**
	 * 
	 * @param id 삭제할 아이디
	 * @return 삭제할 아이디가 존재하는 경우 ture, 
	 *         deleteById로 DB에서 해당 컬럼 삭제
	 *         존재하지않는 경우 false
	 */
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		boolean result = personRepository.existsById(id);
		// id가 존재하는지 여부만 조회
		
		if (result) {
			personRepository.deleteById(id);
			//해당되는 id가 없을 경우 아무동작x
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return DB의 모든 데이터를 list타입(entity)으로 저장
	 *         entity -> dto로 저장
	 */
	public List<PersonDTO> selectAll() {
		List<PersonEntity> entityList = personRepository.findAll();
		List<PersonDTO> dtoList = new ArrayList<>();
		
		for(PersonEntity entity : entityList) { 
			PersonDTO dto = new PersonDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setAge(entity.getAge());
			
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	/**
	 * 사용자 정보를 ID기준으로 수정
	 * @param dto 수정할 정보
	 */
	public void update(PersonDTO dto) {
		// TODO Auto-generated method stub
		PersonEntity entity = personRepository.findById(dto.getId())
				.orElseThrow(()-> new EntityNotFoundException("없는 ID"));
	// dto에 있는 수정할 정보를 entity에 세팅
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		
	// entity에 저장
		personRepository.save(entity);
	
	}


}

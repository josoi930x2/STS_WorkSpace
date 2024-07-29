package net.datasa.web5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web5.domain.dto.MemberDTO;
import net.datasa.web5.domain.entity.MemberEntity;
import net.datasa.web5.repository.MemberRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional   //하나라도 예외가 생기면 전부 롤백됨
public class MemberService {
	
	//회원정보 DB작업
	private final MemberRepository memberRepository;
	
	//암호화
	private final BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * 가입처리
	 * @param dto 가입 데이터
	 */
	public void join(MemberDTO dto) {
		
		//new~, builder()로 생성한 객체는 DB와 상관없음 ->save한 이후부터 DB에 반영
		//dto의 값을 읽어서 entity 생성
		MemberEntity entity = MemberEntity.builder()
				.memberId(dto.getMemberId())
				.memberPassword(passwordEncoder.encode(dto.getMemberPassword()))
				.memberName(dto.getMemberName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.address(dto.getAddress())
				.enabled(true)
				.rolename("ROLE_USER")
				.build();
		
		memberRepository.save(entity);
	}


	public List<MemberDTO> list() {
		// TODO Auto-generated method stub
		
		List<MemberEntity> entityList = memberRepository.findAll();
		List<MemberDTO> dtoList = new ArrayList<>();
		
		for (MemberEntity entity : entityList) {
			MemberDTO dto = new MemberDTO();
			dto.setMemberId(entity.getMemberId());
			dto.setMemberPassword(passwordEncoder.encode(entity.getMemberPassword()));
			dto.setMemberName(entity.getMemberName());
			dto.setEmail(entity.getEmail());
			dto.setPhone(entity.getPhone());
			dto.setAddress(entity.getAddress());
			dto.setEnabled(entity.getEnabled());
			dto.setRolename(entity.getRolename());
			
			dtoList.add(dto);
		}
		return dtoList;
	}

	//id를 조회하여 신규가입자가 써도 되는 아이디인지 확인하는 메소드
	public boolean findId(String searchId) {
		// TODO Auto-generated method stub
		/* 아이디를 가져오지 않아도 되므로 isPresent 존재여부만 확인
		 * MemberEntity entity = memberRepository.findById(searchId)
		 */
		//전달받은 아이디를 데이터베이스에서 조회
		if (memberRepository.findById(searchId).isPresent()) {
			//결과가 있으면 false를 리턴
			return false;
		}
	
		//결과가 없으면 true를 리턴
		else { return true;
		}

	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public MemberDTO getMember(String username) {
		// TODO Auto-generated method stub
		
		//DB에서 아이디로 회원정보 조회해서 Entity로 리턴
		//검색결과가 없으면 예외발생
		//없으면 MemberDTO 객체 생성해서 엔티티의 값을 대입
		//생성된 MemberDTO 객체를 리턴
		
		MemberEntity entity = memberRepository.findById(username)
				.orElseThrow(()-> new EntityNotFoundException(username + "해당 정보가 없습니다"));
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(entity.getMemberId());
		memberDTO.setMemberPassword(entity.getMemberPassword());
		memberDTO.setMemberName(entity.getMemberName());
		memberDTO.setEmail(entity.getEmail());
		memberDTO.setPhone(entity.getPhone());
		memberDTO.setAddress(entity.getAddress());
		memberDTO.setEnabled(entity.getEnabled());
		memberDTO.setRolename(entity.getRolename());
		
		
		/*
		 MemberDTO dto = MemberDTO.builder()
				 .memberId(entity.getMemberId())
				 .memberPassword(entity.getMemberPassword())
				 .build();
		 return dto
		 
		 */
		
		return memberDTO;
	}


	public void update(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
		//entity : 영속적인 객체 
		MemberEntity entity = memberRepository.findById(memberDTO.getMemberId())
				.orElseThrow(()-> new EntityNotFoundException(memberDTO.getMemberId() + "해당 정보가 없습니다"));
		
		
		//memberDTO의 비밀번호가 비어있지 않으면 비번도 수정 (변경안한 경우 자동으로 기존값 유지됨)
		 if (memberDTO.getMemberPassword() != null && !memberDTO.getMemberPassword().isEmpty()) {
		        entity.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
		    }
		 
		
		//나머지 이름, 이메일, 전화, 주소는 무조건 대입
		entity.setMemberName(memberDTO.getMemberName());
		entity.setEmail(memberDTO.getEmail());
		entity.setPhone(memberDTO.getPhone());
		entity.setAddress(memberDTO.getAddress());
		
		
		//rollName과 같이 값을 고치지 않은 것은 원래 있던 상태 그대로 저장됨
		//DB에 저장
		
		memberRepository.save(entity);
	}
	
	
	
	
	
	
}

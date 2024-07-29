package net.datasa.web5.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원정보 DTO
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	String memberId;			//사용자
	String memberPassword;		//비밀번호
	String memberName;			//사용자 이름
	String email;				//이메일
	String phone;				//전화번호
	String address;				//주소
	Boolean enabled;			//
	String rolename;
	
}

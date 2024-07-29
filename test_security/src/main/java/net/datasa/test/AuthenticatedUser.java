package net.datasa.test;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserDetails(인증 정보) AuthenticatedUser
// 마우스오른쪽 -> source -> override :총 7개

//사용자 인증 정보 객체

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthenticatedUser implements UserDetails {

	//객체가 직렬화 될때, 원본 객체와 같다는것을 증명하기 위함
	private static final long serialVersionUID = -2757275378661085190L;
	//인증관련 정보
	String id;
	String password;
	String name;
	String roleName;
	boolean enabled;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(roleName));
	}
	
	//비밀번호 리턴
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return id;
	}
	
	//이하 3개 메서드 전부 true를 리턴해야 로그인 성공
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//상황에 따라 허용여부
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}

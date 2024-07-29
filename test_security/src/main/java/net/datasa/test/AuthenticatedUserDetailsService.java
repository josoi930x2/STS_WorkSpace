package net.datasa.test;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticatedUserDetailsService implements UserDetailsService {
	
	//Configuration에서 리턴값 가져오기
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub.32
		
		// 전달받은 사용자 아이디(username)으로 DB에서 사용자 정보 조회
		// 아이디가 없으면 예외
		// 있으면 조회된 정보로 UserDetails 객체 생성해서 리턴
		
		AuthenticatedUser user = AuthenticatedUser.builder()
				   .id("abc")
				   .password(passwordEncoder.encode("123"))
				   .name("홍길동")
				   .roleName("ROLE_USER") //USER ADMIN
				   .enabled(true)
				   .build();
				log.debug("인증정보 :{}" , user);
		
				return user;
	}

}

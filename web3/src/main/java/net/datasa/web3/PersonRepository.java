package net.datasa.web3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository 
extends JpaRepository<PersonEntity, String> {
// <PersonEntity, String> 엔티티명, pk키 타입
// interface의 하위클래스의 기능을 쓰고있는 것
	List<PersonEntity> findAll(); 
}

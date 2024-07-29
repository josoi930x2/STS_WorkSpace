/**
 * 
 */
package net.datasa.web5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.datasa.web5.domain.entity.MemberEntity;

/**
 * 
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

	//delete 메서드 실행시 바로 DB에서 삭제x => 캐시(메모리)상에서만 반영
	
}

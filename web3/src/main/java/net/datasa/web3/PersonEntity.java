package net.datasa.web3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * DB의 테이블과 매핑되는 클래스
 */

@Data
@Entity
@Table(name="person")
public class PersonEntity {
	@Id
	// pk : 기본키
	@Column(name = "id", nullable = false, length = 30)
	// @Column 테이블을 이미 만들어놨기 때문에 생략가능
	private String id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "age")
	private Integer age;
}

package net.datasa.web4.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="guestbook")
@EntityListeners(AuditingEntityListener.class)
public class GuestbookEntity {
	//글번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num")
	private Integer num;
	
	//board_num -> boardNum
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	
	//가장 먼저 실행되는 Web5Application에 @EnableJpaAuditing 
	//글내용
	@Column(name = "message", nullable = false, columnDefinition = "text")
	private String message;
	
	//작성시간 +LastModifiedDate
	@CreatedDate
	@Column(name = "inputdate")
	private LocalDateTime inputdate;
	
}

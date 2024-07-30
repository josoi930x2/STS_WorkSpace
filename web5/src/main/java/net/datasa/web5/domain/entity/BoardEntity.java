package net.datasa.web5.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
@Table(name="web5_board")
@Entity
public class BoardEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_num")
	private Integer boardNum;
	
	//@OneToMany
	@Column(name = "member_id", length = 30)
	private String memberId;
	
	@Column(name = "title", nullable = false, length = 1000)
	private String title;
	
	@Column(name = "contents", nullable = false, columnDefinition = "text")
	private String contents;
	
	//조회수
	@Column(name = "view_count", columnDefinition = "integer default 0")
	private Integer veiwCount = 0;
	//그냥 entity 객체를 생성해도 조회수가 0으로 되도록
	
	//추천수
	@Column(name = "like_count", columnDefinition = "integer default 0")
	private Integer likeCount = 0;
	
	//첨부파일의 원래 이름
	@Column(name = "original_name", length = 300)
	private String originalName;
	
	//첨부파일의 저장된 이름
	@Column(name = "file_name", length = 100)
	private String fileName;
	
	@CreatedDate
	@Column(name = "create_date", columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@Column(name = "update_date", columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime updateDate;

}

/*
 [web5 프로젝트에 글쓰기 기능]
 1. web5_board 테이블 생성
 2. BoardEntity, BoardRepository, BoardService, BoardController 정의
 3. board/write 경로로 요청하면 글쓰기 폼 
 4. 폼에 입력한 제목, 내용을 BoardDTO 타입으로 받아서 로그인한 아이디 추가하여 서비스로 전달
 5. 서비스에서 BoardEntity 생성하여 리포지토리의 메소드 호출하여 저장
 6. board/list로 리다이렉트
 
 */



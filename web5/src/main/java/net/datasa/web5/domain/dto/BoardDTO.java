package net.datasa.web5.domain.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	
	private Integer boardNum;
	private String memberId;
	private String title;
	private String contents;
	private Integer veiwCount;
	private Integer likeCount;
	private String originalName;
	private String fileName;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

}

package net.datasa.web4.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookDTO {
	
	Integer num;
	String name;
	String password;
	String message;
	LocalDateTime inputdate;

}

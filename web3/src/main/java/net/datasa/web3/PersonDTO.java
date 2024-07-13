package net.datasa.web3;

import lombok.Data;

@Data
public class PersonDTO {
	
	String id;
	String name;
	int age;
	//모든 변수를 다 DTO에 입력해야하는 것은 아님
}

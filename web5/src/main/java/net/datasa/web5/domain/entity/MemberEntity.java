package net.datasa.web5.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name="web5_member")
@Entity
public class MemberEntity {
	
	@Id
	@Column(name = "member_id", length = 30)
	private String memberId;
	
	@Column(name = "member_password", nullable = false, length = 100)
	private String memberPassword;
	
	@Column(name = "member_name", nullable = false, length = 30)
	private String memberName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "enabled", columnDefinition="tinyint(1) default 1 check(enabled in (1, 0))")
	private Boolean enabled;
	
	@Column(name = "rolename", columnDefinition="default 'ROLE_USER' check(rolename in ('ROLE_USER', 'ROLE_ADMIN'))")
	private String rolename;

}

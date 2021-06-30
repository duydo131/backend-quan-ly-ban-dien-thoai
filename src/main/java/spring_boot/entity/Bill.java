package spring_boot.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Bill extends BaseEntity{
	
	@Column
	private UUID code;

	public UUID getCode() {
		return code;
	}

	public void setCode(UUID code) {
		this.code = code;
	}
}

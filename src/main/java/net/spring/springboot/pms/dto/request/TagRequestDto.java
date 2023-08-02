package net.spring.springboot.pms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TagRequestDto {
	@NotBlank(message = "Tag name is mandatory")
	@Size(min = 2, message = "Tag name must have at least 2 characters")
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public TagRequestDto() {
		super();
	}

	@Override
	public String toString() {
		return "TagRequestDto [name=" + name + "]";
	}
}

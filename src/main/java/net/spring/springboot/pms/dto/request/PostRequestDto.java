package net.spring.springboot.pms.dto.request;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequestDto {
	@NotBlank(message = "Title is mandatory")
	@Size(min = 2, message = "Title must have at least 2 characters")
	private String title;

	
	@NotBlank(message = "Content is mandatory")
	@Size(min = 2, message = "Content must have at least 2 characters")
	private String content;

	private Boolean active;

	private List<Long> tagIds = new ArrayList<Long>();

	public PostRequestDto() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Long> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<Long> tagIds) {
		this.tagIds = tagIds;
	}

}

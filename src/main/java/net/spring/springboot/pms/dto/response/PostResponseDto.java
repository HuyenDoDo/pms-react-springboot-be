package net.spring.springboot.pms.dto.response;

import java.util.ArrayList;
import java.util.List;


public class PostResponseDto {
	private Long id;

	private String title;

	private String content;

	private Boolean active;

	private List<TagResponseDto> tags = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<TagResponseDto> getTags() {
		return tags;
	}

	public void setTags(List<TagResponseDto> tags) {
		this.tags = tags;
	}

	public PostResponseDto() {
		super();
	}

}

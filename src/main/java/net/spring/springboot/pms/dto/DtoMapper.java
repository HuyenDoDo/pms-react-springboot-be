package net.spring.springboot.pms.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.spring.springboot.pms.dto.response.PostResponseDto;
import net.spring.springboot.pms.dto.response.TagResponseDto;
import net.spring.springboot.pms.model.Post;
import net.spring.springboot.pms.model.Tag;

public class DtoMapper {
	public static TagResponseDto tagToDto(Tag tag) {
		TagResponseDto dto = new TagResponseDto();
		dto.setId(tag.getId());
		dto.setName(tag.getName());
		return dto;
	}

	public static List<TagResponseDto> tagsToDtos(List<Tag> tags) {
		List<TagResponseDto> dtos = new ArrayList<TagResponseDto>();
		tags.forEach((tag) -> dtos.add(tagToDto(tag)));
		return dtos;
	}

	public static PostResponseDto postToDto(Post post) {
		PostResponseDto dto = new PostResponseDto();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		dto.setActive(post.getActive());

		List<TagResponseDto> tags = post.getTags().stream().map(tag -> tagToDto(tag)).toList();
		dto.setTags(tags);
		return dto;
	}

	public static List<PostResponseDto> postsToDtos(List<Post> posts) {
		return posts.stream().map(post -> postToDto(post)).collect(Collectors.toList());
	}
}

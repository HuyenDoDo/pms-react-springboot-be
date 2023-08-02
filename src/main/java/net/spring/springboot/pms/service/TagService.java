package net.spring.springboot.pms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.spring.springboot.pms.dto.DtoMapper;
import net.spring.springboot.pms.dto.request.TagRequestDto;
import net.spring.springboot.pms.dto.response.TagPaginationDto;
import net.spring.springboot.pms.dto.response.TagResponseDto;
import net.spring.springboot.pms.exception.ResourceNotFoundException;
import net.spring.springboot.pms.model.Tag;
import net.spring.springboot.pms.repository.TagRepository;

@Service
public class TagService {
	private TagRepository repository;

	public TagService(TagRepository repository) {
		super();
		this.repository = repository;
	}

	public List<Tag> getTags() {
		return repository.findAll();
	}
	
	public List<Tag> getTagsByIds(List<Long> ids) {
		return repository.findAllById(ids);
	}

	public Tag getTagById(Long id) {
		Tag existingTag = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found tag with id: " + id));
		return existingTag;
	}

	public Tag createTag(TagRequestDto dto) {
		Tag tag = new Tag();
		tag.setName(dto.getName());
		return repository.save(tag);
	}

	public Tag editTag(Long id, TagRequestDto dto) {
		Tag existingTag = this.getTagById(id);
		existingTag.setName(dto.getName());
		repository.save(existingTag);
		return existingTag;
	}
	
	public void deleteTag(Long id) {
		Tag existingTag = this.getTagById(id);
		repository.delete(existingTag);
	}
	
	public TagPaginationDto getTagPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending()
						: Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Tag> paginatedTags = repository.findAll(pageable);
		
		List<Tag> listOfPaginatedTags = paginatedTags.getContent();
		
		List<TagResponseDto> content = listOfPaginatedTags.stream().map(
				tag -> DtoMapper.tagToDto(tag)).collect(Collectors.toList());
		
		TagPaginationDto tagPaginationDto = new TagPaginationDto();
		tagPaginationDto.setContent(content);
		tagPaginationDto.setPageNo(paginatedTags.getNumber());
		tagPaginationDto.setPageSize(paginatedTags.getSize());
		tagPaginationDto.setTotalElements(paginatedTags.getTotalElements());
		tagPaginationDto.setTotalPages(paginatedTags.getTotalPages());
		tagPaginationDto.setLast(paginatedTags.isLast());
		
		return tagPaginationDto;
	}
}

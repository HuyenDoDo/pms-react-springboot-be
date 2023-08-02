package net.spring.springboot.pms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.spring.springboot.pms.dto.DtoMapper;
import net.spring.springboot.pms.dto.request.TagRequestDto;
import net.spring.springboot.pms.dto.response.TagPaginationDto;
import net.spring.springboot.pms.dto.response.TagResponseDto;
import net.spring.springboot.pms.service.TagService;
import net.spring.springboot.pms.utils.AppConstants;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/tags")
public class TagController {
	private TagService service;

	public TagController(TagService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TagResponseDto> getTagById(@PathVariable("id") Long id){
		return new ResponseEntity<>(
				DtoMapper.tagToDto(service.getTagById(id)),
				HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<TagResponseDto>> getTags() {
		return new ResponseEntity<>(
				DtoMapper.tagsToDtos(service.getTags()),
				HttpStatus.OK);
	}
	
	@GetMapping("pagination")
	public ResponseEntity<TagPaginationDto> getTagPagination(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		
		TagPaginationDto tagPaginationDto = service.getTagPagination(pageNo, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(
				tagPaginationDto,
				HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TagResponseDto> createTag(@Valid @RequestBody TagRequestDto dto) {
		return new ResponseEntity<>(
				DtoMapper.tagToDto(service.createTag(dto)),
				HttpStatus.CREATED); 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TagResponseDto> editTag(
			@PathVariable("id") Long id, 
			@Valid @RequestBody TagRequestDto dto) {
		
		return new ResponseEntity<>(
				DtoMapper.tagToDto(service.editTag(id, dto)),
				HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTag(@PathVariable("id") Long id) {
		service.deleteTag(id);
		return new ResponseEntity<>(
				"Tag has been deleted",
				HttpStatus.OK); 
	}
}

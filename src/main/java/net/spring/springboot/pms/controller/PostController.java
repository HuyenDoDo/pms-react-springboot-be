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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.spring.springboot.pms.dto.DtoMapper;
import net.spring.springboot.pms.dto.request.PostRequestDto;
import net.spring.springboot.pms.dto.response.PostResponseDto;
import net.spring.springboot.pms.service.PostService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostController {
	private PostService service;

	public PostController(PostService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable("id") Long id){
		return new ResponseEntity<>(
				DtoMapper.postToDto(service.getPostById(id)),
				HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PostResponseDto>> getPosts() {
		System.out.println("controller 43 ");
		return new ResponseEntity<>(
				DtoMapper.postsToDtos(service.getPosts()),
				HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto dto) {
		return new ResponseEntity<>(
				DtoMapper.postToDto(service.createPost(dto)),
				HttpStatus.CREATED); 
	}
	
	@PutMapping("{id}")
	public ResponseEntity<PostResponseDto> editPost(
			@PathVariable("id") Long id, 
			@Valid @RequestBody PostRequestDto dto) {
		
		return new ResponseEntity<>(
				DtoMapper.postToDto(service.editPost(id, dto)),
				HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
		service.deletePost(id);
		return new ResponseEntity<>(
				"Post has been deleted",
				HttpStatus.OK); 
	}
}

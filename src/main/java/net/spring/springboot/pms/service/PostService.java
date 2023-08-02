package net.spring.springboot.pms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.spring.springboot.pms.dto.request.PostRequestDto;
import net.spring.springboot.pms.exception.ResourceNotFoundException;
import net.spring.springboot.pms.model.Post;
import net.spring.springboot.pms.model.Tag;
import net.spring.springboot.pms.repository.PostRepository;

@Service
public class PostService {
	private PostRepository repository;
	private TagService tagService;

	public PostService(PostRepository repository, TagService tagService) {
		super();
		this.repository = repository;
		this.tagService = tagService;
	}

	public List<Post> getPosts() {
		return repository.findAll();
	}

	public List<Post> getPostsByIds(List<Long> ids) {
		return repository.findAllById(ids);
	}

	public Post getPostById(Long id) {
		Post existingPost = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Post with id: " + id));
		return existingPost;
	}

	public Post createPost(PostRequestDto dto) {
		if (dto.getTagIds().size() == 0)
			throw new IllegalArgumentException("Post must have atleast 1 tag");
		
		Post post = new Post();
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setActive(dto.getActive());

		List<Tag> tagList = tagService.getTagsByIds(dto.getTagIds());
		
		if(tagList.size() == 0) throw new IllegalArgumentException("Tags not found");
		
		Set<Tag> tagSet = new HashSet<Tag>(tagList);
		post.setTags(tagSet);
		
		return repository.save(post);
	}

	@Transactional
	public Post editPost(Long id, PostRequestDto dto) {
		if (dto.getTagIds().size() <= 0)
			throw new IllegalArgumentException("Post must have atleast 1 tag");
		
		Post existingPost = this.getPostById(id);

		existingPost.setTitle(dto.getTitle());
		existingPost.setContent(dto.getContent());
		existingPost.setActive(dto.getActive());
		
		Set<Tag> tags = new HashSet<Tag>(tagService.getTagsByIds(dto.getTagIds()));
		existingPost.setTags(tags);

		repository.save(existingPost);
		return existingPost;
	}

	public void deletePost(Long id) {
		Post existingPost = this.getPostById(id);
		repository.delete(existingPost);
	}

}

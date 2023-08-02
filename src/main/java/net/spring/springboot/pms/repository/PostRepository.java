package net.spring.springboot.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.spring.springboot.pms.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}

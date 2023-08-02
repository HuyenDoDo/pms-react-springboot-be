package net.spring.springboot.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.spring.springboot.pms.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}

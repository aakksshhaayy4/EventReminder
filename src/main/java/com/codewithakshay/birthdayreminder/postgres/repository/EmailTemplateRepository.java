package com.codewithakshay.birthdayreminder.postgres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithakshay.birthdayreminder.postgres.model.EmailTemplates;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplates, Long> {
	
	public List<EmailTemplates> findByUserId(Long userId);

}

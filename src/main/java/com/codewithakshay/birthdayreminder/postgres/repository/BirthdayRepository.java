package com.codewithakshay.birthdayreminder.postgres.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codewithakshay.birthdayreminder.postgres.model.Birthday;

@Repository
public interface BirthdayRepository extends JpaRepository<Birthday, Long> {

//	@Query("select * from Birthday where date like %:date%")
	public List<Birthday> findByDateLike(String date);

	public List<Birthday> findByUserId(Long userId);

	public List<Birthday> findByUserIdAndBirthdayId(Long userId, Long birthdayId);

	public int deleteByUserIdAndBirthdayId(Long userId, Long birthdayId);

}

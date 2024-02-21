package com.project.spring6withSpringBoot3.data.repository;

import com.project.spring6withSpringBoot3.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository: Why not added ??
public interface GuestRepository extends JpaRepository<Guest, Long> {

}

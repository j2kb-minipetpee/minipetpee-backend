package com.j2kb.minipetpee.api.star.repository;


import com.j2kb.minipetpee.api.star.domain.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StarRepository extends JpaRepository<Star, Long> {
}

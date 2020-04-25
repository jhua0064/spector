package com.monash.spector.service.repository;

import com.monash.spector.model.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide,Integer> {
}

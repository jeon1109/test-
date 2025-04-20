package com.example.jeon.repository;

import com.example.jeon.dto.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

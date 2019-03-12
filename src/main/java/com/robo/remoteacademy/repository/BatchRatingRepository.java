package com.robo.remoteacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.robo.remoteacademy.model.BatchRating;

public interface BatchRatingRepository extends JpaRepository<BatchRating, String>
{
}
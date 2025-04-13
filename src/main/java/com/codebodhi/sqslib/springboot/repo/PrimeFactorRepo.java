package com.codebodhi.sqslib.springboot.repo;

import com.codebodhi.sqslib.springboot.entity.PrimeFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimeFactorRepo extends JpaRepository<PrimeFactor, Integer> {}

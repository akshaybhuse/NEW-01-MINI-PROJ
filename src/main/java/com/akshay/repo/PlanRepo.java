package com.akshay.repo;

import com.akshay.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<Plan,Integer> {
}

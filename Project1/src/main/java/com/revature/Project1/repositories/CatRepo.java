package com.revature.Project1.repositories;

import com.revature.Project1.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {}

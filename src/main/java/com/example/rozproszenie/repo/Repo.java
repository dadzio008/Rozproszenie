package com.example.rozproszenie.repo;

import com.example.rozproszenie.model.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Generator,Long> {
}

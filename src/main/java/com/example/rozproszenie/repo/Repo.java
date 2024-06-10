package com.example.rozproszenie.repo;

import com.example.rozproszenie.model.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface Repo extends JpaRepository<Generator,Long> {
//    void deleteAllByDate(Date date);
    void deleteAllByLastUsageDate(Date date);

}

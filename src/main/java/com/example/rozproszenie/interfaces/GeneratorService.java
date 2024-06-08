package com.example.rozproszenie.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface GeneratorService {

    Long getId(String url);

    HttpStatus deleteUrlByLastUsageDate(Date date);

    HttpStatus deleteUrlByCreationDateOnCassandra(String genId);
}

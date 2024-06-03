package com.example.rozproszenie.services;

import com.example.rozproszenie.interfaces.GeneratorService;
import com.example.rozproszenie.model.Generator;
import com.example.rozproszenie.repo.Repo;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements GeneratorService {

    private Repo repo;

    public ServiceImpl(Repo repo) {
        this.repo = repo;
    }

    @Override
    public Long getId(String url) {
        Generator gen = new Generator();
        gen.setUrl(url);
        repo.save(gen);
        return gen.getId();
    }
}

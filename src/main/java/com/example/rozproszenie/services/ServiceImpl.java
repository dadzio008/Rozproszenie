package com.example.rozproszenie.services;

import com.example.rozproszenie.interfaces.GeneratorService;
import com.example.rozproszenie.model.Generator;
import com.example.rozproszenie.model.GeneratorCassandra;
import com.example.rozproszenie.repo.CassandraRepo;
import com.example.rozproszenie.repo.Repo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceImpl implements GeneratorService {

    private Repo repo;
    private CassandraRepo cassandraRepo;

    public ServiceImpl(Repo repo, CassandraRepo cassandraRepo) {
        this.repo = repo;
        this.cassandraRepo = cassandraRepo;
    }

    @Override
    public Long getId(String url) {
        Generator gen = new Generator();
        gen.setUrl(url);
        repo.save(gen);
        GeneratorCassandra generatorCassandra = new GeneratorCassandra();
        generatorCassandra.setId(gen.getId());
        generatorCassandra.setUrl(url);
        cassandraRepo.save(generatorCassandra);
        return gen.getId();
    }

    @Override
    public HttpStatus deleteUrlByLastUsageDate(Date date) {
        repo.deleteAllByLastUsageDate(date);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus deleteUrlByCreationDateOnCassandra(String genId) {
        GeneratorCassandra gen = cassandraRepo.getGeneratorCassandraById(Long.valueOf(genId));
        cassandraRepo.delete(gen);
        return HttpStatus.OK;
    }
}

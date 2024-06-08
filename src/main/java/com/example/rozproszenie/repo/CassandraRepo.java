package com.example.rozproszenie.repo;

import com.example.rozproszenie.model.GeneratorCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassandraRepo extends CassandraRepository<GeneratorCassandra, Long> {

    GeneratorCassandra getGeneratorCassandraById(Long id);
}

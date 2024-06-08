package com.example.rozproszenie.configs;//package com.example.rozproszenie.configs;
//
//import com.datastax.oss.driver.api.core.CqlSession;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.core.CassandraAdminTemplate;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//
//@Configuration
//@EnableCassandraRepositories
//public class CassandraConfig extends AbstractCassandraConfiguration {
//
//    @Override
//    protected String getKeyspaceName() {
//        return "SolarSystem";
//    }
//
//    @Override
//    protected String getContactPoints() {
//        return "localhost";
//    }
//
//    @Override
//    protected int getPort() {
//        return 9042;
//    }
//
//    @Override
//    protected String getLocalDataCenter() {
//        return "Mars";
//    }
//
//    @Override
//    public SchemaAction getSchemaAction() {
//        return SchemaAction.CREATE_IF_NOT_EXISTS;
//    }
//
//    @Bean
//    public CassandraAdminTemplate cassandraAdminTemplate(CqlSession session) {
//        return new CassandraAdminTemplate(session, cassandraConverter());
//    }
//}


import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.cql.generator.CreateKeyspaceCqlGenerator;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

@Configuration
@ConditionalOnClass(CqlSession.class)
@ConditionalOnProperty(name = "spring.data.cassandra.create-keyspace", havingValue = "true")
@AutoConfigureBefore(CassandraAutoConfiguration.class)
public class CassandraCreateKeyspaceAutoConfiguration {


    public CassandraCreateKeyspaceAutoConfiguration(CqlSessionBuilder cqlSessionBuilder, CassandraProperties properties) {
        // It's OK to mutate cqlSessionBuilder because it has prototype scope.
        try (CqlSession session = cqlSessionBuilder.withKeyspace((CqlIdentifier) null).build()) {
            session.execute(CreateKeyspaceCqlGenerator.toCql(
                    CreateKeyspaceSpecification.createKeyspace(properties.getKeyspaceName()).ifNotExists()));
        }
    }

    @Bean
    public void initializeKeyspace() {
        try (CqlSession session = CqlSession.builder().build()) {
            System.out.println("creating");
            session.execute("CREATE KEYSPACE IF NOT EXISTS mykeyspace "
                    + "WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}");
        }
    }
}
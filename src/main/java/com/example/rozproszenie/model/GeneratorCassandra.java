package com.example.rozproszenie.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("UrlMappings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneratorCassandra {
    @PrimaryKey
    private Long id;
    @Column
    private String url;
}

package com.sammy.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LeaderRepository extends ElasticsearchRepository<Leader, String> {
}

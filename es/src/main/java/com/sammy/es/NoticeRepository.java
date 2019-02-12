package com.sammy.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NoticeRepository extends ElasticsearchRepository<Notice, Integer> {
}

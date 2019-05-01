package com.uday.indexsearch.repository;

import com.uday.indexsearch.rest.domain.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car,String> {
}

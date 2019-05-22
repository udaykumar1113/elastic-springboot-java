package com.uday.indexsearch.repository;

import com.uday.indexsearch.rest.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car,String> {

    public List<Car> findByBrandAndColor(String brand, String color);

    @Query("{\n" +
            "    \"bool\":{\n" +
            "      \"must\":[\n" +
            "        {\n" +
            "          \"match\":\n" +
            "          {\n" +
            "              \"color\":\"black\"\n" +
            "          }\n" +
            "        }\n" +
            "          ]\n" +
            "        }\n" +
            "        }")
    public List<Car> findByColor(String color);

    @Query("{\n" +
            "    \"bool\":{\n" +
            "      \"must\":[\n" +
            "        {\n" +
            "          \"match\":\n" +
            "          {\n" +
            "              \"color\":\"?0\"\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"match\":\n" +
            "          {\n" +
            "              \"brand\":\"?1\"\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }")
    public List<Car> findByColorAndBrand(String color, String brand);

    public Page<Car> findByType(String type, Pageable pageable);
}

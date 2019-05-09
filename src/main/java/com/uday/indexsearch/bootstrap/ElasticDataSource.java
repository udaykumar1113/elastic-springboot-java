package com.uday.indexsearch.bootstrap;

import com.uday.indexsearch.rest.service.RandomCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Configuration
public class ElasticDataSource {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ElasticsearchRepository elasticsearchRepository;
    @Autowired
    private RandomCarService randomCarService;

    @EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void populateData(){
        System.out.println("This is executed");
        ResponseEntity response=
                restTemplate.exchange("http://localhost:9200/car-index", HttpMethod.DELETE,null,String.class);
        System.out.println("Delete response: "+response.getBody());

        ArrayList carsList=new ArrayList();
        for(int i=0;i<10000;i++){
            carsList.add(randomCarService.generateCar());
        }
        elasticsearchRepository.saveAll(carsList);

        System.out.println("Documents in elasticsearch cluster "+elasticsearchRepository.count());
    }
}

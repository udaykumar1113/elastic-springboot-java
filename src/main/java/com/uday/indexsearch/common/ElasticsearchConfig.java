package com.uday.indexsearch.common;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.uday.indexsearch.repository")
public class ElasticsearchConfig {
    private static final String esHost="127.0.0.1";
    private static final int esPort=9300;

    @Bean
    public Client client() throws UnknownHostException {
        TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY);
        return transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), esPort));
    }

    @Bean
    public ElasticsearchOperations esTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }
}

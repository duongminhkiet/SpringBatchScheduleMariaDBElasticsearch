//#7
package com.zmikisoft.springbatch.mariadb2elasticsearch.batch.readwrite;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zmikisoft.springbatch.mariadb2elasticsearch.model.User;
import com.zmikisoft.springbatch.mariadb2elasticsearch.repository.ElasticsearchEmployeeRepository;

@Component
@JobScope
public class UserItemWriter implements ItemWriter<User> {

//    private ElasticsearchTemplate elasticsearchTemplate;
	
    private ElasticsearchEmployeeRepository elasticsearchEmployeeRepository;
    //private Date date;
    private Long runId;

//    @Autowired
//    public UserItemWriter(ElasticsearchTemplate elasticsearchTemplate,
//                               @Value("#{jobParameters['run.id']}") Long runId) {
//        this.elasticsearchTemplate = elasticsearchTemplate;
//        this.runId = runId;
//    }
    @Autowired
    public UserItemWriter(ElasticsearchEmployeeRepository elasticsearchEmployeeRepository,
                               @Value("#{jobParameters['run.id']}") Long runId) {
//        this.elasticsearchTemplate = elasticsearchTemplate;
        this.elasticsearchEmployeeRepository = elasticsearchEmployeeRepository;
        this.runId = runId;
    }

    @Override
    public void write(List<? extends User> items) throws Exception {
    	elasticsearchEmployeeRepository.saveAll(items);
//        List<IndexQuery> indexQueries = items.stream()
//                .map(item -> new IndexQueryBuilder().withObject(item).withId(String.valueOf(item.getId())))
//                .map(builder -> builder.withType("userx"))
//                .map(builder -> builder.withIndexName("userx-" + runId))
//                .map(IndexQueryBuilder::build)
//                .collect(Collectors.toList());
//
//        this.elasticsearchTemplate.bulkIndex(indexQueries);
        
        
    }
}
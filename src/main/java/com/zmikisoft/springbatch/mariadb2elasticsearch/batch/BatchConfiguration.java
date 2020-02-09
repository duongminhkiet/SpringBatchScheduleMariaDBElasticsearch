//#5
package com.zmikisoft.springbatch.mariadb2elasticsearch.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zmikisoft.springbatch.mariadb2elasticsearch.batch.readwrite.UserItemProcessor;
import com.zmikisoft.springbatch.mariadb2elasticsearch.model.User;
import com.zmikisoft.springbatch.mariadb2elasticsearch.repository.ElasticsearchEmployeeRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
//	 @Autowired
//	 public JobBuilderFactory jobBuilderFactory;
//	 
//	 @Autowired
//	 public StepBuilderFactory stepBuilderFactory;
//	 
//
//	 @Autowired
//	 private ElasticsearchEmployeeRepository personRepository;
	//1
	 @Bean
	 public DataSource dataSource() {
	  final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	  dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
	  dataSource.setUrl("jdbc:mariadb://localhost/springbatch");
	  dataSource.setUsername("root");
	  dataSource.setPassword("");
	  return dataSource;
	 }
	 //2
	 @Bean
	 public UserItemProcessor processor(){
	  return new UserItemProcessor();
	 }
	 
	 @Bean
	 public Step importUserStep(StepBuilderFactory stepBuilderFactory,
	                                ItemReader<User> userItemReader,
	                                ItemWriter<User> userItemWriter) {
	     return stepBuilderFactory.get("importEmployeeStep")
	             .<User, User>chunk(1000)
	             .reader(userItemReader)
	             .processor(processor())
//	             .writer(writer())
	             .writer(userItemWriter)
//	             .writer(itemWriter())
	             .build();
	 }
	 @Bean
	 public Job importJob(JobBuilderFactory jobBuilderFactory,
	                      Step importUserStep, Step renameAliasStep) {
	     return jobBuilderFactory.get("importJob")
	             .incrementer(new RunIdIncrementer())
	             .flow(importUserStep)
	             .end()
	             .build();
	 }

}

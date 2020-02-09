package com.zmikisoft.springbatch.mariadb2elasticsearch;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//#1
//#2 => application.properties
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.batch.core.Job;
@SpringBootApplication
@EnableScheduling
public class SpringBactchMigrateDataFromMariadbToElasticsearchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBactchMigrateDataFromMariadbToElasticsearchApplication.class, args);
	}
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@Override
	public void run(String... args) throws Exception {
//		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
//				.toJobParameters();
//		jobLauncher.run(job, params);
//		System.out.print("@Scheduled @Scheduled @Scheduled");
	}
}
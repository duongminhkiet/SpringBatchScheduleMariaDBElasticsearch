//#4
package com.zmikisoft.springbatch.mariadb2elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zmikisoft.springbatch.mariadb2elasticsearch.model.User;



public interface ElasticsearchEmployeeRepository extends ElasticsearchRepository<User, Integer> {
}
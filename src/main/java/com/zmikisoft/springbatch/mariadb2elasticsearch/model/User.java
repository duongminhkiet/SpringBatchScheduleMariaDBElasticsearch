//#3
package com.zmikisoft.springbatch.mariadb2elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Mapping(mappingPath = "/settings/mapping.json")
@Setting(settingPath = "/settings/setting.json")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "user", type = "userTypeX")
public class User {
	@Id
	 private Integer id;
	 private String name;
	 
	 public Integer getId() {
	  return id;
	 }
	 public void setId(Integer id) {
	  this.id = id;
	 }
	 public String getName() {
	  return name;
	 }
	 public void setName(String name) {
	  this.name = name;
	 }
	}
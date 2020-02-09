//#8
package com.zmikisoft.springbatch.mariadb2elasticsearch.batch.readwrite;

import org.springframework.batch.item.ItemProcessor;

import com.zmikisoft.springbatch.mariadb2elasticsearch.model.User;

public class UserItemProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User user) throws Exception {
		return user;
	}

}
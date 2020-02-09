//#6
package com.zmikisoft.springbatch.mariadb2elasticsearch.batch.readwrite;

import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

//import com.zmikisoft.springbatch.mariadb2elasticsearch.batch.readwrite.UserDBConnection.UserRowMapper;
import com.zmikisoft.springbatch.mariadb2elasticsearch.model.User;



@Component
@JobScope
public class UserItemReader implements ItemReader<User> {

    private UserDBConnection userDBConnection;

    public UserItemReader(DataSource dataSource) {
        this.userDBConnection = new UserDBConnection(dataSource);
    }

    @BeforeStep
    public void openExecutionContext(StepExecution stepExecution) {
        this.userDBConnection.open(stepExecution.getExecutionContext());
    }

    @AfterStep
    public void closeExecutionContext() {
        this.userDBConnection.close();
    }

    @Override
    public User read() throws Exception {
    	return this.userDBConnection.read();
    	
    }
    private class UserDBConnection extends JdbcCursorItemReader<User> {

        public UserDBConnection(DataSource dataSource) {
            this.setDataSource(dataSource);
            this.setSql("SELECT id,name FROM user");
            this.setRowMapper(new UserRowMapper());
        }

        private class UserRowMapper implements RowMapper<User> {
        	  @Override
        	  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        	   User user = new User();
        	   user.setId(rs.getInt("id"));
        	   user.setName(rs.getString("name"));
        	   
        	   return user;
        	  }
        }
    }
}
 
package com.ofs.maven.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ofs.maven.bean.BeanFactory;
import com.ofs.maven.model.ToDo;
import com.ofs.maven.service.ToDoService;

import Queries.Statements;
import exception.ErrorCode;
import exception.ServiceException;

@EnableScheduling
public class ToDoServiceImpl implements ToDoService {

	private JdbcTemplate template;

	public ToDoServiceImpl(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(ToDo todo) {
		int todoId = todo.getId();
		try {
			if (Objects.isNull(todoId)) {
				throw new ServiceException(ErrorCode.ID_NOT_FOUND);
			}
			template.update(Statements.create, todo.getId(), todo.getTasks(), todo.getStatus(), todo.getDue_date());

		} catch (DataAccessException dac) {
			throw new ServiceException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Scheduled(cron = "10 12 14 5 2 ?")
	public void scheduleTaskUsingCronExpression() {
	  
	    long now = System.currentTimeMillis() / 1000;
	    System.out.println( "schedule tasks using cron jobs - " + now);
	}
	
	@Override
	public void update(ToDo todo) {
		template.update(Statements.update, todo.getId(), todo.getTasks(), todo.getStatus(), todo.getDue_date());
	}

	@Override
	public void delete(int id) {
		int deletedId = template.update(Statements.delete, id);
		try {
			if (deletedId == 1) {
				throw new ServiceException(ErrorCode.ID_NOT_FOUND);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ToDo> getAllToDos() {
		List<ToDo> listToDo = template.query(Statements.readAll, new RowMapper<ToDo>() {

			@Override
			public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ToDo todo = BeanFactory.getTodoBean();
				todo.setId(rs.getInt("id"));
				todo.setTasks(rs.getString("tasks"));
				todo.setStatus(rs.getString("status"));
				todo.setDue_date(rs.getString("due_date"));

				return todo;
			}
		});
		return listToDo;
	}

	@Override
	public ToDo getTodoById(int id) {
		return template.query(Statements.read, new ResultSetExtractor<ToDo>() {

			@Override
			public ToDo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					ToDo todo = new ToDo();
					todo.setId(rs.getInt("id"));
					todo.setTasks(rs.getString("tasks"));
					todo.setStatus(rs.getString("status"));
					todo.setDue_date(rs.getString("due_date"));
					return todo;
				}
				return null;
			}
		});
	}

	@Override
	public List<ToDo> searchToDo(String tasks) {
		List<ToDo> listResult = template.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(Statements.search);
				statement.setString(1, "%"+tasks+"%");
				return statement;
			}
		}, new RowMapper<ToDo>() {
			@Override
			public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ToDo todo = BeanFactory.getTodoBean();
				todo.setId(rs.getInt("id"));
				todo.setTasks(rs.getString("tasks"));
				todo.setStatus(rs.getString("status"));
				todo.setDue_date(rs.getString("due_date"));
				return todo;
			}
		});
		return listResult;
	}
}

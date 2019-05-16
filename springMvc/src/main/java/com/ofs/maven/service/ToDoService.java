package com.ofs.maven.service;

import java.util.List;
import com.ofs.maven.model.ToDo;

import exception.ServiceException;

public interface ToDoService {

	public void create(ToDo todo) throws ServiceException;
	/*
	 * create new todo task and implementation is in ToDoServiceImpl class
	 */
	
	public ToDo getTodoById(int id);
	/*
	 * get particular todo task using attribute "id"
	 */
	
	public void update(ToDo todo);
	/*
	 * update todo task for a particular id
	 */
	
	public void delete(int id) throws ServiceException;
	/*
	 * delete todo task using id 
	 */
	
	public List<ToDo> getAllToDos();
	/*
	 * get all the todos as a list
	 */
	
	public List<ToDo> searchToDo(String tasks);
	/*
	 * search todos in the table using attribute values  
	 */
}

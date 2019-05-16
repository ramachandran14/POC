package com.ofs.maven.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ofs.maven.Impl.MailServiceImpl;
import com.ofs.maven.Impl.ToDoServiceImpl;
import com.ofs.maven.model.ToDo;

import exception.ServiceException;

@Controller
public class HomeController {
	
	private MailServiceImpl mailServiceImpl;
	
	@Autowired
	private ToDoServiceImpl todoServiceImpl;
	@Autowired(required = true)
	public void setMailService(MailServiceImpl mailServiceImpl) {
	    this.mailServiceImpl = mailServiceImpl;
	}
	
	@GetMapping(value="/todo/{var}")
	public ModelAndView redirect(@PathVariable String var) {
		ModelAndView model = new ModelAndView(var);
		return model;
	}
	
	@GetMapping(path = "/")
	public ModelAndView listTodo(ModelAndView model) {
		List<ToDo> listToDo = todoServiceImpl.getAllToDos();
		model = new ModelAndView("index");
		model.addObject("listToDo", listToDo);
		return model;
	}

	@PostMapping(value = "/save")
	public ModelAndView save(ToDo todo, @RequestParam("tasks") String tasks, @RequestParam("status") String status, @RequestParam("due_date") String due_date) throws ServiceException {
		todo.setTasks(tasks);
		todo.setDue_date(due_date);
		todo.setStatus(status);
		todoServiceImpl.create(todo);
		mailServiceImpl.printMessage();
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping(value = "/update")
	public ModelAndView update(@ModelAttribute ToDo todo) {
		todoServiceImpl.update(todo);
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("value"));
		try {
			todoServiceImpl.delete(id);
			ModelAndView model = new ModelAndView("success");
			model.setStatus(HttpStatus.OK);
			model.addObject("msg", "The Value is deleted");
		} catch (ServiceException se) {
			ModelAndView model = new ModelAndView("error");
			model.setStatus(HttpStatus.BAD_REQUEST);
			model.addObject("msg", "The Value is not present");
		} catch(Exception e) {
			ModelAndView model = new ModelAndView("error");
			model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			model.addObject("msg", "Internal server error");
		}
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/read")
	public ModelAndView read(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		ToDo todo = todoServiceImpl.getTodoById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("todo", todo);
		return model;
	}
	
	@PostMapping(value="/search")
	public ModelAndView search(HttpServletRequest req) {
		String tasks = req.getParameter("tasks");
		System.out.println(System.currentTimeMillis());
		List<ToDo> listToDo = todoServiceImpl.searchToDo(tasks);
		System.out.println(System.currentTimeMillis());
		ModelAndView model = new ModelAndView("index");
		model.addObject("listToDo", listToDo);
		return model;
	}
}
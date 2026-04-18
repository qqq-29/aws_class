package kr.hi.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.hi.todo.dao.TodoDAO;
import kr.hi.todo.model.vo.TodoVO;

@Service
public class TodoService {
	
	private final TodoDAO todoDAO;
	
	public TodoService(TodoDAO todoDAO) {
		this.todoDAO = todoDAO;
	}

	public List<TodoVO> getTodos(String date) {
			return todoDAO.selectTodos(date);
		
	}

	public Boolean insertTodo(TodoVO todoVO) {
		try {
		boolean todo = todoDAO.insertTodo(todoVO);
		return todo;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delTodo(int num) {

		return todoDAO.delTodo(num);
	}

	public boolean updateTodo(TodoVO todo) {
		try {
			return todoDAO.updateTodo(todo);
		}catch(Exception e) {
			e.printStackTrace();
		return false;
		}
	}

}

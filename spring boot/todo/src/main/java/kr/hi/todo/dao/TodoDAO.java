package kr.hi.todo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.hi.todo.model.vo.TodoVO;

public interface TodoDAO {

	List<TodoVO> selectTodos(@Param("date") String date);

	boolean insertTodo(@Param("todoVO") TodoVO todoVO);

	boolean delTodo(@Param("num")int num);

	boolean updateTodo(@Param("todo")TodoVO todo);

}

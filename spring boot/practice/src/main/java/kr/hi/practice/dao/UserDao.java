package kr.hi.practice.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
	// XML의 <select id="findByUsername">와 연결됩니다.
    Map<String, String> findByUsername(String username);
}
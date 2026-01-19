package kr.hi.boot.service;

import org.springframework.stereotype.Service;

import kr.hi.boot.dao.LikeDAO;
import kr.hi.boot.model.util.CustomUser;

@Service
public class LikeService {
	
	private final LikeDAO likeDAO;
	
	public LikeService(LikeDAO likeDAO) {
		this.likeDAO = likeDAO;
	}

	public String like(int postNum, CustomUser user) {
		//로그인 안했으면 로그인이 필요한 서비스입니다를 반환
		if(user == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		//다오에게 게시글번호와 사용자 아이디를 주면서 삭제하고 결과를 가져오라고 요청
		boolean isDel = likeDAO.deleteLike(postNum, user.getUsername());
		//추천했으면 추천을 취소했습니다라고 반환
		if(isDel) {
			return "추천을 취소했습니다.";
		}
		
		//못했으면(추천정보가 없어서)
		//다오에게 게시글번호와 아이디를 주면서 추천하고 결과를 가져오라고 요청
		boolean isLike = likeDAO.insertlike(postNum, user.getUsername());
		//추천했으면 추천을 했습니다를 반환
		if(isLike) {
			return "추천을 했습니다.";
		}
		//못했으면 추천을 하지 못했습니다를 반환
		return "추천을 하지 못했습니다.";
		

	}
}

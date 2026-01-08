package kr.hi.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.hi.community.dao.PostDAO;
import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.util.UploadFileUtils;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.FileVO;
import kr.hi.community.model.vo.PostVO;

@Service
public class PostService {

	@Autowired
	PostDAO postDAO;

	// application.properties에 있는 file.upload-dir에 있는 값을 가져와서 저장
	@Value("${file.upload-dir}")
	String uploadPath;

	public ArrayList<PostVO> getPostList(Criteria cri) {
		// 다오에게 게시글 번호에 맞는 게시글 목록을 가져오라고 요청
		ArrayList<PostVO> list = postDAO.selectPostList(cri);
		// 게시글 목록을 반환
		return list;
	}

	public PostVO getPost(int num) {
		PostVO post = postDAO.getPost(num);
		return post;
	}

	public void viewPlus(int num) {
		postDAO.viewPlus(num);

	}

	public ArrayList<BoardVO> getBoardList() {
		// 다오에게 게시판 목록을 가져오라고 요청
		ArrayList<BoardVO> boardlist = postDAO.getBoardList();
		return boardlist;
	}

	private boolean checkEmpty(String str) {
		// null이거나
		if (str == null) {
			return false;
		}
		// 공백으로 이루어져 있으면 true를 리턴
		if (str.trim().isEmpty()) {
			return true;
		}
		// 공백이 아닌 한글자라도 있는 경우 false를 리턴
		return false;
	}

	public boolean insertPost(PostDTO postDTO, CustomUser customUser, List<MultipartFile> files) {
		// 게시글 정보 확인 => 입력 안된 값 있는지 확인해서 잘못된게 있으면 false를 반환
		if (postDTO.getBoard() == 0 || checkEmpty(postDTO.getContent()) || checkEmpty(postDTO.getTitle())) {
			return false;
		}
		// 사용자 정보를 확인 => 로그인 됐는지 확인해서 안했으면 false를 반환
		if (customUser.getUser() == null || customUser == null) {
			return false;
		}
		// 게시글의 작성자를 로그인한 회원의 아이디로 수정
		// BoardVO id = postDAO.getboard(postDTO.getBoard());
		postDTO.setWriter(customUser.getUsername());
		postDAO.insertPost(postDTO);
		try {
			//다오에게 게시글 정보를 주면서 등록하라고 시킴
			postDAO.insertPost(postDTO);
			
		}catch(Exception e) {
			//잘못된 게시판 번호를 입력한 경우 게시글 등록에 실패
			e.printStackTrace();
			return false;
		}
		// 게시글 등록 후 첨부파일 추가
		// 첨부파일 목록이 없는 경우
		if (files == null || files.isEmpty()) {
			return true;
		}
		for (MultipartFile file : files) {
			//db에 추가하고 서버에 첨부파일 업로드해
			insertFile(postDTO.getPostNum(), file);
			
		}
		return true;
	}

	private void insertFile(int postNum, MultipartFile file) {
		try {
			String fileName = 
					UploadFileUtils.uploadFile(uploadPath, file);
			String oriFileName = file.getOriginalFilename();
			
			FileVO fileVo = 
					new FileVO(postNum, oriFileName, fileName);
			//DB에 업로드한 파일 정보를 추가
			postDAO.insertFile(fileVo);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

	public void insertBoard(String name) {
		// 공백으로 되어 있는 경우
		if (checkEmpty(name)) {
			return;
		}
		// 예외 처리 => 게시판명이 중복되면 예외 발생하기 때문에
		try {
			postDAO.insertBoard(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBoard(int num) {
		try {
			postDAO.deleteBoard(num);
		} catch (Exception e) {
			// 게시글이 있는 게시판을 삭제하려고 하면 예오가 발생
			// => 외래키 옵션에서 게시판번호를 참조하는 게시글이 있는 경우
			// 해당 게시판을 삭제하지 못하도록(Restrict)로 설정되어 있기 때문에
			e.printStackTrace();
		}

	}

	public void updateBoard(String name, int num) {
		if (checkEmpty(name)) {
			return;
		}

		try {
			postDAO.updateBoard(name, num);
		} catch (Exception e) {
			// 수정하려는 게시판 명이 중복되면 예외발생
			e.printStackTrace();
		}

	}

	public int getTotalCount(Criteria cri) {
		if (cri == null) {
			return 0;
		}
		return postDAO.selectTotalCount(cri);
	}

	public void deletePost(int num, CustomUser customUser) {
		// 로그인이 안된 경우 종료
		if (customUser == null || customUser.getUsername() == null) {
			return;
		}
		// 작성자 정보를 가져오기위해 게시글 정보를 가져옴
		PostVO post = postDAO.getPost(num);

		// 작성자가 다르면
		if (post == null || !post.getPo_me_id().equals(customUser.getUsername())) {
			return;
		}
		List<FileVO> files = postDAO.selectFileList(num);
		
		for(FileVO file : files) {
			deleteFile(file);
			
			
		}
		
		//게시글 삭제(실제 삭제하는거 아니고 po_del를 Y로 처리
		postDAO.deletePost(num);

	}

	private void deleteFile(FileVO file) {
		if(file == null) {
			return;
		}
		//첨부파일 삭제
		//1. 실제 첨부파일을 삭제
		UploadFileUtils.deleteFile(uploadPath, file.getFi_name());
		//2. DB에 있는 첨부파일 정보를 삭제
		//다오에게 첨부파일 번호를 주면서 첨부파일을 삭제하라고 요청
		//다오.첨부파일삭제해줘(첨부파일번호)
		//게시글 번호를 다오에게 주면서 첨부파일을 삭제하라고 요청
		//다오.첨부파일삭제해(게시글번호)
		postDAO.deleteFile(file.getFi_num());
		
		
	}

	public boolean postUpdatePost(PostDTO postDTO, CustomUser customUser, int postNum) {

		if (postDTO.getContent() == null || postDTO.getTitle() == null) {
			return false;
		}
		if (customUser == null || customUser.getUsername().equals(postDTO.getWriter())) {
			return false;
		}
		try {
			postDAO.postUpdatePost(postDTO, postNum);
		} catch (Exception e) {
			e.addSuppressed(e);
			return false;
		}
		return false;

	}

//다른 방법
	public void postUpdatePost2(PostDTO postDTO, CustomUser customUser) {
//		- 사용자가 로그인 안했으면 종료
		if (customUser == null || customUser.getUsername().isEmpty()) {
			return;
		}
//		- 게시글 정보가 없거나. 게시글 제목 또는 내용이 비어있으면 종료
		if (postDTO == null || checkEmpty(postDTO.getTitle()) || checkEmpty(postDTO.getContent())) {
			return;
		}
//		- 사용자와 작성자가 같은지 확인해서 다르면 종료
//		  - 다오에게 게시글 번호를 주면서 게시글을 가져오라고 요청
		PostVO dbPost = postDAO.getPost(postDTO.getPostNum());
//		  - 게시글 없거나 게시글 작성자가 사용자와 다르면 종료
		if (dbPost == null || !dbPost.getPo_me_id().equals(customUser.getUsername())) {
			return;
		}
//		- 다오에게 게시글 정보를 주면서 수정하라고 요청
		postDAO.updatePost(postDTO);
	}

	public List<FileVO> getFileList(int postNum) {
		return postDAO.selectFileList(postNum);
	}

}

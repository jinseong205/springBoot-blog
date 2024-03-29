package com.jinseong.blog.controller.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jinseong.blog.auth.PrincipalDetail;
import com.jinseong.blog.dto.ReplySaveRequestDto;
import com.jinseong.blog.dto.ResponseDto;
import com.jinseong.blog.model.Board;
import com.jinseong.blog.model.Reply;
import com.jinseong.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	HttpSession session;

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.saveBoard(board, principal.getMember());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal)
			throws Exception {
		boardService.deleteBoard(id, principal);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board,
			@AuthenticationPrincipal PrincipalDetail principal) throws Exception {
		boardService.updateBoard(id, board, principal);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	// 데이터 받을 때 controller에서 dto를 만들어서 받는게 좋다.
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply,
			@AuthenticationPrincipal PrincipalDetail principal) {
		boardService.saveReply(reply, boardId, principal.getMember());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PostMapping("/api/board/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto reply) {
		boardService.saveReply(reply);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId,
			@AuthenticationPrincipal PrincipalDetail principal) throws Exception {
		boardService.deleteReply(replyId, principal);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}



}

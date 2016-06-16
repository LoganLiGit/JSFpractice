package com.reply.model;

import java.sql.Timestamp;
import java.util.List;


public class ReplyService {

	private ReplyDAO_interface dao;
	
	public ReplyService() {
		dao = new ReplyJNDIDAO();
	}
	
	public ReplyVO addReply(Integer mem_no, Integer article_no, String reply_msg,Timestamp reply_time) {

		ReplyVO replyVO = new ReplyVO();

		replyVO.setMem_no(mem_no);
		replyVO.setArticle_no(article_no);
		replyVO.setReply_msg(reply_msg);
		replyVO.setReply_time(reply_time);
		dao.insert(replyVO);

		return replyVO;
	}
	
	 public List<ReplyVO> addReply2(Integer mem_no, Integer article_no, String reply_msg,Timestamp reply_time) {

		ReplyVO replyVO = new ReplyVO();

		replyVO.setMem_no(mem_no);
		replyVO.setArticle_no(article_no);
		replyVO.setReply_msg(reply_msg);
		replyVO.setReply_time(reply_time);
		return dao.insert2(replyVO,article_no);

	}
	
	public ReplyVO updateReply(Integer reply_no, Integer mem_no, Integer article_no, String reply_msg,Timestamp reply_time) {

		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setReply_no(reply_no);
		replyVO.setMem_no(mem_no);
		replyVO.setArticle_no(article_no);
		replyVO.setReply_msg(reply_msg);
		replyVO.setReply_time(reply_time);
		dao.update(replyVO);

		return replyVO;
	}
	
	public ReplyVO updateReply2(Integer reply_no, String reply_msg) {

		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setReply_no(reply_no);
		replyVO.setReply_msg(reply_msg);

		dao.update2(replyVO);

		return replyVO;
	}
	
	public void deleteReply(Integer reply_no) {
		dao.delete(reply_no);
	}
	
	public void deleteReplyByArticle(Integer article_no) {
		dao.deleteReplyByArticle(article_no);
	}
	
	public List<ReplyVO> deleteReply2(Integer reply_no, Integer article_no) {
	
		return dao.delete2(reply_no,article_no);
	}
	
	public ReplyVO getOneReply(Integer reply_no) {
		return dao.findByPrimaryKey(reply_no);
	}
	
	public List<ReplyVO> getSomeReply(Integer article_no) {
		return dao.findByPrimaryKey2(article_no);
	}
	
	public List<ReplyVO> getAll() {
		return dao.getAll();
	}
}


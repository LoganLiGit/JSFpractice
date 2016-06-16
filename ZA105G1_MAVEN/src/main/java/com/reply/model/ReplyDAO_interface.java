package com.reply.model;

import java.util.*;

public interface ReplyDAO_interface {
	public void insert(ReplyVO replyVO);
    public void update(ReplyVO replyVO);
    public void update2(ReplyVO replyVO);
    public void delete(Integer reply_no);
    public ReplyVO findByPrimaryKey(Integer reply_no);
    public List<ReplyVO> findByPrimaryKey2(Integer article_no);//find one article's replies.
    public List<ReplyVO> getAll();
    public void deleteReplyByArticle(Integer article_no);
	public List<ReplyVO> delete2(Integer reply_no, Integer article_no);
	public List<ReplyVO> insert2(ReplyVO replyVO, Integer article_no);
}

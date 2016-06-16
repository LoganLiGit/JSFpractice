package com.article.model;

import java.sql.Timestamp;
import java.util.List;

import com.friend.model.FriendVO;


public class ArticleService {
	
	private ArticleDAO_interface dao;
	
	public ArticleService() {
		dao = new ArticleJNDIDAO();
	}
	
	public ArticleVO addArticle(Integer store_no, Integer mem_no, String store_name, String article_content,
			String article_title,Timestamp article_create,Timestamp article_modify,String article_status,Integer article_score,Integer article_click,Integer article_replies) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setStore_no(store_no);
		articleVO.setMem_no(mem_no);
		articleVO.setStore_name(store_name);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_create(article_create);
		articleVO.setArticle_modify(article_modify);
		articleVO.setArticle_status(article_status);
		articleVO.setArticle_score(article_score);
		articleVO.setArticle_click(article_click);
		articleVO.setArticle_replies(article_replies);
		dao.insert(articleVO);

		return articleVO;
	}
	
	public ArticleVO updateArticle(Integer article_no, Integer store_no, Integer mem_no, String store_name,String article_content,
			String article_title,Timestamp article_create,Timestamp article_modify,String article_status,Integer article_score,Integer article_click,
			Integer article_replies) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_no(article_no);
		articleVO.setStore_no(store_no);
		articleVO.setMem_no(mem_no);
		articleVO.setStore_name(store_name);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_create(article_create);
		articleVO.setArticle_modify(article_modify);
		articleVO.setArticle_status(article_status);
		articleVO.setArticle_score(article_score);
		articleVO.setArticle_click(article_click);
		articleVO.setArticle_replies(article_replies);
		
		dao.update(articleVO);
		
		return articleVO;
	}
	
	public void deleteArticle(Integer article_no) {
		dao.delete(article_no);
	}
	
	public ArticleVO getOneArticle(Integer article_no) {
		return dao.findByPrimaryKey(article_no);
	}
	
	public List<ArticleVO> getSomeArticle(Integer mem_no) {
		return dao.findByPrimaryKey2(mem_no);
	}
	
	public List<ArticleVO> ListArticleByStore_no(Integer store_no) {
		return dao.ListArticleByStore_no(store_no);
	}
	
	public ArticleVO getlatestArticle(Integer article_no) {
		return dao.findByPrimaryKey3(article_no);
	}
	
	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public ArticleVO updateArticleClick(Integer article_no,Integer article_click){
		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_no(article_no);
		articleVO.setArticle_click(article_click);
		dao.updateArticleClick(articleVO);
		
		return articleVO;
	}
	
	public void updateArticleRepliesNum(Integer article_no, Integer article_replies) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_no(article_no);
		articleVO.setArticle_replies(article_replies);
		dao.updateArticleRepliesNum(articleVO);

	}

}

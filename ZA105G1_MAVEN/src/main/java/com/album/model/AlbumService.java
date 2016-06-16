package com.album.model;

import java.sql.Timestamp;
import java.util.List;

import com.friend.model.FriendVO;


public class AlbumService {
	
	private AlbumDAO_interface dao;
	
	public AlbumService() {
		dao = new AlbumJNDIDAO();
	}
	
	public AlbumVO addAlbum(Integer mem_no, byte[] mem_photo, String photo_title, String photo_description) {

		AlbumVO albumVO = new AlbumVO();

		albumVO.setMem_no(mem_no);
		albumVO.setMem_photo(mem_photo);
		albumVO.setPhoto_title(photo_title);
		albumVO.setPhoto_description(photo_description);
		
		dao.insert(albumVO);

		return albumVO;
	}
	
//	public ArticleVO updateArticle(Integer article_no, Integer store_no, Integer mem_no, String store_name,String article_content,
//			String article_title,Timestamp article_create,Timestamp article_modify,String article_status,Integer article_score,Integer article_click) {
//
//		ArticleVO articleVO = new ArticleVO();
//
//		articleVO.setArticle_no(article_no);
//		articleVO.setStore_no(store_no);
//		articleVO.setMem_no(mem_no);
//		articleVO.setStore_name(store_name);
//		articleVO.setArticle_content(article_content);
//		articleVO.setArticle_title(article_title);
//		articleVO.setArticle_create(article_create);
//		articleVO.setArticle_modify(article_modify);
//		articleVO.setArticle_status(article_status);
//		articleVO.setArticle_score(article_score);
//		articleVO.setArticle_click(article_click);
//		dao.update(articleVO);
//		
//		return articleVO;
//	}
	
	public void deleteAlbum(Integer photo_no) {
		dao.delete(photo_no);
	}
	
//	public ArticleVO getOneArticle(Integer article_no) {
//		return dao.findByPrimaryKey(article_no);
//	}
//	
//	public List<ArticleVO> getSomeArticle(Integer mem_no) {
//		return dao.findByPrimaryKey2(mem_no);
//	}
//	
//	public ArticleVO getlatestArticle(Integer article_no) {
//		return dao.findByPrimaryKey3(article_no);
//	}
	
	public List<AlbumVO> getAll(Integer mem_no) {
		return dao.getAll(mem_no);
	}
	
//	public ArticleVO updateArticleClick(Integer article_no,Integer article_click){
//		ArticleVO articleVO = new ArticleVO();
//
//		articleVO.setArticle_no(article_no);
//		articleVO.setArticle_click(article_click);
//		dao.updateArticleClick(articleVO);
//		
//		return articleVO;
//	}

}

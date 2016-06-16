package com.article.model;

import java.util.*;

public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);
    public void update(ArticleVO articleVO);
    public void delete(Integer article_no);
    public ArticleVO findByPrimaryKey(Integer article_no);
    public List<ArticleVO> findByPrimaryKey2(Integer mem_no);
    public ArticleVO findByPrimaryKey3(Integer article_no);
    public List<ArticleVO> getAll();
    public void updateArticleClick(ArticleVO articleVO);
	public void updateArticleRepliesNum(ArticleVO articleVO);
	
    public List<ArticleVO> ListArticleByStore_no(Integer store_no);
    
}

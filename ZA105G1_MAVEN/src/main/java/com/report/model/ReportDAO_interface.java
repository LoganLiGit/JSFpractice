package com.report.model;

import java.util.*;

public interface ReportDAO_interface {
	public void insert1(ReportVO ReportVO);
	public void insert2(ReportVO ReportVO);
	public void insert3(ReportVO ReportVO);
    public void update(ReportVO ReportVO);
    public void delete(Integer report_no);
    public ReportVO findByPrimaryKey(Integer report_no);
    public List<ReportVO> getAll();
    public void deleteReportByArticle_no(Integer article_no);
}

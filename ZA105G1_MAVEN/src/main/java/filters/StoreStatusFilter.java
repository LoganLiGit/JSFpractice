package filters;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;
import com.store.model.StoreVO;

public class StoreStatusFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		
		StoreVO storeVO = (StoreVO)session.getAttribute("storeVO");
		if (storeVO!= null){
			Integer store_state = storeVO.getStore_state();

					if ( store_state==1 ) {
						res.sendRedirect(req.getContextPath()+"/front/error/error.jsp");
						return;
					} else {
						chain.doFilter(request, response);
					}
		}else {
			chain.doFilter(request, response);
		}
		
	}
}
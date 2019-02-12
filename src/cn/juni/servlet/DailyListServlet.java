package cn.juni.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import cn.juni.dao.IDaily;
import cn.juni.pojo.Daily;
import cn.juni.pojo.DailyCustom;
import cn.juni.util.SqlSessionUtil;

@WebServlet("/dailyListServlet")
public class DailyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SqlSession sqlSession = null;
	private IDaily iDaily = null;
	
    public DailyListServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pageI = request.getParameter("pageIndex");
		
		sqlSession = SqlSessionUtil.getSqlSession();
    	iDaily = sqlSession.getMapper(IDaily.class);
		
		int pageIndex = 1;
		int pageSize = 5;
		
		if(pageI!=null && pageI!="") {
			pageIndex = Integer.parseInt(pageI);
		}
		
		DailyCustom dailyCustom = new DailyCustom();
		
		int count = iDaily.queryCount();
		List<Daily> dailyList = iDaily.queryAllDailyByPage((pageIndex-1)*pageSize, pageSize);
		
		dailyCustom.setPageIndex(pageIndex);
		dailyCustom.setPageSize(pageSize);
		dailyCustom.setCount(count);
		dailyCustom.setDailyList(dailyList);
		
		request.setAttribute("dailyCustom", dailyCustom);
		request.getRequestDispatcher("daily-list.jsp").forward(request, response);
		
	}

}

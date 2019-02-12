package cn.juni.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import cn.juni.dao.IDaily;
import cn.juni.pojo.Daily;
import cn.juni.util.SqlSessionUtil;

/**
 * Servlet implementation class DailyDescServlet
 */
@WebServlet("/dailyDescServlet")
public class DailyDescServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SqlSession sqlSession = null;
	private IDaily iDaily = null;
	
    public DailyDescServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		sqlSession = SqlSessionUtil.getSqlSession();
    	iDaily = sqlSession.getMapper(IDaily.class);
		
		String param = request.getParameter("did");
		Daily daily = null;
		if(param != null) {
			daily = iDaily.queryDescById(Integer.parseInt(param));
		}
		request.setAttribute("descDaily", daily);
		request.getRequestDispatcher("daily-desc.jsp").forward(request, response);
	}
}

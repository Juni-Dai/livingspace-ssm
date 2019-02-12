package cn.juni.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;

import cn.juni.dao.IDaily;
import cn.juni.util.SqlSessionUtil;

/**
 * Servlet implementation class DailyDelServlet
 */
@WebServlet("/dailyDelServlet")
public class DailyDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SqlSession sqlSession = null;
	private IDaily iDaily = null;
	
    public DailyDelServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		sqlSession = SqlSessionUtil.getSqlSession();
    	iDaily = sqlSession.getMapper(IDaily.class);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		String[] delIds = request.getParameterValues("delId");
		
		int result = iDaily.deleteDailyById(delIds);
		
		if(result>0) {
				map.put("code", 0);
				map.put("msg", "删除成功");
				map.put("count", 0);
			}else {
				map.put("code", 500);
				map.put("msg", "服务器异常，删除失败");
				map.put("count", 0);
			}
		
		SqlSessionUtil.closeSqlSession(sqlSession);
		out.print(JSON.toJSON(map));
		out.close();
	}

}

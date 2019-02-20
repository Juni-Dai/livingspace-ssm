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
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.juni.dao.IUser;
import cn.juni.pojo.User;
import cn.juni.util.SqlSessionUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUser iUser = null;
	private SqlSession sqlSession = null;
       
    public UserServlet() {
    	sqlSession = SqlSessionUtil.getSqlSession();
		iUser = sqlSession.getMapper(IUser.class);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String uinfo = request.getParameter("uinfo");
		JSONObject jsonObject = JSON.parseObject(uinfo);
		String uname = jsonObject.getString("uname");
		String upwd = jsonObject.getString("upwd");
		
		User user = iUser.login(new User(uname,upwd));
		
		Map<String,Object> map = new HashMap<String,Object>();
		session.setAttribute("user", user);
		if(user != null) {
			map.put("count", 1);
			map.put("data", user);
		}else {
			map.put("count", 0);
		}
		map.put("code", 0);
		map.put("msg", "");
		SqlSessionUtil.closeSqlSession(sqlSession);
		out.print(JSON.toJSON(map));
		out.close();
	}

}

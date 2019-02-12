package cn.juni.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import cn.juni.dao.IDaily;
import cn.juni.pojo.Daily;
import cn.juni.pojo.User;
import cn.juni.util.SqlSessionUtil;

/**
 * Servlet implementation class DailyAddServlet
 */
@WebServlet("/dailyAddServlet")
public class DailyAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSession sqlSession = null;
	private IDaily iDaily = null;
       
    public DailyAddServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		sqlSession = SqlSessionUtil.getSqlSession();
    	iDaily = sqlSession.getMapper(IDaily.class);
    	
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String dailyJson = request.getParameter("daily");
		
		JSONObject jsonObject = JSON.parseObject(dailyJson);
		String title = jsonObject.getString("title");
		String context = jsonObject.getString("context");
		String records = jsonObject.getString("records");
		
		Daily daily = new Daily();
		daily.setTitle(title);
		daily.setContext(context);
		daily.setUid(user.getUid());
		daily.setRecords(records);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			daily.setCreatetime(df.parse(df.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int result = iDaily.insertDaily(daily);
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(result>0) {
			map.put("code", 0);
			map.put("msg", "添加成功");
			map.put("count", 0);
		}else {
			map.put("code", 500);
			map.put("msg", "服务器异常，添加失败");
			map.put("count", 0);
		}
		SqlSessionUtil.closeSqlSession(sqlSession);
		response.getWriter().print(JSON.toJSON(map));
		response.getWriter().close();
	}

}

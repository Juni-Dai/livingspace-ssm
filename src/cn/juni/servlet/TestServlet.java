package cn.juni.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cn.juni.pojo.Daily;
import cn.juni.service.impl.DailyServiceImpl;

/**
 * Servlet implementation class TestServlet
 */
@ContextConfiguration(locations="classpath:ApplicationContext.xml")
@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	DailyServiceImpl dailyService;
	
	/**
	* 启动初始化上下文
	*/
	@Override
	public void init() throws ServletException {
	ServletContext application = this.getServletContext();
	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, application);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		Daily daily = new Daily();
		daily.setTitle("aaa");
		daily.setContext("sadsadsad");
		daily.setUid(2);
		daily.setCreatetime(new java.sql.Date(new Date().getTime()));
		System.out.println(dailyService.insertDaily(daily));
	}
}

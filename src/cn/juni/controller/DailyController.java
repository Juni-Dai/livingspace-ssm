package cn.juni.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.juni.pojo.Daily;
import cn.juni.pojo.DailyCustom;
import cn.juni.pojo.User;
import cn.juni.service.DailyService;

@Controller
public class DailyController {

	@Autowired
	DailyService dailyService;
	
	@RequestMapping("/queryAllDaily")
	public String queryAllDaily(Model model) {
		List<Daily> dailyList = dailyService.queryAllDaily();
		model.addAttribute("dailyList", dailyList);
		return "show";
	}
	
	@RequestMapping("/dailyList")
	public String homeControll( int pageIndex, int pageSize,Model model) {
		DailyCustom dailyCustom = dailyService.queryAllDailyByPage(pageIndex, pageSize);
		model.addAttribute("dailyCustom", dailyCustom);
		return "daily-list";
	}
	
	@RequestMapping("/dailyDel")
	@ResponseBody
	public Object dailyDel(String[] delId) {
		Object obj = dailyService.deleteDailyById(delId);
		return obj;
	}
	
	@RequestMapping("/dailyAdd")
	@ResponseBody
	public Map<String,Object> dailyAdd(String daily,HttpSession session) {
		User user = (User) session.getAttribute("user");
		System.out.println(new Date()+"-->"+user);
		Daily insertDaily = JSON.parseObject(daily, Daily.class);
		insertDaily.setUid(user.getUid());
		Map<String,Object> map = dailyService.insertDaily(insertDaily);
		return map;
	}
	
	@RequestMapping("/dailyDesc")
	public String queryDailyById(int did,Model model) {
		Daily daily = dailyService.queryDescById(did);
		model.addAttribute("descDaily", daily);
		return "daily-desc";
	}
}

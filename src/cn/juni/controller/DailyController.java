package cn.juni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.juni.pojo.Daily;
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
}

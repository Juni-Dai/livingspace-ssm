package cn.juni.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.juni.dao.IDaily;
import cn.juni.pojo.Daily;
import cn.juni.pojo.DailyCustom;
import cn.juni.service.DailyService;

@Service
public class DailyServiceImpl implements DailyService{

	@Autowired
	IDaily idaily;
	
	@Override
	public Map<String,Object> insertDaily(Daily daily) {
		Map<String,Object> map = new HashMap<String,Object>();
		int result = idaily.insertDaily(daily);
		if(result>0) {
			map.put("code", 0);
			map.put("msg", "添加成功");
			map.put("count", 0);
		}else {
			map.put("code", 500);
			map.put("msg", "服务器异常，添加失败");
			map.put("count", 0);
		}
		System.out.println("-map->"+map);
		return map;
	}

	@Override
	public List<Daily> queryAllDaily() {
		return idaily.queryAllDaily();
	}

	@Override
	public DailyCustom queryAllDailyByPage(int pageIndex, int pageSize) {
		
		List<Daily> dailyList = idaily.queryAllDailyByPage((pageIndex-1)*pageSize, pageSize);
		int count = idaily.queryCount();
		DailyCustom dailyCustom = new DailyCustom();
		dailyCustom.setDailyList(dailyList);
		dailyCustom.setPageIndex(pageIndex);
		dailyCustom.setPageSize(pageSize);
		dailyCustom.setCount(count);
		return dailyCustom;
	}

	@Override
	public int queryCount() {
		return idaily.queryCount();
	}

	@Override
	public Object deleteDailyById(String[] dids) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		int result = idaily.deleteDailyById(dids);
		
		if(result>0) {
			map.put("code", 0);
			map.put("msg", "删除成功");
			map.put("count", 0);
		}else {
			map.put("code", 500);
			map.put("msg", "服务器异常，删除失败");
			map.put("count", 0);
		}
		return JSON.toJSON(map);
	}

	@Override
	public Daily queryDescById(int did) {
		Daily daily = idaily.queryDescById(did);
		return daily;
	}

}

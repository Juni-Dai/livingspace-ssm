package cn.juni.service;

import java.util.List;
import java.util.Map;

import cn.juni.pojo.Daily;
import cn.juni.pojo.DailyCustom;

public interface DailyService {

	public Map<String,Object> insertDaily(Daily daily);

	public List<Daily> queryAllDaily();
	
	public DailyCustom queryAllDailyByPage(int pageIndex,int pageSize);
	
	public int queryCount();
	
	public Object deleteDailyById(String[] dids);
	
	public Daily queryDescById(int did);
}

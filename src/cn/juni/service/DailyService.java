package cn.juni.service;

import java.util.List;

import cn.juni.pojo.Daily;

public interface DailyService {

	public int insertDaily(Daily daily);

	public List<Daily> queryAllDaily();
	
	public List<Daily> queryAllDailyByPage(int pageIndex,int pageSize);
	
	public int queryCount();
	
	public int deleteDailyById(String[] dids);
	
	public Daily queryDescById(int did);
}

package cn.juni.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.juni.dao.IDaily;
import cn.juni.pojo.Daily;
import cn.juni.service.DailyService;

@Service
public class DailyServiceImpl implements DailyService{

	public DailyServiceImpl() {
		System.out.println("--构造器已执行--");
	}
	
	@Autowired
	IDaily idaily;
	
	@Override
	public int insertDaily(Daily daily) {
		return idaily.insertDaily(daily);
	}

	@Override
	public List<Daily> queryAllDaily() {
		return idaily.queryAllDaily();
	}

	@Override
	public List<Daily> queryAllDailyByPage(int pageIndex, int pageSize) {
		return idaily.queryAllDailyByPage(pageIndex, pageSize);
	}

	@Override
	public int queryCount() {
		return idaily.queryCount();
	}

	@Override
	public int deleteDailyById(String[] dids) {
		return idaily.deleteDailyById(dids);
	}

	@Override
	public Daily queryDescById(int did) {
		return null;
	}

}

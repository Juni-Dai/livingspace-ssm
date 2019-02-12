package cn.juni.test;

import static org.junit.Assert.fail;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.juni.pojo.Daily;
import cn.juni.service.DailyService;
import cn.juni.service.impl.DailyServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:ApplicationContext.xml")
public class DailyServiceImplTest {

//	@Resource(name="dailyServiceImpl")
	@Autowired
	DailyServiceImpl dailyService;
	
/*	@Autowired
	IDaily idaily;*/
	
	@Test
	public void testInsertDaily() {
		Daily daily = new Daily();
		daily.setTitle("aaa");
		daily.setContext("sadsadsad");
		daily.setUid(2);
		daily.setCreatetime(new java.sql.Date(new Date().getTime()));
		System.out.println(dailyService.insertDaily(daily));
	}
	

	@Test
	public void testQueryAllDaily() {
		System.out.println(dailyService.queryAllDaily());
	}

	@Test
	public void testQueryAllDailyByPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDailyById() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryDescById() {
		fail("Not yet implemented");
	}

}

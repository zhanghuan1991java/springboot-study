package com.didispace.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

	Logger logger = LoggerFactory.getLogger(MyTask.class);
	/**
	 * Linux 的cron表达式   ：                    分  时  天  月 周
	 * 
	 * springboot批量的cron表达式：秒    分   时  天  月 周
	 */
	@Scheduled(cron="0 */5 * * * *")
	public void reportCurrentTime() {
		logger.info("5分钟批量执行——>当前时间："+new Date().toLocaleString());
	}

}

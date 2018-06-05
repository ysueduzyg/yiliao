package io.renren.modules.job.task;

/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.renren.modules.app.config.JPushUtil;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试定时任务(演示Demo，可删除)
 *
 * testTask为spring bean的名称
 *

 * @since 1.2.0 2016-11-28
 */
@Component("testTask")
public class TestTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private JPushUtil jPushUtil;
	
	public void test(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SysUserEntity user = sysUserService.selectById(1L);
		System.out.println(ToStringBuilder.reflectionToString(user));
		
	}

	public void  jPushManager(String json){
		Map<String,String> map = JSON.parseObject(json,Map.class);
		String title = map.get("title");
		String content = map.get("content");
		String rid = map.get("rid");
		List<String> list = new ArrayList<>();
		list.add(rid);
		Map<String,Object> mmap = new HashMap<>();
		mmap.put("m_id","10101010");
		mmap.put("content",content);
		JPushUtil.push("a","a","a","","","");
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}


}

package com.zuoxiaolong.reptile;

import com.zuoxiaolong.config.Configuration;
import com.zuoxiaolong.freemarker.Generators;
import com.zuoxiaolong.util.ImageUtil;
import org.apache.log4j.Logger;

/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author 左潇龙
 * @since 2015年5月12日 下午9:42:50
 */
public abstract class Reptiles {
	
	public static Thread newReptileThread() {
		return new CnblogsThread();
	}

	static class CnblogsThread extends Thread {

		private static final Logger logger = Logger.getLogger(CnblogsThread.class);
		
		private static final int THREAD_SLEEP_MINUTES = Integer.valueOf(Configuration.get("thread.sleep.minutes"));

		@Override
		public void run() {
			while (true) {
				try {
					ImageUtil.loadArticleImages();
					Cnblogs.fetchArticles();
					Generators.generate();
					Thread.sleep(1000 * 60 * THREAD_SLEEP_MINUTES);
				} catch (Exception e) {
					logger.warn("fetch and generate failed ...", e);
					break;
				}
			}
		}

	}

}

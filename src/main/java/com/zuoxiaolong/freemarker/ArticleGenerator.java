package com.zuoxiaolong.freemarker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zuoxiaolong.config.Configuration;
import com.zuoxiaolong.dao.ArticleDao;
import com.zuoxiaolong.dao.CommentDao;

import freemarker.template.Template;
import freemarker.template.TemplateException;

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
 * @since 5/7/2015 6:06 PM
 */
public class ArticleGenerator implements Generator {

	private static final Logger logger = Logger.getLogger(ArticleGenerator.class);

	@Override
	public void generate() {
		List<Map<String, String>> articles = ArticleDao.getArticles("create_date");
		for (int i = 0; i < articles.size(); i++) {
			generateArticle(Integer.valueOf(articles.get(i).get("id")), articles);
		}
	}

	void generateArticle(Integer id, List<Map<String, String>> articles) {
		Map<String, String> article = ArticleDao.getArticle(id);
		freemarker.template.Configuration configuration = Configuration.getFreemarkerConfiguration();
		Writer writer = null;
		try {
			Template template = configuration.getTemplate("article.ftl", "UTF-8");
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("article", article);
			data.put("accessCharts", ArticleDao.getArticles("access_times"));
			data.put("newCharts", articles);
			data.put("recommendCharts", ArticleDao.getArticles("good_times"));
			data.put("imageArticles", articles);
			data.put("comments", CommentDao.getComments(id));
			String htmlPath = Configuration.getContextPath("article_" + id + ".html");
			if (logger.isInfoEnabled()) {
				logger.info("htmlPath : " + htmlPath);
			}
			writer = new FileWriter(htmlPath);
			template.process(data, writer);
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}

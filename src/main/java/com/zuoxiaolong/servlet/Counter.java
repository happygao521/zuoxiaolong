package com.zuoxiaolong.servlet;

import com.zuoxiaolong.dao.ArticleDao;
import com.zuoxiaolong.freemarker.Generators;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
 * @since 5/8/2015 4:12 PM
 */
public class Counter extends BaseServlet {

	private static final long serialVersionUID = -2655585691431759816L;
	
	private static final Logger logger = Logger.getLogger(Counter.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer articleId = Integer.valueOf(request.getParameter("articleId"));
        String column = request.getParameter("column");
        if (logger.isInfoEnabled()) {
            logger.info("counter param : articleId = " + articleId + "   , column = " + column);
        }
        ArticleDao.updateCount(articleId, column);
        Generators.generate(articleId);
    }

}


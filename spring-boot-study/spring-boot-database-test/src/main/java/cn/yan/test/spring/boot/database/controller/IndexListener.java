package cn.yan.test.spring.boot.database.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created
 * User  wankunYan
 * Date  2018/4/20
 * Time  10:20
 */
@WebListener
public class IndexListener implements ServletContextListener {
    private Log log = LogFactory.getLog(IndexListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("IndexListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

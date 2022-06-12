package za.co.uyanda.interview.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import za.co.uyanda.interview.dbmanager.DbManager;

/**
 *
 * @author TRAIN 85
 */
@WebListener
public class MyContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String uri = sc.getInitParameter("uri");
        String database = sc.getInitParameter("database");
        String driver = sc.getInitParameter("driver");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");
        DbManager manager = new DbManager(uri,database,driver,username,password);
        manager.getConnection();
        sc.setAttribute("manager", manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        DbManager manager = (DbManager)sc.getAttribute("manager");
        if(manager!=null){
            manager.closeConnection();
        }
    }  
}

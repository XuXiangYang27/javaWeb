package Listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AListener implements ServletContextListener
{
    public AListener() 
    {
       
    }
    public void contextInitialized(ServletContextEvent arg0)
    {
        Map<String, Integer> map=new LinkedHashMap<String, Integer>();
        arg0.getServletContext().setAttribute("MAP", map);
    }
    public void contextDestroyed(ServletContextEvent arg0) 
    {
       
    }
}

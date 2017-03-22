package com.lcc.kingnod.pushlet;

import com.google.gson.Gson;
import com.lcc.kingnod.entity.Chat;
import com.lcc.kingnod.service.ChatService;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;

/**
 * Created by asus on 2017/3/22.
 */
@Component
public class PushletService extends HttpServlet {

    @Autowired
    private ChatService chatService;
    {
        WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
        //手动注入dao或者Service
        //taskDao =(TaskDao)applicationContext.getBean("taskDao");
        chatService =(ChatService)applicationContext.getBean("chatService");
    }

    /**
     * 获取所有未读的消息, 具体消息的过滤在页面上实现
     * @return 未读的消息
     * @throws UnsupportedEncodingException
     */
    public String findNotReadChat()throws UnsupportedEncodingException{
        final List<Chat> list = chatService.findNotReadChat();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    chatService.setReadFlag(list);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        Gson gson = new Gson();
        String message = gson.toJson(list);
        return URLEncoder.encode(message, "UTF-8");
    }

    public static class Pushlet extends EventPullSource{

        @Override
        protected long getSleepTime() {
            return 1000;
        }

        @Override
        protected Event pullEvent() {
            String message = "";
            try {
                message = (new PushletService()).findNotReadChat();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Event event = Event.createDataEvent("/zheng/hongwei");
            event.setField("mes",message);
            event.setField("time", Calendar.getInstance().getTime().getTime());
            return event;
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }
}

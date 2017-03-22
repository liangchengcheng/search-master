package com.lcc.kingnod.service;

import java.util.Date;
import java.util.List;
import com.lcc.kingnod.dao.ChatDao;
import com.lcc.kingnod.entity.Chat;
import com.lcc.kingnod.util.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("chatService")
@Transactional(readOnly=true)
public class ChatService {

    @Autowired
    private ChatDao chatDao;

    /**
     * 保存聊天
     */
    @Transactional(readOnly=false)
    public Chat saveChat(Chat chat){
        chat.setSendId(Users.userId());
        chat.setReadFlag("N");
        chat.setSendDate(new Date());
        return chatDao.save(chat);
    }

    /**
     * 修改聊天状态
     */
    @Transactional(readOnly=false)
    public Chat updateChat(Chat chat){
        chat.setReadFlag("Y");
        return chatDao.save(chat);
    }
    /**
     * 获取所有未读信息
     */
    public List<Chat> findNotReadChat(){
        return chatDao.findNotReadChat("N");
    }

    /**
     * 将未读数据设置为已读
     */
    @Transactional(readOnly=false,rollbackFor=Exception.class)
    public void setReadFlag(List<Chat> list){
        for(Chat chat : list){
            chat.setReadFlag("Y");
            chatDao.save(chat);
        }
    }
}


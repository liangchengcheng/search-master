package com.lcc.kingnod.redis;

import com.lcc.kingnod.entity.JcnUser;
import com.lcc.kingnod.tool.RedisKeyUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/3/21.
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Map<String, String>> redisTemplate;

    @Autowired
    private RedisTemplate<String, Long> redisLongTemplate;

    @Autowired
    private RedisTemplate<String, Integer> redisIntTemplate;

    @Autowired
    private RedisTemplate<String, String> RedisStringTemplate;

    @Autowired
    private RedisTemplate<String, JcnUser> redisObjectTemplate;

    public List<Long> putUserId(Long userId) {
        String key = RedisKeyUtil.getKey(RedisKeyUtil.CACHE_CPY, RedisKeyUtil.USER_ID);
        redisLongTemplate.opsForList().leftPush(key.toString(), userId);
        List<Long> list = redisLongTemplate.opsForList().range(key, 0,
                redisLongTemplate.opsForList().size(key));
        return list;
    }

    public List<String> findByKey(String key){
        List<String> list = RedisStringTemplate.opsForList().range(key, 0,RedisStringTemplate.opsForList().size(key));
        return null;
    }

    /**
     * 通过主键查找map
     */
    public Map<Object,Object> findValue(String key){
        JcnUser user = new JcnUser();
        user.setCreateDate(new Date());
        user.setLastUpdateDate(new Date());
        user.setName("lulu");
        user.setPassword("1234");
        Map map = new HashMap<String,String>();
        map.put("create_date", DateFormatUtils.format(user.getCreateDate(),"yyyy-MM-dd"));
        map.put("update_date", DateFormatUtils.format(user.getLastUpdateDate(),"yyyy-MM-dd HH:mm:ss"));
        map.put("name",user.getName());
        map.put("password",user.getPassword());
        redisTemplate.opsForHash().putAll(key, map);
        //redisObjectTemplate.opsForValue().set(key,user);保存对象，该对象将被编译成字符串保存在redis,但日期属性无法保存，估计是无法序列化
        //RedisStringTemplate.opsForValue().get(key);
        return	redisTemplate.opsForHash().entries(key);
    }
}

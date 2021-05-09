package com.jbf.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTest {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

//    操作String字符串类型数据时，使用RedisTemplate类中的opsForValue方法操作。

    @Test
    public void testOPsforvalue() {
        redisTemplate.opsForValue().set("str1", "12");
        redisTemplate.opsForValue().set("str1", "23");
        redisTemplate.opsForValue().set("str1", 12);
        String str[]=new String[]{"121","wo"};
        redisTemplate.opsForValue().set("str1", str);
        //String str1 = redisTemplate.opsForValue().get("str1", 0, -1);
        System.out.println(redisTemplate.opsForValue().get("str1"));
        //redisTemplate.opsForValue().set("res2",2,10, TimeUnit.SECONDS);
        //System.out.println(res1);
    }

//    操作List列表类型数据时，使用RedisTemplate类中的opsForList方法操作。

    @Test
    public void testOPsforList() {
        redisTemplate.delete("list01");
        redisTemplate.opsForList().leftPush("list01",69);
        redisTemplate.opsForList().leftPush("list01","23");
//        Long li = redisTemplate.opsForList().size("li");
//        System.out.println(li);
//
        String[] str = new String[]{"sz1", "sz2", "sz3"};
        redisTemplate.opsForList().leftPush("list01",str);
        redisTemplate.opsForList().leftPushAll("list01",str);
        Map<String, Object> map = new HashMap<>();
        map.put("123", "23");
        map.put("233", 233);
        redisTemplate.opsForList().leftPush("list01",map);
        redisTemplate.opsForList().leftPush("list01",map);


        List<Object> list01 = redisTemplate.opsForList().range("list01", 0, -1);
        System.out.println(list01);
//        redisTemplate.opsForList().leftPush("op",map);
//        redisTemplate.opsForList().leftPushAll("op1",map);
//        Long mi = redisTemplate.opsForList().leftPush("mi", str);
//        System.out.println(mi);
//        Long ni = redisTemplate.opsForList().leftPushAll("ni", str);
//        System.out.println(ni);
//        System.out.println(redisTemplate.opsForList().range("ni", 0, -1));
//        System.out.println(redisTemplate.opsForList().leftPop("op1"));
//        System.out.println(redisTemplate.opsForList().range("op", 0, -1));
//        redisTemplate.opsForList().set("op",1,"2222");
//        System.out.println(redisTemplate.opsForList().range("op", 0, -1));
        //Long op = redisTemplate.opsForList().remove("op", 0, map);
//        System.out.println(redisTemplate.opsForList().range("op", 0, -1));
    }

    //    操作Set集合类型数据时，使用RedisTemplate类中的opsForSet方法操作。
    @Test
    public void testOPsforset() {
        redisTemplate.delete("set01");
        redisTemplate.opsForSet().add("set01","1");
        redisTemplate.opsForSet().add("set01",3);
        redisTemplate.opsForSet().add("set01","1");
        //redisTemplate.opsForSet().remove("yjk1","2323322323");
        String str[]=new String[]{"121","wo"};
        redisTemplate.opsForSet().add("set01",str);
        Set<Object> set01 = redisTemplate.opsForSet().members("set01");
        Iterator<Object> iterator = set01.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        Cursor<Object> set02 = redisTemplate.opsForSet().scan("set01", ScanOptions.NONE);
        while(set02.hasNext()){
            System.out.println(set02.next());
        }
//        Object yjk11 = redisTemplate.opsForSet().pop("yjk1");
//        Set<Object> yjk2 = redisTemplate.opsForSet().members("yjk1");
//        Iterator<Object> iterator1 = yjk2.iterator();
//        while (iterator1.hasNext()) {
//            System.out.println(iterator1.next());
//        }
    }

    //    操作Hash哈希类型数据时，使用RedisTemplate类中的opsForHash方法操作。
    @Test
    public void testOPsforhash() {
        Boolean hash01 = redisTemplate.delete("hash01");
        redisTemplate.opsForHash().put("hash01","name","lxr");
        Map<String, Object> map = new HashMap<>();
        map.put("123", "23");
        map.put("233", 233);
        redisTemplate.opsForHash().putAll("hash01",map);
        Map<Object, Object> hash011 = redisTemplate.opsForHash().entries("hash01");
        System.out.println(hash011);
        Cursor<Map.Entry<Object, Object>> it = redisTemplate.opsForHash().scan("hash01",ScanOptions.NONE);
        while(it.hasNext()){
            Map.Entry<Object, Object> next = it.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }
//       redisTemplate.opsForHash().delete("hash1","lxr");
//       redisTemplate.opsForHash().put("lxr","name","lxr");
//       redisTemplate.opsForHash().put("lxr","age",20);
//       redisTemplate.opsForHash().put("lxr","sex","nv");
//        redisTemplate.opsForHash().putAll("yjk", map);
//        System.out.println(redisTemplate.opsForHash().hasKey("lxr", "name"));
//        System.out.println(redisTemplate.opsForHash().entries("lxr"));
//        System.out.println(redisTemplate.opsForHash().size("lxr"));
//        System.out.println(redisTemplate.opsForHash().values("lxr"));
    }

    //    操作Zset有序集合类型数据时，使用RedisTemplate类中的opsForZSet方法操作。
    @Test
    public void testOPsforzset() {
        redisTemplate.delete("zset1");
        redisTemplate.opsForZSet().add("zset1", 2, 1);
        redisTemplate.opsForZSet().add("zset1", "2", 1);
        redisTemplate.opsForZSet().add("zset1", "cnm", 10);
        redisTemplate.opsForZSet().add("zset1", "cnm", 10);
        Set<Object> zset11 = redisTemplate.opsForZSet().range("zset1", 0, -1);
        System.out.println(zset11);
        Long zset1 = redisTemplate.opsForZSet().count("zset1", 0, 2);
        System.out.println(zset1);
        Cursor<ZSetOperations.TypedTuple<Object>> zset12 = redisTemplate.opsForZSet().scan("zset1", ScanOptions.NONE);
        while (zset12.hasNext()){
            ZSetOperations.TypedTuple<Object> next = zset12.next();

            System.out.println(next.getScore()+""+next.getValue());
        }
    }

}
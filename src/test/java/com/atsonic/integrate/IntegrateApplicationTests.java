package com.atsonic.integrate;

import com.atsonic.integrate.modules.moduleA.entity.Article;
import com.atsonic.integrate.modules.moduleA.entity.Book;
import com.atsonic.integrate.modules.moduleA.dao.MyuserMapper;
import com.atsonic.integrate.modules.moduleA.entity.Myuser;
import com.atsonic.integrate.modules.moduleA.task.AsyncService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrateApplicationTests {

    @Autowired
    MyuserMapper myuserMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    JestClient jestClient;
//    @Autowired
//    ESBookRepository esBookRepository;

    @Autowired
    AsyncService asyncService;

    @Autowired
    JavaMailSenderImpl mailSender;


    /**
     * ======================= 测试 MybatisPlus ====================================
     */

    @Test
    public void testMyBatisPlus() {
        System.out.println(("----- selectAll method test ------"));
////        List<Myuser> userList = myuserMapper.selectList(null);
////        Assert.assertEquals(5, userList.size());
////        userList.forEach(System.out::println);

        List<Myuser> myusers = myuserMapper.selectList(new EntityWrapper<Myuser>());
        myusers.stream().sorted(Comparator.comparing(Myuser::getAge).reversed()).forEach(System.out::println);
    }

    /**
     * ======================= 测试 Redis ====================================
     */

    /**
     * Redis常见的五大数据类型
     *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     *  stringRedisTemplate.opsForValue()[String（字符串）]
     *  stringRedisTemplate.opsForList()[List（列表）]
     *  stringRedisTemplate.opsForSet()[Set（集合）]
     *  stringRedisTemplate.opsForHash()[Hash（散列）]
     *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    public void testRedis01(){
        //给redis中保存数据
        stringRedisTemplate.opsForValue().append("msg","hello");
//		String msg = stringRedisTemplate.opsForValue().get("msg");
//		System.out.println(msg);

//		stringRedisTemplate.opsForList().leftPush("mylist","1");
//		stringRedisTemplate.opsForList().leftPush("mylist","2");
    }

    //测试保存对象
    @Test
    public void testRedis02(){
        Myuser myuser = myuserMapper.selectById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        //redisTemplate.opsForValue().set("emp-01",empById);
        //1、将数据以json的方式保存
        //(1)自己将对象转为json
        //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
//        userRedisTemplate.opsForValue().set("myuser-01",myuser);
    }


    /**
     * ======================= 测试 amqp ====================================
     */
    /**
     * 自动配置
     *  1、RabbitAutoConfiguration
     *  2、有自动配置了连接工厂ConnectionFactory；
     *  3、RabbitProperties 封装了 RabbitMQ的配置
     *  4、 RabbitTemplate ：给RabbitMQ发送和接受消息；
     *  5、 AmqpAdmin ： RabbitMQ系统管理功能组件;
     *  	AmqpAdmin：创建和删除 Queue，Exchange，Binding
     *  6、@EnableRabbit +  @RabbitListener 监听消息队列的内容
     */

    @Test
    public void createExchange(){

//		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		System.out.println("Exchange创建完成");

//		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
//        System.out.println("Queue创建完成");

        //创建绑定规则
//		amqpAdmin.declareBinding(
//		        new Binding("amqpadmin.queue"
//                        , Binding.DestinationType.QUEUE
//                        ,"amqpadmin.exchange"
//                        ,"amqp.haha"
//                        ,null));
//        System.out.println("绑定完成");

        // 类似地，也有相应删除操作。。

    }


    /**
     * 1、单播（点对点）
     */
    @Test
    public void testAmqp() {
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("helloworld",123,true));
//        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);

        // javaBean被默认序列化以后发送出去（需要自定义 MessageConverter，指定 Jackson2JsonMessageConverter）
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));

    }

    //接受数据,如何将数据自动的转为json发送出去（消费掉指定queue中的数据）
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦2","曹雪芹"));
    }

    /**
     * ======================= 测试 ElasticSearch ====================================
     */
    @Test
    public void testElasticSearch() {
        //1、给Es中索引（保存）一个文档；
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("zhangsan");
        article.setContent("Hello World");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("atsonic").type("news").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试搜索
    @Test
    public void search(){

        //查询表达式
        String json ="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        //更多操作：https://github.com/searchbox-io/Jest/tree/master/jest
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("atsonic").addType("news").build();

        //执行
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




//    @Test
//    public void testSpringDataES(){
//		ESBook esBook = new ESBook();
//        esBook.setId(1);
//        esBook.setBookName("西游记");
//        esBook.setAuthor("吴承恩");
//        esBookRepository.index(esBook);
//
//
////        for (ESBook esBook : esBookRepository.findByBookNameLike("游")) {
////            System.out.println(esBook);
////        }
//
//    }


    /**
     * ======================= 测试 异步 ====================================
     */
    @Test
    public void testAsync(){
        asyncService.hello();
        System.out.println("方法执行结束");
    }

    /**
     * ======================= 测试 邮件 ====================================
     */
    @Test
    public void testEmail01() {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");

        message.setTo("378242944@qq.com");
        message.setFrom("852478424@qq.com");

        mailSender.send(message);
    }

    @Test
    public void testEmail02() throws  Exception{
        //1、创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);

        helper.setTo("378242944@qq.com");
        helper.setFrom("852478424@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("E:\\1.jpg"));
        helper.addAttachment("2.jpg",new File("E:\\2.jpg"));

        mailSender.send(mimeMessage);

    }

    /**
     * ======================= 测试 mongodb ====================================
     */

    /**
     * 带条件分页倒排序查询
     * 直接copy的例子
     * 伪代码
     *
     * @param userid
     * @param pageSize
     * @param pageNum
     * @return
     * @throws Exception
     */
    /*public List<ConfReport> handleConfReportList(String userid, Integer pageSize, Integer pageNum) throws Exception {
        Query query = new Query(Criteria.where("userid").is(userid))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .with(new Sort(Sort.Direction.DESC, "create_time"));
        return mongoDBDao.find(query, ConfReport.class, "conf_report");
    }*/

    /**
     * 根据条件更新mongodb中对应的collection
     * 伪代码
     */
    /*public void updateMongodb(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        String confStatus = "01";
        update.set("conf_status", confStatus);
        mongoDBDao.update(query, update, ConfReport.class);
    }*/

}

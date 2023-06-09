package com.qcby;

import com.qcby.dao.UserDao;
import com.qcby.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {
    private InputStream in = null;//输入流
    private SqlSession session = null;//数据库会话
    private UserDao mapper = null;//mapper-->映射

    @Before  //前置通知, 在方法执行之前执行
    public void init() throws IOException {
        //加载主配置文件，目的是为了构建SqlSessionFactory对象
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //通过SqlSessionFactory工厂对象创建SqlSesssion对象
        session = factory.openSession();
        //通过Session创建UserDao接口代理对象
        mapper = session.getMapper(UserDao.class);
    }

    @After  //@After: 后置通知, 在方法执行之后执行 。
    public void destory() throws IOException {
        //释放资源
        session.close();
        in.close();
    }

    @Test
    public void findAll() throws IOException {
        List<User> users = mapper.findAll();
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    /**
     * 根据id查询
     */
    @Test
    public void findById(){
        //不用保持一致
        User user =mapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void insert(){
        User user =new User();
        user.setBirthday(new Date());
        user.setAddress("印度");
        user.setSex("男");
        user.setUsername("刘墉");
        //提交
        int count =mapper.insert(user);
        session.commit();
        System.out.println(count);
    }

    @Test
    public void delete(){
        int count =mapper.delete(1);
        session.commit();
        System.out.println(count);

    }

    @Test
    public void update(){
        User user =new User();
        user.setBirthday(new Date());
        user.setAddress("北京");
        user.setSex("男");
        user.setUsername("阿吴");
        user.setId(5);
        int count = mapper.update(user);
        session.commit();
        System.out.println(count);

    }




}
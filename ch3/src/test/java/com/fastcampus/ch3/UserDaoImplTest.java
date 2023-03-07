package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class) //SJUnit4라는 클래스를 이용하여 Test를 한다(manven) 또한 Test를 할때마다 ac를 생성하지 않아도 된다
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // 테스트에 사용할 테스트 설정파일을 지정

public class UserDaoImplTest {
    @Autowired
    UserDao userDao;

    @Test
    public void deleteUser() {
    }

    @Test
    public void selectUser() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2021,1,1);

       userDao.deleteAll();
        User user = new User("asdf", "1234","abc","aaa@aaa.com",new Date(cal.getTimeInMillis()),"facebook",new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt==1);
        user.setPwd("4321");
        user.setEmail("bbb@Bbb.com");
        rowCnt = userDao.updateUser(user);
        assertTrue(rowCnt==1);

        User user2 = userDao.selectUser(user.getId());

        System.out.println("user = " + user);
        System.out.println("user2 = " + user2);
        assertTrue(user.equals(user2));




    }
}
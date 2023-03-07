package com.fastcampus.ch3;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //SJUnit4라는 클래스를 이용하여 Test를 한다(manven) 또한 Test를 할때마다 ac를 생성하지 않아도 된다
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // 테스트에 사용할 테스트 설정파일을 지정
public class DBConnectionTest2Test {
      @Autowired
      DataSource ds; //ds는 인스턴스 변수 그런데 JUnit의 각 테스트 테스트메서드 하나하나를 별도의 객체에서 실행시킨다
                     // 인스턴스 변수이긴 해도 같은 클래스의 테스트메서드들이 ds를 공유하지 않는다

        @Test
      public void insertUserTest() throws Exception {
            User user = new User("asdf2","1234","abc","aaa@aaa.com",new Date(),
                                "facebook",new java.util.Date());
            deleteAll();
            int rowCnt = insertUser(user);
            assertTrue(rowCnt==1);
        }

        @Test
        public  void selectUserTest() throws Exception {
            deleteAll();
            User user = new User("asdf2","1234","abc","aaa@aaa.com",new Date(),
                    "facebook",new java.util.Date());
            int rowCnt = insertUser(user);
            User user2 = selectUser("asdf2");
            assertTrue(user.getId().equals(user2.getId()));
        }

        @Test
        public void deleteUserTest()throws Exception{
            deleteAll();
            int rowCnt = deleteUser("asdf");
            assertTrue(rowCnt==0);

            User user = new User("asdf2","1234","abc","aaa@aaa.com",new Date(),
                    "facebook",new java.util.Date());
             rowCnt = insertUser(user);
            assertTrue(rowCnt==1);

            rowCnt = deleteUser((user.getId()));
            assertTrue(rowCnt==1); //삭제된 문장이 1이라는 뜻

            assertTrue(selectUser(user.getId())==null);


        }















////매개변수로 받은 사용자 정보로 user_info 테이블을 update하는 메서드
//    public int updateUser(User user)throws Exception{
//            return 0;
//    }






        public int deleteUser(String id) throws Exception{
            Connection conn = ds.getConnection();

            String sql = "delete from user_info where id= ?";

            PreparedStatement pstmt= conn.prepareStatement(sql); //SQL Injection 공격을 방어할 수 있다, 재사용을 통한 성능향상
            pstmt.setString(1, id);

//            int rowCnt = pstmt.executeUpdate(); //insert, delete, update할때 사용한다
//            return rowCnt;
            return pstmt.executeUpdate(); // 위 두줄을 하나로 축소
        }


        public User selectUser(String id) throws Exception{
            Connection conn = ds.getConnection();

            String sql = "select * from user_info where id= ?";

            PreparedStatement pstmt= conn.prepareStatement(sql); //SQL Injection 공격을 방어할 수 있다, 재사용을 통한 성능향상
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery(); //select문일때는 excuteQuery이며 ResultSet을 사용해 테이블로 받아온다


            if(rs.next()){
                User user = new User();
                user.setId(rs.getString(1));
                user.setPwd(rs.getString(2));
                user.setName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setBirth(new Date(rs.getDate(5).getTime()));
                user.setSns(rs.getString(6));
                user.setReg_date(rs.getDate(7));

                return user;
            }
            return null;
        }

    private void deleteAll()throws Exception{
        Connection conn = ds.getConnection();

        String sql = "delete from user_info";

        PreparedStatement pstmt= conn.prepareStatement(sql); //SQL Injection 공격을 방어할 수 있다, 재사용을 통한 성능향상
        pstmt.executeUpdate(); //insert, delete, update할때 사용한다
    }


    @Test
    public void transactionTest() throws Exception {
            Connection conn =null;
        try {

            deleteAll();
            conn = ds.getConnection();
            conn.setAutoCommit(false); //기본이 true이기 때문에 여러명령어로된 트랜잭션의 경우 처리할 수 없다?

            String sql = "insert into user_info  values (?,?,?,?,?,?,now())";

            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,"asdf"); //SQL Injection 공격을 방어할 수 있다, 재사용을 통한 성능향상
            pstmt.setString(2,"1234");
            pstmt.setString(3,"abc");
            pstmt.setString(4,"aaa@aaa.com");
            pstmt.setDate(5,new java.sql.Date(new Date().getTime()));
            pstmt.setString(6, "facebook");

            int rowCnt = pstmt.executeUpdate(); //insert, delete, update할때 사용한다

            pstmt.setString(1,"asdf");
            rowCnt = pstmt.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
        }

    }








    //사용자 정보를 user_info 테이블에 저장
   public int insertUser(User user) throws Exception {

       Connection conn = ds.getConnection();
       //       insert into user_info (id, pwd, name, email, birth, sns, reg_date)
       //       values ('asdf22','1234','smith','aaa@aaa.com','2023-03-05','facebook',now());

//       String sql = "insert into user_info  values ('asdf22','1234','smith','aaa@aaa.com','2023-03-05','facebook',now())";
//       String sql = "insert into user_info  values ('"+user.getId()+"','1234','smith','aaa@aaa.com','2023-03-05','facebook',now())";
       String sql = "insert into user_info  values (?,?,?,?,?,?,now())";

       PreparedStatement pstmt= conn.prepareStatement(sql);
       //PrepareedStatetment --
       //SQL Injection 공격을 방어할 수 있다, 재사용을 통한 성능향상
       pstmt.setString(1,user.getId());
       pstmt.setString(2,user.getPwd());
       pstmt.setString(3,user.getName());
       pstmt.setString(4,user.getEmail());
       pstmt.setDate(5,new java.sql.Date(user.getBirth().getTime()));
       pstmt.setString(6, user.getSns());

       int rowCnt = pstmt.executeUpdate(); //insert, delete, update할때 사용한다

       return rowCnt;
   }

//    @Autowired
//    ApplicationContext ac; //Jnuit4 클래스가 ac를 자동으로 만들기 때문에 생략가눙

    @Test
    public void springJDBCConnectionTest() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null); //괄호한의 조건식이 True면 테스트 성공, 아니면 실패
    }

}
package com.fastcampus.ch3;

import com.mysql.cj.xdevapi.Warning;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //SJUnit4라는 클래스를 이용하여 Test를 한다(manven) 또한 Test를 할때마다 ac를 생성하지 않아도 된다
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // 테스트에 사용할 테스트 설정파일을 지정
public class TxServiceTest {
    @Autowired
    TxService txService;

    @Test
    public  void insertA1WithoutTxTest()throws Exception{
        txService.insertA1WithTx();
    }

}
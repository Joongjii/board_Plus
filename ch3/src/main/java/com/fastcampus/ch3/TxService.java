package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired A1Dao a1Dao;
    @Autowired B1Dao b1Dao;

    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertA1WithTx() throws Exception {
        a1Dao.insert(1, 100);
        insertB1WithTx();
        a1Dao.insert(1, 200);
    }
   // @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertB1WithTx() throws Exception {
        b1Dao.insert(1, 100);
        b1Dao.insert(2, 200);
    }
//    conn = com.mysql.cj.jdbc.ConnectionImpl@62ddd21b
//    conn = com.mysql.cj.jdbc.ConnectionImpl@30b2b76f  -- conn이 다르다

    public void insertA1WithoutTx() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

    @Transactional(rollbackFor = Exception.class) // Exception을 rollback
//    @Transactional //RuntimeException, Error만 rollback을 한다
    public void insertA1WithTxFail() throws Exception {
        a1Dao.insert(1, 100); //성공
        //   throw new RuntimeException();
        //   throw new Exception(); //@Tx만 있는 line 주석해제하고 두 줄 비교해봐라
        a1Dao.insert(1, 200); //실패 -- 둘 다 안들어가야한다
    }

    @Transactional
    public void insertA1WithTxSuccess() throws Exception {
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }
}
package com.plantynet.tech2.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantynet.common.base.vo.DataTableOutput;
import com.plantynet.tech2.dao.TestDao;
import com.plantynet.tech2.service.TestService;
import com.plantynet.tech2.vo.TestVo;

@Service(value = "testService")
public class TestServiceImpl implements TestService
{
    //@Autowired -> 생성자 주입
    private TestDao testDao;
    
    //@Autowired -> spring 4.3+ 생성자 하나인 경우 자동으로 설정됨
    public TestServiceImpl(TestDao testDao)
    {
        this.testDao = testDao;
    }
    
    @Override
    public DataTableOutput<TestVo> selectTestList(TestVo testVo)
    {
        return new DataTableOutput<>(testVo, testDao.selectTestListTotalCnt(), testDao.selectTestListCnt(testVo), testDao.selectTestList(testVo));
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transactionTestInfo(TestVo testVo)
    {
        testDao.insertTestInfo(testVo);
        
        /*if( 1 == 1 )
        {
            throw new ProcessException("TEST");
        }*/
        
        testDao.deleteTestInfo(testVo.getStrId());
    }

    @Override
    public int selectTestListCnt(TestVo testVo)
    {
        return testDao.selectTestListCnt(testVo);
    }
    
    @Async
    @Override
    public void asyncTest()
    {
        System.out.println("###async service start###");
        
        try
        {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("###async service stop###");
    }
}
package com.plantynet.tech2.service;

import com.plantynet.common.base.vo.DataTableOutput;
import com.plantynet.tech2.vo.TestVo;

public interface TestService
{
    /*public abstract 생략됨*/
    DataTableOutput<TestVo> selectTestList(TestVo testVo);
    
    void transactionTestInfo(TestVo testVo);

    int selectTestListCnt(TestVo testVo);

    void asyncTest();

}

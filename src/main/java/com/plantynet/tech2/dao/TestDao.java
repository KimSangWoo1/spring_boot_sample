package com.plantynet.tech2.dao;

import java.util.List;

import com.plantynet.tech2.vo.TestVo;

public interface TestDao
{
    /*인터페이스는 public abstract 생략됨*/
    /**
     * 전체 사용자 목록 수
     * @return
     */
    int selectTestListTotalCnt();
    /**
     * 사용자 목록 수
     * @param testVo
     * @return
     */
    int selectTestListCnt(TestVo testVo);
    
    /**
     * 사용자 목록
     * @param testVo
     * @return
     */
    List<TestVo> selectTestList(TestVo testVo);
    
    /**
     * 사용자 등록
     * @param testVo
     */
    void insertTestInfo(TestVo testVo);
    
    /**
     * 선택한 사용자 삭제
     * @param strId
     */
    void deleteTestInfo(String strId);
}
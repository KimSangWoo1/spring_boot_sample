package com.plantynet.tech2.vo;

import com.plantynet.common.base.vo.BaseObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
public class TestVo extends BaseObject
{
    private String strId = "";
    private String strNm = "";
    private String sttus = "";
    private String rgsde = "";
    private String updde = "";
}
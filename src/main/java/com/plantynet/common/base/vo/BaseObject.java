package com.plantynet.common.base.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//https://kwonnam.pe.kr/wiki/java/lombok/pitfall //롬복 사용 주의 사항!!!
@Getter
@Setter
@ToString(exclude= {"columns", "order"})//toString에서 뺄 것 지정(by lombok)
//@JsonIgnoreProperties({"columns", "order", "search"})//json으로 생성될 때 뺄 것 지정 또는 field에 @JsonIgnore 지정(by jackson json)
public class BaseObject
{
    @JsonIgnore
    private String searchBgnDe;
    @JsonIgnore
    private String searchEndDe;
    @JsonIgnore
    private String searchCnd;
    @JsonIgnore
    private String searchWrd;
    @JsonIgnore
    private String sortColumn;
    @JsonIgnore
    private String sortOrder;

    //datatable용
    @JsonIgnore
    private int draw;
    @JsonIgnore
    private int start;
    @JsonIgnore
    private int length = 20;
    @JsonIgnore
    private List<Map<ColumnCriterias, String>> columns = new ArrayList<>();
    @JsonIgnore
    private List<Map<OrderCriterias, String>> order = new ArrayList<>();
    //@JsonIgnore
    //private Map<SearchCriterias, String> search = new HashMap<>();
    //페이지 번호로 호출할 경우
    public void setPage(int page)
    {
        this.start = (page - 1) * this.length;
    }
}
package com.plantynet.common.base.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataTable 용 ValueObject https://datatables.net/manual/server-side
 * @param <T>
 */
@Getter
@Setter
@ToString
public class DataTableOutput<T extends BaseObject>
{
    //data
    private T searchVo;
    private int draw;
    private int recordsTotal;//전체 목록 수
    private int recordsFiltered;//검색 목록 수
    private List<T> data;//페이징한 목록
    
    /**
     * 생성자
     * @param searchVo
     * @param recordsTotal
     * @param recordsFiltered
     * @param data
     */
    public DataTableOutput(T t, int recordsTotal, int recordsFiltered, List<T> data)
    {
        this.searchVo = t;
        this.draw = t.getDraw();
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }
    
    public int getPage()
    {
        return (this.searchVo.getStart()/this.searchVo.getLength() + 1);
    }
}

package com.plantynet.tech2.ctl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.plantynet.common.base.ctl.BaseController;
import com.plantynet.common.base.vo.ColumnCriterias;
import com.plantynet.common.base.vo.DataTableOutput;
import com.plantynet.common.base.vo.OrderCriterias;
import com.plantynet.common.exception.JsonException;
import com.plantynet.tech2.service.TestService;
import com.plantynet.tech2.vo.MailVo;
import com.plantynet.tech2.vo.TestVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic="SAMPLE")
@RestController
@RequestMapping(value = "/api/{apiVersion:v\\d+\\.\\d+}")
//@RequestMapping(value = "/api/v1.0")
public class RestApiController extends BaseController
{
    @Value("${custom.array}")//1, 2, 3 이렇게 넣어도 1/2/3 이렇게 알아서 공백 제거 하는 듯
    private String[] array;
    
    @Value("#{'${custom.array}'.split(',')}")//1, 2, 3이면 1/ 2/ 3 이렇게 공백이 그대로 들어가는 듯
    private List<String> list;
    
    @Value("${custom.systemProperties:기본값1}") 
    private String systemPropertiesYml;
    
    @Value("${JASYPT_PASSWORD:기본값2}")//java -jar {application}.jar --JASYPT_PASSWORD=someValue
    private String systemProperties;
    
    @Autowired
    private MailVo mailVo;
    
    //@Autowired -> 생성자 주입
    TestService testService;
    
    //@Autowired -> spring 4.3+ 생성자 하나인 경우 자동으로 설정됨
    public RestApiController(TestService testService)
    {
        this.testService = testService;
    }
    
    //@GetMapping(value = "/echo/{id}")
    @GetMapping(value = {"/echo", "/echo/{id}"})
    public ResponseEntity<?> echo(
            @PathVariable("apiVersion")String apiVersion,
            @PathVariable("id")Optional<String> id// -> JDK 8의 Optional 이용
            )
    {
        System.out.println(array);
        System.out.println(list);
        System.out.println(systemPropertiesYml);
        System.out.println(systemProperties);
        
        System.out.println(mailVo);
        
        Map<String, Object> map = new HashMap<>();
        
        log.info("{}, {}", apiVersion, id);
        
        map.put("apiVersion", apiVersion);
        map.put("id", id.orElse("안 왔다..."));
                
        return jsonResult(HttpStatus.OK, map);
    }
    
    @GetMapping(value = "/tests")
    @ResponseStatus(HttpStatus.OK)
    public List<TestVo> selectTestList(@ModelAttribute("search") TestVo testVo)
    {
        DataTableOutput<TestVo> dataTable = testService.selectTestList(testVo);
        
        return dataTable.getData();
    }
    
    @PostMapping(value = "/insert")
    @ResponseStatus(HttpStatus.OK)
    //public Map<String, String> insertTest(TestVo testVo) //일반 form 형식
    public Map<String, String> insertTest(@RequestBody TestVo testVo)//json 형식
    {
        Map<String, String> map = new HashMap<>();
        
        try
        {
            testService.transactionTestInfo(testVo);
            
            map.put("status", "success");
            map.put("message", "처리 되었습니다.");
        }
        catch(Exception e)
        {
            throw new JsonException(e.toString());
        }
        
        return map;
    }
    
    @GetMapping(value = "/testasync")
    @ResponseStatus(HttpStatus.OK)
    public String testAsync() 
    {
        System.out.println("###async controller start");
        
        testService.asyncTest();
        
        System.out.println("###async controller stop");
        
        return "async call";
    }
    
    @GetMapping(value = "/paging")
    @ResponseStatus(HttpStatus.OK)
    public DataTableOutput<TestVo> selectTestList2(TestVo testVo, Model model)
    {
        //멀티 정렬인 경우
        /*String sortOrder = "";
        List<Map<OrderCriterias, String>> orderList = testVo.getOrder();
        for(Map<OrderCriterias, String> item : orderList)
        {
            int index = Integer.parseInt(item.get(OrderCriterias.column));
            String dir = item.get(OrderCriterias.dir);
            
            String column = testVo.getColumns().get(index).get(ColumnCriterias.name);
            if(!StringUtil.isEmptyorNot(sortOrder))
            {
                sortOrder += ", ";
            }
            
            sortOrder += column + " " + dir.toUpperCase();
        }
        testVo.setSortOrder(sortOrder);*/
        //단일 정렬인 경우
        List<Map<OrderCriterias, String>> orderList = testVo.getOrder();
        for(Map<OrderCriterias, String> item : orderList)
        {
            int index = Integer.parseInt(item.get(OrderCriterias.column));
            String dir = item.get(OrderCriterias.dir);
            
            testVo.setSortColumn(testVo.getColumns().get(index).get(ColumnCriterias.name));
            testVo.setSortOrder(dir.toUpperCase());
        }
        
        DataTableOutput<TestVo> dataTable = testService.selectTestList(testVo);
        System.out.println("current page ==> " + dataTable.getPage());
        return dataTable;
    }
}
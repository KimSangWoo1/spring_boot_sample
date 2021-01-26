package com.plantynet.tech2.ctl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.plantynet.common.exception.JsonException;

//component scan으로 명시적으로 지정하지 않으려면 Application 하위 패키지에 넣어야 함. 안 그럼 인식 안 함.
@RestControllerAdvice(annotations = RestController.class)
//@ControllerAdvice(annotations = Controller.class) //일반 controller에 대해서 처리 시
//==> ResponseEntityExceptionHandler 상속 받아서 처리하는 방법도 있는데 그렇게 되면 405, 404 등에 대해서 처리가 가능해지지만 대신 json이냐 html이냐에 따라 다르게 처리해야 함
public class RestCommonExceptionAdvice
{
    //https://www.slipp.net/questions/600
    
    /**
     * json 에러 처러
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {JsonException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleJsonException(Exception ex)
    {
        Map<String, String> map = new HashMap<>();
        
        map.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        map.put("message", ex.getMessage());
        
        return map;
    }    
}

/*
 * @RestControllerAdvice 는 @ExceptionHandler, @ModelAttribute, @InitBinder가 적용된 메소드들을 AOP를 적용해
 * 컨트롤러 단에 적용하기 위헤 생긴 어노테이션임.
 * 
 * 여기선 @ExceptionHandler를 통해서 Exception 처리를 설정해줌 
 */
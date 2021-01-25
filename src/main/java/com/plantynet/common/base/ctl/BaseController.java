package com.plantynet.common.base.ctl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController
{
    /**
     * client ip를 가져온다.
     * @param req
     * @return
     */
    public String getClientIp(HttpServletRequest req)
    {        
        String clientIp = req.getHeader("X-FORWARDED-FOR");
        if(clientIp == null)
            clientIp = req.getRemoteAddr();
        else 
        {
            //X-Forwarded-For: <client>, <proxy1>, <proxy2>
            int idx = clientIp.indexOf(",");
            if (idx > -1) 
                clientIp = clientIp.substring(0, idx);
        }
        
        return clientIp;
    }
    
    /**
     * result json 생성(대부분 error에서 사용)
     * @param httpStatus
     * @return
     */
    public ResponseEntity<?> jsonResult(HttpStatus httpStatus)
    {
        Map<String, String> map = new HashMap<> ();
        
        map.put("status", String.valueOf(httpStatus.value()));
        map.put("message", httpStatus.name());
        
        return new ResponseEntity<>(map, httpStatus);
    }
    
    /**
     * result json 생성
     * @param httpStatus
     * @param map
     * @return
     */
    public ResponseEntity<?> jsonResult(HttpStatus httpStatus, Map<String, Object> map)
    {
        map.put("status", String.valueOf(httpStatus.value()));
        
        return new ResponseEntity<>(map, httpStatus);
    }
}

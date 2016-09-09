package com.dyb.platforms.fixfunds.services.utils.core.controller;

import com.dyb.platforms.fixfunds.services.utils.core.exception.DybExceptionCode;
import com.dyb.platforms.fixfunds.services.utils.core.exception.DybRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/3/2.
 */
public class BaseController {

    public Logger log = Logger.getLogger(BaseController.class);//日志

    protected ResponseEntity<Object> result(Object obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        Dto result = new BaseDto();
        result.put("result", obj);
        result.put("statusCode", DybExceptionCode.OK);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(result,headers, HttpStatus.OK);
        return responseEntity;
    }

    @ExceptionHandler
    public Object exception( HttpServletRequest request , Exception ex ) {
        Dto result = new BaseDto();
        if(ex instanceof DybRuntimeException){
            DybRuntimeException ycRuntimeException= (DybRuntimeException) ex;
            result.put("result", ycRuntimeException.getMessage());
            result.put("statusCode", ycRuntimeException.getExceptionCode());
            log.error("异常状态码："+ycRuntimeException.getExceptionCode()+"，异常信息："+ycRuntimeException.getMessage());
        }else {
            result.put("result", "系统错误");
            result.put("statusCode", DybExceptionCode.PROGRAM_EXCEPTION);
            log.error("异常状态码："+DybExceptionCode.PROGRAM_EXCEPTION+"，异常信息："+ex.getMessage());
        }
        return result;
    }

}

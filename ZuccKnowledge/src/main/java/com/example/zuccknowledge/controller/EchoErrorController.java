package com.example.zuccknowledge.controller;

import com.example.zuccknowledge.exception.EchoServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EchoErrorController {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleGlobleError(HttpServletRequest request, Exception ex) {
        //用Map容器返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("msg", "应用程序错误:" + ex.getMessage());
        ex.printStackTrace();
        return map;
    }

    @ExceptionHandler(EchoServiceException.class)
    @ResponseBody
    public Map<String, Object> handleEchoError(HttpServletRequest request, EchoServiceException ex) {
        //用Map容器返回信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("msg", ex.getMessage());
        ex.printStackTrace();
        return map;
    }
}

package com.example.demo.handler;

import com.example.demo.controller.UserController;
import com.example.demo.model.viewModel.MessageResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 该类放在单独一个文件夹
 * 只能捕捉进入controller里异常的代码。
 * <p>
 * extends ResponseEntityExceptionHandler
 */
@ControllerAdvice
//@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);


    /**
     * 404无法进入此方法。404被tomcat拦截了
     * 自定义返回数据格式
     */


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MessageResult<Void> exceptionHandler(Exception ex, WebRequest request) {
        MessageResult<Void> messageResult = new MessageResult<>();
        messageResult.setCode(500);
        messageResult.setMessage(ex.getMessage());
        messageResult.setSuccess(false);
//        return ResponseEntity.ok(messageResult);
        logger.error(ex.toString());
        return messageResult;
    }


}

package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 处理SQL异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        // Duplicate entry 'one' for key 'employee.idx_username'

        // 获取到异常是什么.用户名已存在,但重复输入用户名的异常
        String message = ex.getMessage();
        // 判断提取到的异常是否是包含内容Duplicate entry
        if (message.contains("Duplicate entry")) {
            // 如果包含,输出错误信息
            // 提取重复的用户名
            String[] s = message.split(" ");
            String username = s[2];
//            String mess = username + "用户已存在,请尝试其它用户名";
            // 为了规范,使用常量
            String mess = username + MessageConstant.ALREADY_EXITS;
            return Result.error(mess);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}

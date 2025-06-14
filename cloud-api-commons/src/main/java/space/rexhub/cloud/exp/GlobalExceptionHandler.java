package space.rexhub.cloud.exp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import space.rexhub.cloud.resp.R;
import space.rexhub.cloud.resp.ReturnCodeEnum;

/**
 * Description: 统一异常处理类
 *
 * @author Rex
 * @date 2025-05-23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有异常
     * @param e 异常
     * @return 统一返回值
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> exception(Exception e){
        log.error("GlobalExceptionHandler, error: {}", e.getMessage(), e);
        return R.error(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }
}

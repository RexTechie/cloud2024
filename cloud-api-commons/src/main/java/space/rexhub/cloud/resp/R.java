package space.rexhub.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Description: 统一定义返回对象
 *
 * @author Rex
 * @date 2025-05-23
 */
@Data
@Accessors(chain = true)
public class R<T> {

    private String code;

    private String message;

    private T data;

    private long timestamp;

    public R() {
        this.timestamp = System.currentTimeMillis();
    }


    public static <T> R<T> success(T data){
        R<T> result = new R<T>();
        result.setCode(ReturnCodeEnum.RC200.getCode());
        result.setMessage(ReturnCodeEnum.RC200.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> R<T> success(){
        return success(null);
    }

    public static <T> R<T> error(String code, String message){
        R<T> result = new R<T>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static <T> R<T> error(ReturnCodeEnum returnCodeEnum){
        R<T> result = new R<T>();
        result.setCode(returnCodeEnum.getCode());
        result.setMessage(returnCodeEnum.getMessage());
        result.setData(null);
        return result;
    }
}

package cn.yan.study.springboot.websocket.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : HEE
 * @date : 2019/1/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> {

    private T body;

    private Integer code;

    private String message;

    public BaseResult(T data) {
        body = data;
        code = 0;
        message = "";
    }

    public BaseResult(T data, String message) {
        body = data;
        code = 0;
        this.message = message;
    }

    public void makeSuccessResult() {
        code = 0;
        message = "success";
    }

    public void makeSuccessResult(T data) {
        body = data;
        code = 0;
        message = "success";
    }

    public void makeFailedResult() {
        code = -1;
        message = "failed";
    }

    public void makeFailedResult(String message) {
        code = -1;
        this.message = message;
    }

    public BaseResult makeFailedResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}

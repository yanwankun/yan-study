package cn.yan.study.springboot.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/

public class BaseResult<T> {

    private T body;

    private Integer code;

    private String message;

    public BaseResult() {
    }

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

    public BaseResult makeSuccessResult() {
        code = 0;
        message = "success";
        return this;
    }

    public BaseResult makeSuccessResult(T data) {
        body = data;
        code = 0;
        message = "success";
        return this;
    }

    public void makeFailedResult() {
        code = -1;
        message = "failed";
    }

    public BaseResult makeFailedResult(String message) {
        code = -1;
        this.message = message;
        return this;
    }

    public BaseResult makeFailedResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}

package cn.yan.study.websocket.mychat.result;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
public class ApiResult<T> {

    private T body;

    private Integer code;

    private String message;

    public ApiResult() {
    }

    public ApiResult(T data) {
        body = data;
        code = 0;
        message = "";
    }

    public ApiResult(T data, String message) {
        body = data;
        code = 0;
        this.message = message;
    }

    public ApiResult makeSuccessResult() {
        code = 0;
        message = "success";
        return this;
    }

    public ApiResult makeSuccessResult(T data) {
        body = data;
        code = 0;
        message = "success";
        return this;
    }

    public ApiResult makeFailedResult() {
        code = -1;
        message = "failed";
        return this;
    }

    public ApiResult makeFailedResult(String message) {
        code = -1;
        this.message = message;
        return this;
    }

    public ApiResult makeFailedResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}

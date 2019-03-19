package cn.yan.study.netty.response;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class HttpJsonResponse {
    private FullHttpResponse httpResponse;
    private Object result;

    public HttpJsonResponse(FullHttpResponse httpResponse, Object result) {
        this.httpResponse = httpResponse;
        this.result = result;
    }

    /**
     * @return the httpResponse
     */
    public final FullHttpResponse getHttpResponse() {
        return httpResponse;
    }

    /**
     * @param httpResponse the httpResponse to set
     */
    public final void setHttpResponse(FullHttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    /**
     * @return the body
     */
    public final Object getResult() {
        return result;
    }

    /**
     * @param result the body to set
     */
    public final void setResult(Object result) {
        this.result = result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpJsonResponse [httpResponse=" + httpResponse + ", result="
                + result + "]";
    }

}

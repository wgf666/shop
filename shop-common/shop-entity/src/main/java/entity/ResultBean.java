package entity;

import java.io.Serializable;

/**
 * @Author zzp
 * @Date 2020/3/11
 * Do:
 */
public class ResultBean<T> implements Serializable {
    private Integer statusCode;

    private T data;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultBean(Integer statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
    public ResultBean() {
    }
}

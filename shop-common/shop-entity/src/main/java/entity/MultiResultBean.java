package entity;

import java.io.Serializable;

/**
 * @Author zzp
 * @Date 2020/3/17
 * Do:
 */
public class MultiResultBean implements Serializable{
    private String statusCode;

    private String[] data;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}

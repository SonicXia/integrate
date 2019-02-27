package com.atsonic.integrate.common.pojo;

/**
 * 控制层响应通用对象。
 *
 * @param <V>
 */
public class Rsp<V> {

    // 错误码，0，1，2 .
    // 0表示操作成功，全部成功，1表示操作部分成功。
    // 当data是list时，可能出现数据部分成功的情况。2，操作失败，全部失败
    private int code;

    //错误详细描述
    private String message;

    //时间戳
    private long timestamp;

    //具体的业务数据。泛型表示
    private V data;

    public Rsp() {

    }

    /**
     * 正常结果返回
     *
     * @param data 服务查询结果数据
     * @return
     */
    public Rsp<V> success(V data) {
        setRspParam(OperResult.OPER_SUCCESS, data);
        return this;
    }

    public Rsp<V> success() {
        setRspParam(OperResult.OPER_SUCCESS, null);
        return this;
    }

    public Rsp<V> success(String message) {
        setRspParam(OperResult.OPER_SUCCESS, null, message);
        return this;
    }

    public Rsp<V> success(String message, V data) {
        setRspParam(OperResult.OPER_SUCCESS, data, message);
        return this;
    }

    public Rsp<V> fail() {
        setRspParam(OperResult.OPER_FAIL, null, "fail");
        return this;
    }

    public Rsp<V> fail(V payload) {
        setRspParam(OperResult.OPER_FAIL, payload);
        return this;
    }

    public Rsp<V> fail(String message) {
        setRspParam(OperResult.OPER_FAIL, null, message);
        return this;
    }

    public Rsp<V> fail(int errorCode, String message) {
        setRspParam(errorCode, null, message);
        return this;
    }

    private void setRspParam(int errorCode, V data) {
        this.setCode(errorCode);
        this.setData(data);
        this.setMessage("");
        this.setTimestamp(System.currentTimeMillis());
    }

    private void setRspParam(int errorCode, V data, String message) {
        this.setCode(errorCode);
        this.setData(data);
        this.setMessage(message);
        this.setTimestamp(System.currentTimeMillis());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Rsp{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}

package com.atsonic.integrate.common.pojo;



/**
 * 控制层响应通用对象。
 *
 * @param <V>
 */
public class BusRsp<V> {

    // 错误码，0，1，2 .
    // 0表示操作成功，全部成功，1表示操作部分成功。
    private int code;

    //错误详细描述
    private String message;

    private V data;

    public BusRsp() {

    }

    /**
     * 正常结果返回
     *
     * @param data 服务查询结果数据
     * @return
     */
    public BusRsp<V> success(V data) {
        setRspParam(OperResult.OPER_SUCCESS, data);
        return this;
    }

    public BusRsp<V> success() {
        setRspParam(OperResult.OPER_SUCCESS, null);
        return this;
    }

    public BusRsp<V> success(String message) {
        setRspParam(OperResult.OPER_SUCCESS, null, message);
        return this;
    }

    public BusRsp<V> success(String message, V data) {
        setRspParam(OperResult.OPER_SUCCESS, data, message);
        return this;
    }

    public BusRsp<V> fail() {
        setRspParam(OperResult.OPER_FAIL, null, "fail");
        return this;
    }

    public BusRsp<V> fail(V payload) {
        setRspParam(OperResult.OPER_FAIL, payload);
        return this;
    }

    public BusRsp<V> fail(String message) {
        setRspParam(OperResult.OPER_FAIL, null, message);
        return this;
    }

    public BusRsp<V> fail(int errorCode, String message) {
        setRspParam(errorCode, null, message);
        return this;
    }

    private void setRspParam(int errorCode, V data) {
        this.setCode(errorCode);
        this.setMessage("");
    }

    private void setRspParam(int errorCode, V data, String message) {
        this.setCode(errorCode);
        this.setMessage(message);
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

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BusRsp{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

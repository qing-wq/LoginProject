package com.example.login.pojo.vo;

/*消息模型对象
* 1.状态码
* 2.提示信息
* 3.回显数据
* */

public class MessageModel {
    private int StateCode;
    private String message;
    private Object data;

    public int getStateCode() {
        return StateCode;
    }

    public void setStateCode(int stateCode) {
        StateCode = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

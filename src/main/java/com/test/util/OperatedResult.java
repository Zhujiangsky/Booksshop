package com.test.util;

import java.io.Serializable;

public class OperatedResult implements Serializable{

    private boolean status=true;//表示成功,false表示失败
    private Object data;
    private String message="操作成功";

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * 设置成功状态
     */
    public OperatedResult setSuccess(String message,Object obj){
        this.status=true;
        this.message=message;
        this.data=obj;
        return this;
    }
    /**
     * 设置失败状态
     * @param message
     */
    public OperatedResult setFail(String message,Object obj){
        this.status=false;
        this.message=message;
        this.data=obj;
        return this;
    }
    public OperatedResult getResult() {
    	return this;
    }
    public OperatedResult(String message, boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public OperatedResult(){

    }
}

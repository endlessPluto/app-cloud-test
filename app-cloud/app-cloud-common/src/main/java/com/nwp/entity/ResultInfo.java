package com.nwp.entity;

/**
 * 返回结果信息
 *
 */
public class ResultInfo {
    private int code;//状态---成功为0，失败为1
    private String msg;//提示信息
    private Object data;//数据
    private int count;//总数据量
    public ResultInfo(int code,String msg,int count){
        this.code=code;
        this.msg=msg;
        this.count=count;
        this.data=new String[]{};
    }
    public ResultInfo(){
        this.code=code;
        this.msg=msg;
        this.data=data;
        this.count=count;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
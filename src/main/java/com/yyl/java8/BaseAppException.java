package com.yyl.java8;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: Prodigy</p>
 *
 * <p>Description: 基本异常类,所有的业务方法在发生异常时候都通过ExceptionHandler来

 * 发布该异常.</p>
 *
 * <p>Copyright: Copyright (c) 2005 </p>
 *
 * <p>Company: ztesoft</p>
 *
 * @author fan.zhenzhi
 * @version 0.1
 */
public class BaseAppException extends Exception implements Serializable {

    /** 异常标识. */
    private int id;

    /** 异常代码. */
    private String code;

    /** 异常描述. */
    private String desc;

    /** 异常发生时间. */
    private Date time;

    /** 错误堆栈. */
    private Throwable cause;

    /** 错误类型. */
    private int type;

    private String realMsg;

    /**
     * 默认构造器.
     */
    public BaseAppException() {
        super();
    }

    /**
     * 参数构造器.
     * @param msg String 错误消息
     */
    public BaseAppException(String msg) {
        super(msg);
        setRealMsg(msg);
    }

    /**
     * 参数构造器
     * @param code String 错误编码
     * @param msg String 错误消息
     */
    public BaseAppException(String code, String msg) {
        this.code = code;
        this.desc = msg;
    }


    /**
     * 设置错位消息标识.
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 设置错误编码.
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 设置错误描述信息.
     * @param desc String
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 设置错误发生时间.
     * @param time Date
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 设施错误类型.
     * @param type int
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 设置错误.
     * @param cause Throwable
     */
    public void setThrowable(Throwable cause) {
        this.cause = cause;
    }

    /**
     * 得到错误消息标识.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * 得到错误编码.
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * 得到错误描述.
     * @return String
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 得到错误发生时间.
     * @return Date
     */
    public Date getTime() {
        if (time == null) {
            time = new Date();
        }
        return time;
    }

    /**
     * 得到错误类型.
     * @return int
     */
    public int getType() {
        return type;
    }

    /**
     * 得到错误
     * @return Throwable
     */
    public Throwable getCause() {
        return cause == this ? null : cause;
    }

    public String getRealMsg() {
        return realMsg;
    }

    public void setRealMsg(String realMsg) {
        this.realMsg = realMsg;
    }
}

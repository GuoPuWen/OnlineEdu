package com.guli;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/9/29 22:35
 */
@Data
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //不让外部new对象
    private R(){}

    public static R ok(){
        R r = new R();
        r.setMessage("成功");
        r.setCode(ResultCode.SUCCESS);
        r.setSuccess(true);
        return r;
    }

    public static R error(){
        R r = new R();
        r.setMessage("失败");
        r.setCode(ResultCode.ERROR);
        r.setSuccess(false);
        return r;
    }

    /**
     * 链式编程
     * @param success
     * @return
     */
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R data(String name, Object obj){
        data.put(name, obj);
        this.setData(data);
        return this;
    }

}

package org.movie.model;
/**
 * 自定义返回码和消息的枚举类
 * @author Administrator
 *
 */
public enum ResultEnumCode {
	SUCCESS("200","success !!!"),
	/*
	 * 错误码格式说明（示例：200201）：
 		2	002	01
 		服务级错误（1为系统级错误）	服务模块代码(即数据ID)	具体错误代码
 		https://www.juhe.cn/docs/api/id/39/aid/117
	 */
	//密码错误
	PASSWORD_ERROR("200101","password error !!!"),
	//参数为空
	PARAM_IS_NULL("200102","param is null !!!"),
	//用户信息为空
	USERINFO_IS_NULL("200102","param is null !!!");
	private String code;
    
    private String msg;
    
    /**
     * 构造函数私有化 不允许代码中创建枚举类型
     * @param code
     * @param msg
     */
    private ResultEnumCode(String code,String msg){
		this.code=code;
		this.msg=msg;
	}

	public String getCode() {
		return code;
	}


	public String getMsg() {
		return msg;
	}

	
}

/**
 * 
 */
package org.movie.model;

/**
 * 返回信息实体类
 * @author Administrator
 *
 */
public class ResultInfo {
	 	private String resultCode;
	    
	    private String resultMsg;

	    /**
	     * <默认构造函数>
	     */
	    public ResultInfo(ResultEnumCode enumCode)
	    {
	        super();
	        this.resultCode = enumCode.getCode();
	        this.resultMsg = enumCode.getMsg();
	    }
	    
	    public ResultInfo(String code,String msg )
	    {
	        this.resultCode = code;
	        this.resultMsg = msg;
	    }
	    /**
	     */
	    public ResultInfo()
	    {
	    	this.resultCode=ResultEnumCode.SUCCESS.getCode();
	    	this.resultMsg=ResultEnumCode.SUCCESS.getMsg();
	    }
	    
	    
	    public void setResultInfo(ResultEnumCode enumCode){
	    	this.resultCode = enumCode.getCode();
	        this.resultMsg = enumCode.getMsg();
	    }

	    public String getResultCode()
	    {
	        return resultCode;
	    }

	    public void setResultCode(String resultCode)
	    {
	        this.resultCode = resultCode;
	    }

	    public String getResultMsg()
	    {
	        return resultMsg;
	    }

	    public void setResultMsg(String resultMsg)
	    {
	        this.resultMsg = resultMsg;
	    }
	    
	    /**
	     * @return
	     */
	    @Override
	    public String toString()
	    {
	        StringBuilder builder = new StringBuilder();
	        builder.append("ResultInfo [resultCode=");
	        builder.append(resultCode);
	        builder.append(", resultMsg=");
	        builder.append(resultMsg);
	        builder.append("]");
	        return builder.toString();
	    }
}

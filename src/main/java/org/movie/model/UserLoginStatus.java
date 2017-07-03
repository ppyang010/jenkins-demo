package org.movie.model;

import java.io.Serializable;

import org.movie.tools.Constant;
import org.springframework.util.StringUtils;

public class UserLoginStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6211815263104689844L;
	
	//手机网页登陆状态 0：未登陆   1:已登陆
	private int wapLoginStatus=0;
	//pc网页登陆状态 0：未登陆 1:已登陆
	private int wwwLoginStatus=0;
	//客户端（安卓，ios） 0：未登陆 1:已登陆
	private int clientLoginStatus=0;
	//用户id
	private Integer uid;
	private String wapSessionID;
	private String wwwSessionID;
	private String clientSessionID;
	
	public UserLoginStatus() {
		super();
		this.wapLoginStatus=0;
		this.wwwLoginStatus=0;
		this.clientLoginStatus=0;
	}
	//根据门户类型获取登陆状态
	public int getLoginStatus(int clientType) {
		int status=0;
		switch (clientType) {
		case Constant.PORTALTYPE_WAP:
			status=wapLoginStatus;
			break;
		case Constant.PORTALTYPE_WWW:
			status=wwwLoginStatus;
			break;
		case Constant.PORTALTYPE_CLIENT:
			status=clientLoginStatus;
			break;
		default:
			status=0;
			break;
		}
		return status;
	}
	//根据门户类型设置登陆状态
	public void setLoginStatus(int clientType,Integer status) {
		if(StringUtils.isEmpty(status)){
			return ;
		}
		switch (clientType) {
		case Constant.PORTALTYPE_WAP:
			this.wapLoginStatus=status;
			break;
		case Constant.PORTALTYPE_WWW:
			this.wwwLoginStatus=status;
			break;
		case Constant.PORTALTYPE_CLIENT:
			this.clientLoginStatus=status;
			break;
		default:
			this.wwwLoginStatus=status;
			break;
		}
	}
	//根据门户类型获取登陆的sessionID
	  public String getLoginSession(int clientType) {
			String sessionID=null;
			switch (clientType) {
			case Constant.PORTALTYPE_WAP:
				sessionID=wapSessionID;
				break;
			case Constant.PORTALTYPE_WWW:
				sessionID=wwwSessionID;
				break;
			case Constant.PORTALTYPE_CLIENT:
				sessionID=clientSessionID;
				break;
			default:
				sessionID=null;
				break;
			}
			return sessionID;
		}
		//根据门户类型设置登陆状态
		public void setLoginSession(int clientType,String sessionID) {
			if(StringUtils.isEmpty(sessionID)){
				return ;
			}
			switch (clientType) {
			case Constant.PORTALTYPE_WAP:
				this.wapSessionID=sessionID;
				break;
			case Constant.PORTALTYPE_WWW:
				this.wwwSessionID=sessionID;
				break;
			case Constant.PORTALTYPE_CLIENT:
				this.clientSessionID=sessionID;
				break;
			default:
				this.wwwSessionID=sessionID;
				break;
			}
		}
	public int getWapLoginStatus() {
		return wapLoginStatus;
	}
	public void setWapLoginStatus(int wapLoginStatus) {
		this.wapLoginStatus = wapLoginStatus;
	}
	public int getWwwLoginStatus() {
		return wwwLoginStatus;
	}
	public void setWwwLoginStatus(int wwwLoginStatus) {
		this.wwwLoginStatus = wwwLoginStatus;
	}
	public int getClientLoginStatus() {
		return clientLoginStatus;
	}
	public void setClientLoginStatus(int clientLoginStatus) {
		this.clientLoginStatus = clientLoginStatus;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getWapSessionID() {
		return wapSessionID;
	}
	public void setWapSessionID(String wapSessionID) {
		this.wapSessionID = wapSessionID;
	}
	public String getWwwSessionID() {
		return wwwSessionID;
	}
	public void setWwwSessionID(String wwwSessionID) {
		this.wwwSessionID = wwwSessionID;
	}
	public String getClientSessionID() {
		return clientSessionID;
	}
	public void setClientSessionID(String clientSessionID) {
		this.clientSessionID = clientSessionID;
	}
	
	
}

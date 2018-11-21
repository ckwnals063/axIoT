package axIoT.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDto implements Serializable,Cloneable {

	/** 세션 종료 시간. */
	private int expire;

	/** 사용자 정보 */
	private String userId;
	
	private String userName;
	
	private String authorSeCodeValue;
	
	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getAuthorSeCodeValue() {
		return authorSeCodeValue;
	}

	public void setAuthorSeCodeValue(String authorSeCodeValue) {
		this.authorSeCodeValue = authorSeCodeValue;
	}
}
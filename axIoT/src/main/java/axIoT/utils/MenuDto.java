package axIoT.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MenuDto implements Serializable,Cloneable {

	/** 세션 종료 시간. */
	private int expire;

	/** 메뉴 정보 */
	private String userId;
	
	private String authCl;
	
	private String authClNm;
	
	private String menuCd;
	
	private String upperMenuCd;
	
	private String url;
	
	private String menuNm;

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

	public String getAuthCl() {
		return authCl;
	}

	public void setAuthCl(String authCl) {
		this.authCl = authCl;
	}

	public String getAuthClNm() {
		return authClNm;
	}

	public void setAuthClNm(String authClNm) {
		this.authClNm = authClNm;
	}

	public String getMenuCd() {
		return menuCd;
	}

	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	public String getUpperMenuCd() {
		return upperMenuCd;
	}

	public void setUpperMenuCd(String upperMenuCd) {
		this.upperMenuCd = upperMenuCd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
}
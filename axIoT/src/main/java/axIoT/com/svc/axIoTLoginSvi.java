package axIoT.com.svc;

import java.util.Map;

import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 로그인을 처리하는 컨트롤러 클래스
 * @since 2016.08.29
 * @version 1.0
 * @see
 */

public interface axIoTLoginSvi {
	
	/**
	 * @Method 설명 : 로그인 처리한다.
	 * @작성자     : ywkim
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> processLogin(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Method 설명 : 사용자 권한에 맞는 탑메뉴를 가져온다.
	 * @작성자     : ywkim
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<?> authTopMenuList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Method 설명 : 사용자 권한에 맞는 서브메뉴를 가져온다.
	 * @작성자     : ywkim
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	List<?> submenuList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @Method 설명 : 마지막로그인일시를 업데이트한다.
	 * @작성자     : ywkim
	 * @param iParam
	 * @return
	 * @throws Exception
	 */
	public void updateLastloginDttm(Map<String, Object> iParam) throws Exception;
	
}

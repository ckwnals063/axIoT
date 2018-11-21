package axIoT.syscom.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import axIoT.syscom.CommonDao;
import axIoT.utils.SessionUtil;
import axIoT.utils.UserDto;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Package Name   : axIoT.syscom
 * @FileName  : AuthenticInterceptor.java
 * @작성자       : ywkim
 * @프로그램 설명 : 
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter {
	
	/** commonDao 적용 */
    @Resource(name = "commonDao")
    private CommonDao commonDao;    	

	/**
	 * 세션에 로그인정보가 있는지 여부로 인증 여부를 체크한다.
	 * 로그인정보가 없다면, 로그인 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//TO-TODO 로그인세션이없을 경우를 구현해주어야 합니다.
		return true;
	}
}

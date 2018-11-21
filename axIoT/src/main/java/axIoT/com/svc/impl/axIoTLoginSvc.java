package axIoT.com.svc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import axIoT.com.svc.axIoTLoginSvi;
import axIoT.syscom.CommonDao;
import axIoT.utils.axIoTUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 로그인을 처리하는 컨트롤러 클래스
 * @author ywkim
 * @version 1.0
 * @see
 */

@Service(value = "axIoTLoginSvi")
public class axIoTLoginSvc extends EgovAbstractServiceImpl implements axIoTLoginSvi {
	
	
    /** commonDao 적용 */
    @Resource(name = "commonDao")
    private CommonDao commonDao;    

    /** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** messageSource */
    @Resource(name="messageSource")
    MessageSource messageSource;
    
    //Logger
    protected Logger log = LogManager.getLogger(this.getClass());
    
    
    @Override
	public Map<String, Object> processLogin(Map<String, Object> paramMap) throws Exception {
    	
    	String rtnMessage="";
    	
    	String pw = paramMap.get("pw").toString();
    	
    	log.debug(axIoTUtil.mapToLog(paramMap));
    	// 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	Map<String, Object> userInfo = (Map<String, Object>) commonDao.select("axIoTLogin.selectUserInfo", paramMap);
    	
    	log.debug("-----------------------------------------------------------");
        log.debug("로그인  체크 시작");
        log.debug("-----------------------------------------------------------");
        log.debug(axIoTUtil.mapToLog(paramMap));
    	if (userInfo ==null) {
        	log.debug("-----------------------------------------------------------");
        	log.debug("Step 1. 로그인ID 오류");
            log.debug("-----------------------------------------------------------");

    		rtnMessage="로그인 ID나 패스워드가 올바르지 않습니다.";
    		
    	}else if (userInfo.get("password").equals(pw)) {
    		log.debug("-----------------------------------------------------------");
        	log.debug("Step 2. 비밀번호 정상 처리 ");
            log.debug("-----------------------------------------------------------");
    	}
    	                         
    	
    	if(rtnMessage==""){
    		userInfo.put("message","");
    		return userInfo;
    	}else{
    		Map<String, Object> userInfoErr = new HashMap<String, Object>();
    		userInfoErr.put("message",rtnMessage);
    		return userInfoErr;
    	}
    }
    
    @Override
    public List<?> authTopMenuList(Map<String, Object> paramMap) throws Exception {
    	
    	log.debug(axIoTUtil.mapToLog(paramMap));
    	
    	List<?> TopmenuList = commonDao.list("axIoTLogin.selectTopMenuList", paramMap);
    	
    	return TopmenuList;
    }
    
    @Override
    public List<?> submenuList(Map<String, Object> paramMap) throws Exception {
    	
    	log.debug(axIoTUtil.mapToLog(paramMap));
    	
    	List<?> submenuList = commonDao.list("axIoTLogin.selectsubmenuList", paramMap);
    	
    	return submenuList;
    }
    
	@Override
	public void updateLastloginDttm(Map<String, Object> iParam) throws Exception {
		commonDao.update("axIoTLogin.updateLastloginDttm",iParam);
	}
    
	
}



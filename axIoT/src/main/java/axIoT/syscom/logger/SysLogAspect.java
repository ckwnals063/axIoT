package axIoT.syscom.logger;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import axIoT.syscom.CommonDao;
import axIoT.utils.axIoTUtil;
import axIoT.utils.SessionUtil;

public class SysLogAspect {

	 /** log */
    protected Logger log = LogManager.getLogger(this.getClass());
    
    /** commonDao 적용 */
    @Resource(name = "commonDao")
    private CommonDao commonDao; 
	
	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 insert로 시작되는 Method
	 * @param request 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logInsert(ProceedingJoinPoint joinPoint) throws Throwable {
 
		StopWatch stopWatch = new StopWatch();
		
		try {
			stopWatch.start();
 
			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();
			
			Map<String, Object> sParam = new HashMap<String, Object>();	
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			
			String srvcNm = joinPoint.getTarget().getClass().getName();
			String methodNm = joinPoint.getSignature().getName();
			String procsSeCode = "C";
			String procsTime = Long.toString(stopWatch.getTotalTimeMillis());
			String userId = SessionUtil.getSession(request).getUserId();
			
			sParam.put("srvcNm", srvcNm);
			sParam.put("methodNm", methodNm);
			sParam.put("procsSeCode", procsSeCode);
			sParam.put("procsTime", procsTime);
			sParam.put("userId", userId);
			
			commonDao.insert("BatchLog.insertSysLog", sParam);
 
		}
 
	}
 
	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 update로 시작되는 Method
	 * @param request 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
 
		StopWatch stopWatch = new StopWatch();
		
		
		try {
			stopWatch.start();
 
			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();
 
			Map<String, Object> sParam = new HashMap<String, Object>();	
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			
			String srvcNm = joinPoint.getTarget().getClass().getName();
			String methodNm = joinPoint.getSignature().getName();
			String procsSeCode = "U";
			String procsTime = Long.toString(stopWatch.getTotalTimeMillis());
			String userId = SessionUtil.getSession(request).getUserId();
			
			sParam.put("srvcNm", srvcNm);
			sParam.put("methodNm", methodNm);
			sParam.put("procsSeCode", procsSeCode);
			sParam.put("procsTime", procsTime);
			sParam.put("userId", userId);

			commonDao.insert("BatchLog.insertSysLog", sParam);
		}
 
	}
 
	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 delete로 시작되는 Method
	 * @param request 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logDelete(ProceedingJoinPoint joinPoint) throws Throwable {
 
		StopWatch stopWatch = new StopWatch();
 
		try {
			stopWatch.start();
 
			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();
 
			Map<String, Object> sParam = new HashMap<String, Object>();	
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			
			String srvcNm = joinPoint.getTarget().getClass().getName();
			String methodNm = joinPoint.getSignature().getName();
			String procsSeCode = "D";
			String procsTime = Long.toString(stopWatch.getTotalTimeMillis());
			String userId = SessionUtil.getSession(request).getUserId();
			
			sParam.put("srvcNm", srvcNm);
			sParam.put("methodNm", methodNm);
			sParam.put("procsSeCode", procsSeCode);
			sParam.put("procsTime", procsTime);
			sParam.put("userId", userId);
			
			commonDao.insert("BatchLog.insertSysLog", sParam);
		}
 
	}
 
	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 select로 시작되는 Method
	 * @param request 
	 * @param request 
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logSelect(ProceedingJoinPoint joinPoint) throws Throwable {
 
		StopWatch stopWatch = new StopWatch();
		
		try {
			stopWatch.start();
 
			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();
			 
			Map<String, Object> sParam = new HashMap<String, Object>();	
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			
			String srvcNm = joinPoint.getTarget().getClass().getName();
			String methodNm = joinPoint.getSignature().getName();
			String procsSeCode = "R";
			String procsTime = Long.toString(stopWatch.getTotalTimeMillis());
			String userId = SessionUtil.getSession(request).getUserId();
 
			sParam.put("srvcNm", srvcNm);
			sParam.put("methodNm", methodNm);
			sParam.put("procsSeCode", procsSeCode);
			sParam.put("procsTime", procsTime);
			sParam.put("userId", userId);
			log.debug(axIoTUtil.mapToLog(sParam));
			
		}
 
	}
	
	
	
	    
			
	
}

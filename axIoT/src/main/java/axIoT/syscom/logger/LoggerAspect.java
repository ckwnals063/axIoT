package axIoT.syscom.logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import axIoT.utils.axIoTUtil;

@Aspect
public class LoggerAspect {

    /** log */
    protected Logger log = LogManager.getLogger(this.getClass());

    static String type = "";

    /**
     * @Method 설명 : 컨트롤러 호출전 화면 파라미터를 로깅 처리한다.
     * @작성자 : ywkim
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    @Before("execution(* axIoT..ctr.*Ctr.*(..))")
    public void logCtrPrintStart(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        log.debug("Request URI : " + request.getRequestURI());
        log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.debug(String.format("%s.%s() Start!!", joinPoint.getSignature().getDeclaringTypeName(), joinPoint
                        .getSignature().getName()));
        log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //log.debug("PARAMS:" + Arrays.toString(joinPoint.getArgs()));
        if (joinPoint.getArgs() != null) {
            log.debug("Controller argument:" + axIoTUtil.mapToLog(joinPoint.getArgs()[0]));
        }
    }
    
    /**
     * @Method 설명 : 컨트롤러 호출후 로깅 처리한다.
     * @작성자 : ywkim
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    @After("execution(* axIoT..ctr.*Ctr.*(..))")
    public void logCtrPrintEnd(JoinPoint joinPoint) throws Throwable {
        log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        log.debug(String.format("%s.%s() End!!", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
        log.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }  
    
    /**
     * @Method 설명 : 서비스 호출전 화면 파라미터를 로깅 처리한다.
     * @작성자 : ywkim
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    @Before("execution(* axIoT..impl.*Svc.*(..))")
    public void logSvcPrintStart(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        log.debug("Request URI : " + request.getRequestURI());
        log.debug("**********************************************************************");
        log.debug(String.format("%s.%s() Start!!", joinPoint.getSignature().getDeclaringTypeName(), joinPoint
                        .getSignature().getName()));
        log.debug("**********************************************************************");
    }
    
    /**
     * @Method 설명 : 서비스 호출후 로깅 처리한다.
     * @작성자 : ywkim
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    @After("execution(* axIoT..impl.*Svc.*(..))")
    public void logSvcPrintEnd(JoinPoint joinPoint) throws Throwable {
        log.debug("**********************************************************************");
        log.debug(String.format("%s.%s() End!!", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()));
        log.debug("**********************************************************************");
    }    

}

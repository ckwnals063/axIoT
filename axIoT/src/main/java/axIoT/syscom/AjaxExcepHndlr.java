package axIoT.syscom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import axIoT.utils.SessionUtil;



/**
 * @Package Name   : axIoT.syscom
 * @FileName  : AjaxExcepHndlr.java
 * @작성자       : ywkim
 * @프로그램 설명 : 
 */
public class AjaxExcepHndlr extends SimpleMappingExceptionResolver {
	
	
	 /** log */
    protected Logger log = LogManager.getLogger(this.getClass());

	private String ajaxErrorView = "";
    private String ajaxDefaultErrorMessage = "An error has occurred";
    private boolean ajaxShowTechMessage = true;
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        if( isAjax(request) ) {
        	
        	log.debug("isAjax ----------------------------------------->" + isAjax(request)   );
        	
        	
            String exceptionMessage = ajaxDefaultErrorMessage;
            if( ajaxShowTechMessage )
                exceptionMessage += "\n" + getExceptionMessage(e);
            ModelAndView m = new ModelAndView(ajaxErrorView);
            m.addObject("exceptionMessage", exceptionMessage);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return m;
        } else {
            return super.resolveException(request, response, o, e);
        }
    }

    private String getExceptionMessage(Throwable e) {
        String message = "";
        while( e != null ) {
            message += e.getMessage() + "\n";
            e = e.getCause();
        }
        return message;
    }
    
    private boolean isAjax(HttpServletRequest request) {
    	
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public void setAjaxDefaultErrorMessage(String ajaxDefaultErrorMessage) {
        this.ajaxDefaultErrorMessage = ajaxDefaultErrorMessage;
    }

    public void setAjaxErrorView(String ajaxErrorView) {
        this.ajaxErrorView = ajaxErrorView;
    }

    public void setAjaxShowTechMessage(boolean ajaxShowTechMessage) {
        this.ajaxShowTechMessage = ajaxShowTechMessage;
    }

    public String getAjaxDefaultErrorMessage() {
        return ajaxDefaultErrorMessage;
    }

    public String getAjaxErrorView() {
        return ajaxErrorView;
    }

    public boolean isAjaxShowTechMessage() {
        return ajaxShowTechMessage;
    }
    
       
}
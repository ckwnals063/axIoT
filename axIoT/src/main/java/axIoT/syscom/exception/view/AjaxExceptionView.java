package axIoT.syscom.exception.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;



public class AjaxExceptionView extends AbstractView {
	
	   /** log */
    protected Logger log = LogManager.getLogger(this.getClass());
	
    /** messageSource */
    @Resource(name="messageSource")
    MessageSource messageSource;
    
    private String exceptionAttribute = "exception";

    /**
     * @Method 설명 :
     * @작성자     : ywkim
     * @param exceptionAttribute
     */
    public void setExceptionAttribute(String exceptionAttribute)
    {
        this.exceptionAttribute = exceptionAttribute;
    }


    /* 
     * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Exception ex = (Exception) model.get(exceptionAttribute);
        if (ex != null) {

            String message = ex.getMessage();
            if (message == null)
                message = messageSource.getMessage("fail.common.msg", null, Locale.KOREA);

            Map exceptionModel = new HashMap();
            
            exceptionModel.put("message", message);
            exceptionModel.put("error", "true");
            exceptionModel.put("errortype", ex.getClass().getSimpleName());

            if (ex instanceof EgovBizException){
                writeExceptionContent(response, HttpServletResponse.SC_EXPECTATION_FAILED, exceptionModel);
            }else if (ex instanceof ValidationException){
                writeExceptionContent(response, HttpServletResponse.SC_OK, exceptionModel);
            }else if (ex instanceof DataAccessException){
                exceptionModel.put("message", messageSource.getMessage("fail.common.msg", null, Locale.KOREA));
                writeExceptionContent(response, HttpServletResponse.SC_EXPECTATION_FAILED, exceptionModel);
            }else if (ex instanceof TransactionException) {
                writeExceptionContent(response, HttpServletResponse.SC_EXPECTATION_FAILED, exceptionModel);
            } else {
                exceptionModel.put("message", message);
                writeExceptionContent(response, HttpServletResponse.SC_EXPECTATION_FAILED, exceptionModel);
            }
        }
    }

    /**
     * @Method 설명 :
     * @작성자     : ywkim
     * @param response
     * @param httpStatus
     * @param model
     * @throws Exception
     */
    protected void writeExceptionContent(HttpServletResponse response, int httpStatus, Map model)
        throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(model);
        response.setContentType("text/html ; charset=UTF-8");
        response.setStatus(httpStatus);
        response.getWriter().write(jsonStr);
    }

}

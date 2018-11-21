package axIoT.syscom.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
	
public abstract class AbstractHeaderMappingExceptionResolver implements HandlerExceptionResolver, Ordered
{

	protected final Logger logger = LogManager.getLogger();
	private int order = 0x7fffffff;
	private String exceptionAttribute = "exception";
	private String exceptionCodeAttribute = "exceptionCode";
	private Logger warnLogger;
	private Logger errorLogger;
		    

    public void setOrder(int order)
    {
        this.order = order;
    }

    public int getOrder()
    {
        return order;
    }

    public String getExceptionCodeAttribute()
    {
        return exceptionCodeAttribute;
    }

    public void setWarnLogCategory(String loggerName)
    {
        warnLogger = LogManager.getLogger(loggerName);
    }

    public void setErrorLogCategory(String loggerName)
    {
        errorLogger = LogManager.getLogger(loggerName);
    }

    public void setExceptionAttribute(String exceptionAttribute)
    {
        this.exceptionAttribute = exceptionAttribute;
    }

    public void setExceptionCodeAttribute(String exceptionCodeAttribute)
    {
        this.exceptionCodeAttribute = exceptionCodeAttribute;
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        if (supportsHeader(request)) {
            if (logger.isDebugEnabled())
                logger.debug((new StringBuilder()).append("Resolving exception from handler [").append(handler)
                        .append("]: ").append(ex).toString());
            logException(ex, request);
            return doResolveException(request, response, handler, ex);
        } else {
            return null;
        }
    }

    protected void logException(Exception ex, HttpServletRequest request)
    {
        if(warnLogger != null && warnLogger.isWarnEnabled())
            warnLogger.warn(buildLogMessage(ex, request), ex);
        if(errorLogger != null && errorLogger.isErrorEnabled())
            errorLogger.error(buildLogMessage(ex, request), ex);
    }

    protected String buildLogMessage(Exception ex, HttpServletRequest request)
    {
        return "Handler execution resulted in exception";
    }

    protected ModelAndView getModelAndView(String viewName, Exception ex)
    {
        ModelAndView mv = new ModelAndView(viewName);
        if(exceptionAttribute != null)
        {
            if(logger.isDebugEnabled())
                logger.debug((new StringBuilder()).append("Exposing Exception as model attribute '").append(exceptionAttribute).append("'").toString());
            mv.addObject(exceptionAttribute, ex);
        }
        return mv;
    }

    protected abstract boolean supportsHeader(HttpServletRequest httpservletrequest);

    protected abstract ModelAndView doResolveException(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Object obj, Exception exception);

   
}


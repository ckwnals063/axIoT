package axIoT.syscom.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.ModelAndView;

public class SimpleHeaderMappingExceptionResolver extends AbstractHeaderMappingExceptionResolver {
	
	private String headerName;
	private String headerValues[];
	private String errorView;
	
    public void setHeaderName(String headerName)
    {
        this.headerName = headerName;
    }

    public void setHeaderValues(String headerValues[])
    {
        this.headerValues = headerValues;
    }

    public void setErrorView(String errorView)
    {
        this.errorView = errorView;
    }

    /**
     * @Method 설명 : HttpServletRequest request 헤더정보
     * @param request
     * @return
     */
    protected boolean supportsHeader(HttpServletRequest request)
    {
        if(headerName != null && headerValues != null)
        {
            String orginalHeaderValue = request.getHeader(headerName);
            if(orginalHeaderValue != null)
            {
                String arr$[] = headerValues;
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    String headerValue = arr$[i$];
                    if(PatternMatchUtils.simpleMatch(headerValue, orginalHeaderValue))
                        return true;
                }

            }
        }
        return false;
    }

    
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        String viewName = errorView;
        if(viewName != null)
            return getModelAndView(viewName, ex);
        else
            return null;
    }	
	
}

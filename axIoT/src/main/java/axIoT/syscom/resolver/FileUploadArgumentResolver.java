package axIoT.syscom.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;


public class FileUploadArgumentResolver implements WebArgumentResolver{
	
	/**
	 * 
	 * @param methodParameter 메소드 파라미터의 타입,인덱스등의 정보 
	 * @param webRequest web request 객체
	 * @return argument에 commandMap(java.util.Map)이 있으면 commandMap, 없으면<code>UNRESOLVED</code>.
	 * @exception Exception
	 */
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {

		Object[] annotations = methodParameter.getParameterAnnotations();
		String dsName = null;

		for ( Object annotation : annotations){
			if ( annotation instanceof FileUploadRequestParam ){
				dsName = ( (FileUploadRequestParam)annotation ).value();
			}
		}
		if (dsName != null){
			HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
			Object lfileInfo = request.getAttribute(dsName);
		
			return lfileInfo;
		} else
			return UNRESOLVED;
	}

	public static void main(String[] args) {
	}
}

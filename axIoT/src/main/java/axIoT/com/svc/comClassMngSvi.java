package axIoT.com.svc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface comClassMngSvi {
	
	List<?> selectClassMngDetail(Map<?, ?> searchParam);

}

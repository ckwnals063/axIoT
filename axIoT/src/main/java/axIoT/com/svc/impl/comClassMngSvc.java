package axIoT.com.svc.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import axIoT.com.svc.comClassMngSvi;
import axIoT.syscom.CommonDao;
import axIoT.utils.axIoTUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;


@Service(value = "comClassMngSvi")
public class comClassMngSvc extends EgovAbstractServiceImpl implements comClassMngSvi{
	protected Logger log = LogManager.getLogger(this.getClass());
		
	/** commonDao 적용 */
	@Resource(name = "commonDao")
	private CommonDao commonDao;    	

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
		
	private EgovIdGnrService egovIdGnrService;

	/** 
	 * @see axIoT.com.svc.comClassMngSvi#selectcomCodeList(java.util.Map)
	 */
	@Override
	public List<?> selectClassMngDetail(Map<?, ?> searchParam) {
		
		
		return commonDao.list("comClassMng.selectClassMngDetail", searchParam);
	}
	

}

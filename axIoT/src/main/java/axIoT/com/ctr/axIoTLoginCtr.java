package axIoT.com.ctr;



import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import axIoT.com.svc.axIoTLoginSvi;
import axIoT.utils.axIoTUtil;
import axIoT.utils.MenuDto;
import axIoT.utils.SessionUtil;
import axIoT.utils.UserDto;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class axIoTLoginCtr {

	/** kwc30LoginSvi */
    @Resource(name = "axIoTLoginSvi")
    private axIoTLoginSvi axIoTLoginSvi;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSource")
    MessageSource messageSource;
    
    
    /** log */
    protected Logger log = LogManager.getLogger(this.getClass());
    
    
    
    @RequestMapping(value = "/com/openaxIoTLogin.do")
    public String openLoginView(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, ModelMap model) throws Exception {
    	
    	log.debug(axIoTUtil.mapToLog(paramMap));
    	model.addAttribute("message", paramMap.get("message"));
    	
    	return "com/COM00000S";
    }
    
    
   
    @RequestMapping(value = "/com/processLogin.do")
    public String processLogin(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, ModelMap model) throws Exception {
    	
    	log.debug(axIoTUtil.mapToLog(paramMap));
    	 
    	 String securedUsername = paramMap.get("securedUsername").toString();
         String securedPassword = paramMap.get("securedPassword").toString();
         
         HttpSession session = request.getSession();
         
         paramMap.put("userId", securedUsername);
         paramMap.put("pw", securedPassword);
    	
        //TODO 로그인 처리
    	Map<String, Object> userInfo = null;
    	
    	                           
    	return "redirect:/com/opencomClassMng.do";
    	
    }
    
        
    
    @RequestMapping(value = "/com/openaxIoTMngSystemMain.do")
    public String openMainView(Map<String, Object> paramMap, ModelMap model) throws Exception {
        return "com/COM00010S";
    }
    
    
   
    @RequestMapping(value = "/com/selectTopMenu.do")
    public ModelAndView  selectTopMenu(ModelMap model, HttpServletRequest request) throws Exception {
    	
    	ModelAndView mv  = new ModelAndView("jsonView");
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();	
    	String userId = SessionUtil.getSession(request).getUserId();
    	paramMap.put("userId",  userId);
    	
        return mv;
    }
    
    
    
    @RequestMapping(value = "/com/processLogout.do")
    public String processLogout(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, ModelMap model) throws Exception {
    	
    	// 로그인 정보를 세션에서 삭제
    	SessionUtil.Logout(request);
    	log.debug("getSession ----------------------------------------->" + SessionUtil.getSession(request));
    	
    	return "redirect:/com/openaxIoTLogin.do";

    }
    
    private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] encryptedBytes = hexToByteArray(securedValue);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
        return decryptedValue;
    }
		
   
    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }
     
  
}

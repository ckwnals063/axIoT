package axIoT.com.ctr;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.ParamAware;

import axIoT.com.svc.comClassMngSvi;
import axIoT.utils.SessionUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Package Name   : axIoT.com.ctr
 * @FileName  : comClassMngCtr.java
 * @작성자       : ywkim
 * @프로그램 설명 : comClassMng Ctr 
 */
@Controller
public class comClassMngCtr {

	/** programMngSvi */
	@Resource(name = "comClassMngSvi")
	private comClassMngSvi comClassMngSvi;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	 /** log */
    protected Logger log = LogManager.getLogger(this.getClass());
       
	@RequestMapping(value = "/com/opencomClassMng.do")
	public String opencomClassMng(@RequestParam Map<String,Object> paramMap, Model model) throws Exception {
		return "com/COM30090Q";
	}
		
	@RequestMapping(value = "/com/selectClassMngDetail.do")
	@ResponseBody
	public ModelAndView selectClassMngDetail(@RequestParam Map<String,Object> paramMap, HttpServletRequest request, Model model) throws Exception {
		
		ModelAndView mv = new ModelAndView("jsonView");
		List<?> comClassMngList = comClassMngSvi.selectClassMngDetail(paramMap);
		
		model.addAttribute("result", "ok");
		model.addAttribute("list", comClassMngList);
			
		return mv;
	}
	
}


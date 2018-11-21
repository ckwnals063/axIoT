package axIoT.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class axIoTUtil {
	 /**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";

    /**
     * @Description : Java 객체 값 확인 사용방법 : Java 쪽에서 log.debug(axIoTUtil.mapToLog(객체변수));
     * @author : ywkim
     * @param source 콘솔로그 찍고 싶은 객체
     * @return result 콘솔로그 찍을 String
     */
    @SuppressWarnings("unchecked")
    public static String mapToLog(Object obj) throws Exception {
        StringBuffer sb = new StringBuffer();
        JSONArray jsonArray = null;
        JSONObject jsonObj = null;
        Iterator<String> nameItr = null;
        String value = "";
        String name = "";
        StringBuffer lineStr = new StringBuffer();

        if (obj == null) {
            sb.append("=== 오브젝트는 null입니다. ==== > ").append("\n");
            return sb.toString();
        }

        sb.append("\n");
        sb.append("==================================================================================================\n");
        sb.append("                        CLASS TYPE : ")
                .append(obj.getClass()).append("\n");
        sb.append("==================================================================================================\n");
        sb.append("             INDEX NO          |              VO NAME            |           VO VALUE             \n");
        sb.append("==================================================================================================\n");
        if (obj.getClass().isAssignableFrom(String.class)
                || obj.getClass().isAssignableFrom(Integer.class)
                || obj.getClass().isAssignableFrom(BigDecimal.class)) {
            sb.append("=== Vo 또는 List 가 아닙니다. ==== > ").append(obj.toString()).append("\n");
        } else if (obj instanceof String[]) {
            String[] castObj = (String[]) obj;
            for (int i = 0; i < castObj.length; i++) {
                sb.append(castObj[i]).append("\n");
            }
        } else if (obj.getClass().isAssignableFrom(ArrayList.class) || obj.getClass().isAssignableFrom(HashMap.class)) {
            jsonArray = JSONArray.fromObject(obj);
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObj = (JSONObject) JSONSerializer.toJSON(jsonArray.get(i));
                nameItr = jsonObj.keys();
                while (nameItr.hasNext()) {
                    lineStr = new StringBuffer().append("                ");
                    name = nameItr.next();
                    value = jsonObj.getString(name);
                    if (value != null && value.length() != 0) {
                        lineStr.append(i);
                        while (lineStr.length() < 31) {
                            lineStr.append(" ");
                        }
                        lineStr.append("|     ").append(name);
                        while (lineStr.length() < 65) {
                            lineStr.append(" ");
                        }
                        lineStr.append("|     ").append("[").append(value)
                                .append("]");
                        sb.append(lineStr).append("\n");
                    }
                }
                sb.append("-----------------------------------------------------------------------------------------\n");
                if (i >= 9 && jsonArray.size() > 10) {
                    sb.append("MORE DATA... ARRAY TOTAL COUNT : "
                            + jsonArray.size() + "\n");
                    break;
                }
            }
        } else {
            jsonObj = (JSONObject) JSONSerializer.toJSON(obj);
            nameItr = jsonObj.keys();
            while (nameItr.hasNext()) {
                lineStr = new StringBuffer()
                        .append("                -              ");
                name = nameItr.next();
                value = jsonObj.getString(name);
                if (value != null && value.length() != 0) {
                    lineStr = lineStr.append("|     ").append(name);
                    while (lineStr.length() < 65) {
                        lineStr.append(" ");
                    }
                    lineStr.append("|     ").append("[").append(value)
                            .append("]");
                    sb.append(lineStr).append("\n");
                }
            }
        }
        sb.append("==================================================================================================\n");
        return sb.toString();
    }

  
    
    
	/**
	 * @Method 설명 : getObjToStr
	 * @작성자     : ywkim
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getObjToStr(Object obj) throws Exception{
		if(obj == null) return "";
		else return ((String) obj).trim();
	}
	
  
    
}

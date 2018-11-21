package axIoT.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    public static Map<String, Object>responseEntity2Map(ResponseEntity<Object>resEntity)
    {
       Map<String, Object> result = null;
       try
       {
          if (resEntity != null)
          {
             JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(resEntity);
             ObjectMapper mapper = new ObjectMapper();
             result = mapper.readValue(jsonObj.toString(), new TypeReference<HashMap<String, Object>>(){});
          }
       }
       catch(Exception ell)
       {
          
       }
       return result;
    }
}

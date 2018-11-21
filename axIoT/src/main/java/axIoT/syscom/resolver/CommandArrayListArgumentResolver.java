package axIoT.syscom.resolver;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class CommandArrayListArgumentResolver implements WebArgumentResolver {

	protected Logger log = LogManager.getLogger();
	
	@Override
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		
		Class<?> clazz = methodParameter.getParameterType();
		
		if(clazz.isArray()){
			Class<?> targetClazz = clazz.getComponentType();
			
			HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
			String[] properties = getPropertyName(targetClazz);
			
			int max = 0;
			for(String getter : properties){
				if(request.getParameterValues(getter) == null ){
					continue;
				}
				
				if(max < request.getParameterValues(getter).length){
					max = request.getParameterValues(getter).length;
				}
			}
			
			Object ret = Array.newInstance(targetClazz, max);
			Object target = null;
			
			for(int i = 0 ; i < max ; i++){
				target = targetClazz.newInstance();
				
				for(String property : properties){
					String[] values = request.getParameterValues(property);
					
					if(values != null && i < values.length){
						callSetter(target, property, values[i]);
					}
				}
				
				Array.set(ret, i, target);
			}
			
			return ret;
			
		}
		
		return UNRESOLVED;
		
	}
	
	protected String[] getPropertyName(Class<?> clazz){
		List<String> list = new ArrayList<String>();
		
		Method[] methods = clazz.getMethods();
		
		for(Method method : methods){
			if(method.getName().startsWith("set") && method.getParameterTypes().length == 1){
				list.add(method.getName().substring(3,4).toLowerCase() + method.getName().substring(4));
			}
		}
		return list.toArray(new String[0]); 
	}

	protected void callSetter(Object vo, String propertyName, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(vo.getClass(), propertyName);
		pd.getWriteMethod().invoke(vo, new Object[]{value});
	}
}

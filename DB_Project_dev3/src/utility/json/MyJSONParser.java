package utility.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MyJSONParser {
	public static Map<String, String> jSonEntityToMap(String jSONeXpression) {
		
		Object obj = JSONValue.parse(jSONeXpression);
		JSONObject  jsonMap =  (JSONObject) obj;
		return jsonMap;
		
	}
	
	public static Map<String, Object> jSonEntityToObjectMap(String jSONeXpression){
		Object obj = JSONValue.parse(jSONeXpression);
		JSONObject  jsonMap =  (JSONObject) obj;
		return jsonMap;
	}
	
	public static List<Object> jSonArrayToArray(String jSONeXpression){
		Object obj=JSONValue.parse(jSONeXpression);
		  JSONArray array=(JSONArray)obj;
		  return array;
	}
	
	public static String getValueFromEntity(String key, String jSONeXpression){
		return jSonEntityToMap(jSONeXpression).get(key);
	}
	
	public static Object getObjectValueFromEntity(String key, String jSONeXpression){
		return jSonEntityToObjectMap(jSONeXpression).get(key);
	}
	
	public static JSONArray getJSONArrayFromEntity(String key, String jSONeXpression){
		Object obj = JSONValue.parse(jSONeXpression);
//		JSONObject  jsonMap =  (JSONObject) obj;
		JSONArray array = (JSONArray) ((JSONObject) obj).get(key);
		return array;
	}
	
	public static String getEntityInArrayByIndex(Integer index, String jSONeXpression){
		return (String) jSonArrayToArray(jSONeXpression).get(index);
	}
	
	public static List<String> getAllEntityInArray(String jSONeXpression){
		List<Object> list = jSonArrayToArray(jSONeXpression);
		List<String> resultList = new ArrayList<String>();
		for (Object object : list) {
			resultList.add((String) object);
		}
		return resultList;
	}
	

	
	public static void main(String[] args) {
//		String exp1 = "{\"username\": \"userName\",\"password\": \"passWord\"}";
		
		String exp1 = "{   \"username\": \"admin\",   \"password\": \"admin\" }";
		
		System.out.println(exp1);
		
		Map<String, String> resultMap = jSonEntityToMap(exp1);
		
		String username = resultMap.get("username");
		
		System.out.println(username);
		
		String exp2 = "[1,2,3,4,{\"a\" : \"b\"}]";
		
		List<Object> resultArray = jSonArrayToArray(exp2);
		
		Object obj1 = resultArray.get(4);
		
		System.out.println(obj1);
		
		String exp3 = "{\"name\":[1,2,34]}";
		Map<String, Object> resultMap3 = jSonEntityToObjectMap(exp3);
		
		Object res = resultMap3.get("name");
		
		List<Object> resultArraytest = jSonArrayToArray(res.toString());
		Object obj2 = resultArraytest.get(2);
		
		System.out.println(obj2);
		
		Object objres = getObjectValueFromEntity("name", exp3);
		System.out.println(objres);
		List<Object> resultArraytest1 = jSonArrayToArray(objres.toString());
		Object obj3 = resultArraytest.get(2);
		System.out.println(obj3);
	}

}

package utility.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

public class MyJSONEncoder {
	public static String encodingMapToEntity(Map<String, Object> map){
		
		String jsonText = JSONValue.toJSONString(map);
		return jsonText;
	}
	
	public static String encodingArrayToArray(List<Object> list){
		String jsonText = JSONValue.toJSONString(list);
		return jsonText;
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("a1", "string");
		map.put("a2", 1);
		map.put("a3", false);
		map.put("a4", null);
		
		String resStr = encodingMapToEntity(map);
		
		
		List<Object> list = new ArrayList<Object>();
		list.add("ele1");
		list.add("ele2");
		list.add("ele3");
		list.add("ele4");
		
		Map<String, Object> map2 = new HashMap<String, Object>(map);
		
		map2.put("a5", list);
		
		resStr = encodingMapToEntity(map2);
		
		System.out.println(resStr);
	}
}

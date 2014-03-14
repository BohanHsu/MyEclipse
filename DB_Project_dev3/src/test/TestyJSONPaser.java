package test;

import java.util.List;
import java.util.Map;

import utility.json.MyJSONParser;

class TestyJSONPaser {
	public static void main(String[] args) {
//		String exp1 = "{\"username\": \"userName\",\"password\": \"passWord\"}";
		
		String exp1 = "{   \"username\": \"admin\",   \"password\": \"admin\" }";
		
		System.out.println(exp1);
		
		Map<String, String> resultMap = MyJSONParser.jSonEntityToMap(exp1);
		
		String username = resultMap.get("username");
		
		System.out.println(username);
		
		String exp2 = "[1,2,3,4,{\"a\" : \"b\"}]";
		
		List<Object> resultArray = MyJSONParser.jSonArrayToArray(exp2);
		
		Object obj1 = resultArray.get(4);
		
		System.out.println(obj1);
		
	}
}

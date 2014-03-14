package utility.json;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONValue;

public class JsonMessage {
	public static String SUCCEED_MSG = "succeed";

	private Map<String, Object> data = null;
	private Boolean succeed = null;
	private String message = null;

	public JsonMessage() {
		this.data = new LinkedHashMap<String, Object>();
	}

	public void addData(String key, Object value) {
		data.put(key, value);
	}
	
	public void appendMapToData(Map<String, Object> map){
		data.putAll(map);
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toJSONString() {
		Map<String, Object> msg = new LinkedHashMap<String, Object>();

		msg.put("data", this.data);
		msg.put("succeed", this.succeed);
		msg.put("msg", this.message);

		String messageStr = MyJSONEncoder.encodingMapToEntity(msg);

		return messageStr;
	}

	public static void main(String[] args) {
		JsonMessage jm = new JsonMessage();
		jm.addData("username", "xbh");
		jm.addData("email", "ilovebarce@gmail.com");
		jm.setSucceed(true);
		jm.setMessage(JsonMessage.SUCCEED_MSG);


		String jStr = jm.toJSONString();

		System.out.println(jStr);
	}
}

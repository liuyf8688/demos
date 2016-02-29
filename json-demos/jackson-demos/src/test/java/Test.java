import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyf.demos.json.jackson.model.User;

public class Test {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String content = "{\"givenName\":\"\",\"middelName\":\"\",\"surname\":\"\",\"age\":\"\"}";
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.getDeserializationConfig());
		mapper.readValue(content, User.class);
	}
}

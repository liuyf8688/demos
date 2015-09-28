import javax.sql.DataSource;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		
		AbstractApplicationContext context  = null;
		
		try {
			context = new ClassPathXmlApplicationContext("config.xml");
			System.out.println(context.getBean(DataSource.class));
		} finally {
			if (context != null) {
				context.close();
			}
		}
	}
}

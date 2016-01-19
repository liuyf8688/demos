package com.demos.liuyf.apache.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SimpleDemo {

	public static void main(String[] args) throws IOException {
		
		String baseDirPath = SimpleDemo.class.getResource("").getPath();
		baseDirPath = baseDirPath.substring(0, baseDirPath.indexOf("apache-freemarker-demos"));
		baseDirPath += "apache-freemarker-demos" + File.separator + "contents";
		
		
		File folder = new File(baseDirPath);
		Configuration config = new Configuration();
		// Settings of Layer 1
		config.setLocale(new Locale("zh", "CN"));
		config.setTemplateLoader(new FileTemplateLoader(folder));
		
		String path = "simple_demo.ftl";
		try {
			
			Map<String, Object> rootMap = new HashMap<>();
			rootMap.put("locale", new Locale("cs", "CZ")); // override in settings of Layer 2
			rootMap.put("number_format", "0.###");
			rootMap.put("No", 123456.0009098);
			rootMap.put("redirectPage", "redirectPage");
			rootMap.put("activationToken", "activationToken");
			rootMap.put("MAILING_ADDRESS_SUPPORT", "http://www.baidu.com");
			
			StringWriter writer = new StringWriter();
			Template template = config.getTemplate(path);
			template.process(rootMap, writer);
			
			System.out.println(writer.toString());
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

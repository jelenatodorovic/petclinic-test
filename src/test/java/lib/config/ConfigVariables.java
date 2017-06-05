package lib.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ConfigVariables {
	
	 @Value("${url}")
	 private String url;
	 
	 @Value("${browser}")
	 private String browser;
	 
	 public String getBrowser() {
		 return browser;
	 }
	 
	 public String getUrl() {
		 return url;
	 }
	
}

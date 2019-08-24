package info.jsjackson.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/*
 * https://springframework.guru/spring-boot-web-application-part-2-using-thymeleaf/
 */


//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
public class AssetCatalogApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AssetCatalogApplication.class, args);
		
		//ApplicationContext context = SpringApplication.run(DemoProjectApplication.class, args);;
		//System.out.println("Contains SecurityConfiguration:  " + context.containsBeanDefinition("demoBeanA"));
		
		//System.out.println("Bean Definition Count: " + context.getBeanDefinitionCount());
		String[]  beans =   context.getBeanDefinitionNames();
		/*for (int i = 0; i <= beans.length -1; i++) {
			System.out.println(i + ": Bean Name: " + beans[i]);
		}*/
		
		
	}
}

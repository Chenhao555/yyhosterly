package haohost.controller.A;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import haohost.controller.InitialiseFile;

/**
 * 
 * @author 陈豪
 * 	继承了WebMvcConfigurer类的特殊类
 * 	可以进行配置
 *
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private ServletContext servletContext;    //servlt的上下文
	
	// 路径后缀，绑定Conten-type的类型。
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
	}
	
	// 校验支持
	@Override
	public Validator getValidator() {
		return new org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean();
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//转发
		registry.addViewController("/").setViewName("/hello");
		// 重定向跳转
		registry.addRedirectViewController("abc", "https://www.baidu.com");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("=============================注册资源器成功=================================");
		String path = servletContext.getRealPath("/");
		InitialiseFile.Intiialise(path.endsWith("/") ||path.endsWith("\\")? path : path + "/");
		System.out.println("项目运行真实路径：" + path);
		registry.addResourceHandler("/static/**").addResourceLocations("/public", "/static/").setCachePeriod(31556926);
	}
}


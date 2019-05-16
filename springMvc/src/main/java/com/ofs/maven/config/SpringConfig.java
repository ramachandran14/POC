package com.ofs.maven.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ofs.maven.Impl.MailServiceImpl;
import com.ofs.maven.Impl.ToDoServiceImpl;

@Configuration
@ComponentScan(basePackages="com.ofs.maven.*")
@EnableWebMvc
public class SpringConfig extends WebMvcConfigurerAdapter {
	
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    @Bean
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://rpc71:3306/ramachandran");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;
	}
    
    @Bean
	public MailServiceImpl bean() {
		return new MailServiceImpl();
	}
    
    @Bean
    public MultipartResolver multipartResolver() {
    	CommonsMultipartResolver cmr = new CommonsMultipartResolver();
    	cmr.setMaxUploadSize(27266);
    	cmr.setMaxInMemorySize(3633);
        return new StandardServletMultipartResolver();
    }
    
    @Bean
    public ToDoServiceImpl getToDoServiceImpl() {
        return new ToDoServiceImpl(getDataSource());
    }
    
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
}
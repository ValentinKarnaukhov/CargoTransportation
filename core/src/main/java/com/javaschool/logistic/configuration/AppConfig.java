package com.javaschool.logistic.configuration;


import com.javaschool.logistic.converters.CityConverter;

import com.javaschool.logistic.converters.TruckConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@Import(JpaConfiguration.class)
@EnableWebMvc
@ComponentScan(basePackages = "com.javaschool.logistic")
public class AppConfig extends WebMvcConfigurerAdapter{


	@Autowired
	CityConverter cityConverter;

	@Autowired
	TruckConverter truckConverter;





	@Bean
	public InternalResourceViewResolver resolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/css/**").addResourceLocations("/static/css/");
		registry.addResourceHandler("/static/img/**").addResourceLocations("/static/img/");
		registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/");
		registry.addResourceHandler("/static/font/**").addResourceLocations("/static/font/");
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("validation");
		return messageSource;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(truckConverter);
		registry.addConverter(cityConverter);
	}

	@Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
    
}


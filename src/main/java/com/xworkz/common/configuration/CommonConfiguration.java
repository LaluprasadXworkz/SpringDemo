package com.xworkz.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.xworkz")
public class CommonConfiguration {

    @Bean
   public ViewResolver viewResolver(){
       return new InternalResourceViewResolver("/",".jsp");
   }
}

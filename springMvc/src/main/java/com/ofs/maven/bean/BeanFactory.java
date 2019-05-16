package com.ofs.maven.bean;

import org.springframework.context.annotation.Bean;

import com.ofs.maven.model.ToDo;

public class BeanFactory {

	@Bean
    public static ToDo  getTodoBean() {
        return new ToDo();
    }
}

package org.kpn.ch10;

import org.kpn.ch10.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class SpringValidatorDemo {

    private static final Logger logger = LoggerFactory.getLogger(SpringValidatorDemo.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Validator singerValidator = ctx.getBean("singerValidator", Validator.class);

        Singer singer = new Singer();
        singer.setFirstName(null);
        singer.setLastName("Mayer");

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(singer, "John");
        ValidationUtils.invokeValidator(singerValidator, singer, result);

        List<ObjectError> allErrors = result.getAllErrors();
        logger.error("No of validation errors: {}", allErrors.size());
        allErrors.forEach(e -> logger.error("{}", e.getCode()));

        ctx.close();
    }
}

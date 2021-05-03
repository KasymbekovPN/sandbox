package org.kpn.ch10;

import org.kpn.ch10.config.AppConfig;
import org.kpn.ch10.obj.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349Demo {

    private static final Logger logger = LoggerFactory.getLogger(Jsr349Demo.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingerValidationService singerValidationService = ctx.getBean(SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("J");
        singer.setLastName("Mayer");
        singer.setGenre(null);
        singer.setGender(null);

        validateSinger(singer, singerValidationService);

        ctx.close();
    }

    private static void validateSinger(Singer singer,
                                       SingerValidationService singerValidationService){
        Set<ConstraintViolation<Singer>> violations = singerValidationService.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations){
        logger.info("No. of violations : {}", violations.size());
        for (ConstraintViolation<Singer> violation : violations) {
            logger.info(
                    "Validation error for property: {} with value {} with error message {}",
                    violation.getPropertyPath(),
                    violation.getInvalidValue(),
                    violation.getMessage()
            );
        }
    }
}

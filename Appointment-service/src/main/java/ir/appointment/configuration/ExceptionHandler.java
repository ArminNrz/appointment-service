package ir.appointment.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@ControllerAdvice
public class ExceptionHandler implements ProblemHandling {
}

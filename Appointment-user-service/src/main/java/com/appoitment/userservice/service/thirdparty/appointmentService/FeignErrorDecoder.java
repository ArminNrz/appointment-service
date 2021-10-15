package com.appoitment.userservice.service.thirdparty.appointmentService;

import com.appoitment.userservice.exception.dto.AppointmentExceptionDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@Component
@Slf4j
@RequiredArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400 :
                AppointmentExceptionDTO exceptionDTO = objectMapper.readValue(response.body().toString(), AppointmentExceptionDTO.class);
                return Problem.valueOf(Status.BAD_REQUEST, exceptionDTO.getDetail());
            case 404 :
                return Problem.valueOf(Status.NOT_FOUND, response.reason());
            case 503 :
                return Problem.valueOf(Status.SERVICE_UNAVAILABLE, "Appointment ws not available");
            default:
                return Problem.valueOf(Status.BAD_REQUEST, response.reason());
        }
    }
}

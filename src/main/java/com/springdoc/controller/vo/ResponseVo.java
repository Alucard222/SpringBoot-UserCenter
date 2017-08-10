package com.springdoc.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by alucard on 8/4/17.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> {
    private long timestamp;
    private ProcessStatus processStatus;
    private String message;
    private T data;
}

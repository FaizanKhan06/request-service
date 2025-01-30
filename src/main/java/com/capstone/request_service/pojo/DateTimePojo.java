package com.capstone.request_service.pojo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DateTimePojo {

    private int id;
    private LocalDateTime dateTime;

}

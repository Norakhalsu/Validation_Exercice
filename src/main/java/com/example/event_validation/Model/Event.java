package com.example.event_validation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class Event {

    //@Size(min = 3, message = " ID length must be more than 2")
    @NotNull(message = "ID cannot be null")
    @Length(min = 3 , message = " ID Length more than 2")
    @Pattern(regexp = "\\d+", message = " ID must be a number")
    private String id;

    @Size(min = 16 ,message = "Description Length must be more than 15 ")
    @NotEmpty(message = "Description Cannot be null")
    private String description;

    //@Min(value =  , message = "capacity Length must be more than 25 ")
    //@Pattern(regexp = "^[2-9][6-9]|[3-9]\\d+|\\d{3,}$", message = "capacity must be more than 25")
    @NotNull(message = "capacity Cannot be null ")
    @Pattern(regexp = "\\d+", message = " capacity must be a number")
    @Min(value = 26, message = "Capacity must be more than 25")
    private String capacity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;
}

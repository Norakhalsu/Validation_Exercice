package com.example.company_validation.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Positive
    @NotNull(message = "id Cannot be null") // int , String
    @Pattern(regexp = "\\d+", message = " ID must be a number")
    @Size(min =3 , message = " ID length it must more than 2 ")
    private String id;


    @NotEmpty(message = "title Cannot be null") // String
    @Size(min=9 , message = " title length more than 8")
    private String title;


    @NotEmpty(message = "description Cannot be null")
    @Size(min = 16 , message = "description Length more than 15")
    private String description;

    //must be Not Started or in Progress or Completed only
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be Not Started, In Progress, or Completed only")
    @NotEmpty(message = "status Cannot be null") // String
    private String status;


    @NotEmpty(message = "Cannot be null")
    @Size(min = 7, message = "Company name length must be more then 6")
    private String companyName;

}

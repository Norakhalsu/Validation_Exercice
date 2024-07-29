package com.example.company_validation.Controller;


import com.example.company_validation.Model.Company;
import jakarta.validation.Valid;
import jdk.jshell.Snippet;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    ArrayList<Company> companyList = new ArrayList<Company>();

    @PostMapping("/add")// creat new company (With Validation)
    public ResponseEntity addCompany(@Valid @RequestBody Company company , Errors errors) {
        if (errors.hasErrors()) {
            String response=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(response);
        }
        companyList.add(company);
        return ResponseEntity.status(201).body(" Company Added Successfully");
    }

    @GetMapping("/get")// display all companies(With Validation)
    public ResponseEntity getCompany() {
          return ResponseEntity.ok(companyList);
    }


    @PutMapping("/update/{index}")//update Company (With Validation)
    public ResponseEntity updateCompany(@Valid @RequestBody Company company, @PathVariable int index , Errors errors) {
        if(errors.hasErrors()) {
            String response=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(response);
        }
        companyList.set(index, company);
        return status(200).body(" Company Updated Successfully");
    }


@DeleteMapping("/delete/{index}") // delete with Entity Response
    public ResponseEntity deleteCompany( @PathVariable int index) {
        companyList.remove(index);
        return ResponseEntity.ok(" Project Deleted Successfully");
}


@PutMapping("/update-status/{index}") // change Status (With Validation)
    public ResponseEntity updateCompanyStatus(@PathVariable int index  ) {
//        if(errors.hasErrors()) {
//            String response=errors.getFieldError().getDefaultMessage();
//            return status(400).body(response);
//        }
        for(Company company : companyList) {
            if(companyList.get(index).getStatus().equalsIgnoreCase("Not Started")){
                company.setStatus("In Progress");
                return ResponseEntity.status(200).body(" Company Updated Successfully");

            }
            else if (companyList.get(index).getStatus().equalsIgnoreCase("In Progress")) {
                company.setStatus("Completed");
                return ResponseEntity.status(200).body(" Company Updated Successfully");
            }
        }
        return null;
}

@GetMapping("/search-title/{title}")// search by title
    public ResponseEntity getCompanyByTitle(@PathVariable String title) {
         for (Company company : companyList) {
             if(company.getTitle().equals(title)){
                 return ResponseEntity.ok(company);
             }
         } return ResponseEntity.ok("Company not found");
}


@GetMapping("/all-projects/{companyname}")//display all company same name
    public ResponseEntity getAllCompany(@PathVariable String companyname) {
        ArrayList<Company> allCompany = new ArrayList<>();
        for (Company company : companyList) {
            if(company.getCompanyName().equals(companyname)){
                allCompany.add(company);
                return ResponseEntity.ok(allCompany);
            }
        } return ResponseEntity.ok("Company not found");
       }
}

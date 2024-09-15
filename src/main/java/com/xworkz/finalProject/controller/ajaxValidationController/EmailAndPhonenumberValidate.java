package com.xworkz.finalProject.controller.ajaxValidationController;


import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("")
public class EmailAndPhonenumberValidate {

    @Autowired
    private SignUpService signUpService;



    @GetMapping("/validateEmail")
    public String validateEmail(@RequestParam String email) {
        Optional<SignupDTO> optional = signUpService.findByemail(email);
        return optional.isPresent() ? "Email is already Exist" : "";
    }

    @GetMapping("/validatePhone")
    public String validatePhone(@RequestParam long phoneNumber) {
        Optional<SignupDTO> existingPhone = signUpService.findByPhoneNumber(phoneNumber);
        return existingPhone.isPresent() ? "PhoneNumber is already Exist": ""; // Return true if phone number already exists
    }
}

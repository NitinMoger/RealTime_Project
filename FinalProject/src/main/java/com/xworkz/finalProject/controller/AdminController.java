package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/adminLogin")
    public String adminLoginCheck(@Valid AdminDTO adminDTO, Model model){
        Optional<AdminDTO> adminResult= this.adminService.findByEmailAndPassword(adminDTO.getEmail(), adminDTO.getPassword());
        System.out.println("Admin DTO from controller..."+adminResult);
        if (adminResult.isPresent()){
            model.addAttribute("success","Hello, Welcome to Admin Home");
            return "AdminHomePage";
        }else {
            model.addAttribute("msg","Enter valid email and password");
            model.addAttribute("dto",adminDTO);
            return "AdminSignIn";
        }
    }

    @PostMapping("/viewDetails")
    public String fetchAllClientDetails(Model model){
        List<SignupDTO> list= this.adminService.fetchAllClientRecords();
        if (!list.isEmpty()){
            model.addAttribute("dto",list);
        }else {
            model.addAttribute("msg","No Records found");
        }
        return "AdminHomePage";
    }
}
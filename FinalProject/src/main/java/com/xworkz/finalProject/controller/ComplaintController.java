package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/")
@Resource(name="complaintService")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private JavaMailSender javaMailSender;
    public ComplaintController() {
        System.out.println("Create the no argument constructor for ComplaintController");
    }


    @PostMapping("/raiseComplaint")
    public String saveComplaintDetails(@Valid ComplaintDTO complaintDTO,
                                       HttpSession session, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError));
            model.addAttribute("errorMessage", bindingResult.getAllErrors());
            model.addAttribute("complaintDto", complaintDTO);
        } else {
            SignupDTO signupDTO1 = (SignupDTO) session.getAttribute("dto");
            System.err.println(signupDTO1);
            complaintDTO.setCreatedBy(signupDTO1.getFirstName() + " " + signupDTO1.getLastName());
            complaintDTO.setCreatedDate(LocalDateTime.now());
            complaintDTO.setUserId(signupDTO1.getId());
            complaintDTO.setStatus("Active");
            boolean saved = this.complaintService.saveTheComplaints(complaintDTO);
            if (saved) {

                model.addAttribute("successMessage", "Your complaint is successfully submitted,Please waite to resolve it...");
            } else {
                model.addAttribute("failedMessage", "Enter valid details..");
            }
        }
        return "RaiseComplaint";
    }


    //    public String viewComplaintsGet(Model model, HttpSession session, @RequestParam(value = "status", required = false) String status) {
//        return viewComplaints(model, session, status);
//    }
    @PostMapping("/viewComplaints")
    private String viewComplaints(Model model, HttpSession session, String status) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            model.addAttribute("complaintMsg", "User not logged in.");
            return "ViewComplaints";
        }
        if (status == null || status.equals("0")) {
            status = "active";
        }
        List<ComplaintDTO> complaints = this.complaintService.findByUserIdAndStatus(userId, status);
        if (complaints.isEmpty()) {
            model.addAttribute("complaintMsg", "No complaints found.");
        } else {
            model.addAttribute("complaints", complaints);
        }
        model.addAttribute("selectedStatus", status);
        return "ViewComplaints";
    }
}
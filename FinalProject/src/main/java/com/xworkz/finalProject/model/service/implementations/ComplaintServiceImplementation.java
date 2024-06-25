package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ComplaintServiceImplementation implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(ComplaintDTO complaintDTO, SignupDTO signupDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(signupDTO.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear " + signupDTO.getFirstName() + " " + signupDTO.getLastName() +
                " your signup success Please SignIn through this password :" + signupDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " + "X-workz Team");
        javaMailSender.send(simpleMailMessage);
    }

    public ComplaintServiceImplementation(){
        System.out.println("create the no argument constructor for ComplaintServiceImplementation");
    }

    @Override
    public boolean saveTheComplaints(ComplaintDTO complaintDTO) {
        boolean saveRecord= this.complaintRepository.saveTheComplaints(complaintDTO);
        if (saveRecord){
            sendEmail(complaintDTO);
            return saveRecord;
        }else {
            return false;
        }

    }

    @Override
    public List<ComplaintDTO> findByUserIdAndStatus(int userId, String status) {
        return Collections.emptyList();
    }


}

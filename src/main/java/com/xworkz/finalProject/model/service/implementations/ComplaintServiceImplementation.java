package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImplementation implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public ComplaintServiceImplementation(){
        System.out.println("create the no argument constructor for ComplaintServiceImplementation");
    }

    @Override
    public boolean saveTheComplaints(ComplaintDTO complaintDTO) {
        boolean saveRecord= this.complaintRepository.saveTheComplaints(complaintDTO);
        if (saveRecord){
            return saveRecord;
        }else {
            return false;
        }

    }
}

package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.ComplaintDTO;

import java.util.List;

public interface ComplaintService {
    boolean saveTheComplaints(ComplaintDTO complaintDTO);
    List<ComplaintDTO> findByUserIdAndStatus(int userId, String status);

}

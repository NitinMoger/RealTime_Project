package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.ComplaintDTO;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ComplaintRepository{

   boolean saveTheComplaints(ComplaintDTO complaintDTO);
    List<ComplaintDTO> findByUserIdAndStatus(int userId, String status);
}

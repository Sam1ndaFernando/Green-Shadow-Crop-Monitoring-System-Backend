package com.example.demo.service;


import com.example.demo.dto.StaffStatus;
import com.example.demo.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    StaffStatus getStaff(String id);

    void deleteStaff(String id);

    void UpdateStaff(String id, StaffDTO staffDTO);
}

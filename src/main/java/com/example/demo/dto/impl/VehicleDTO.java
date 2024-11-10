package com.example.demo.dto.impl;

import com.example.demo.dto.VehicleStatus;
import com.example.demo.entity.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleStatus {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private FuelType fuelType;
    private String status;
    private String allocatedStaffMemberDetails;
    private String remarks;
}

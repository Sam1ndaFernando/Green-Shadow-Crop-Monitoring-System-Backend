package com.example.demo.entity.impl;

import com.example.demo.entity.FuelType;
import jakarta.persistence.*;

@Entity
@Table(name = "vahicle")
public class VehicleEntity {
    @Id
    private String vehicleCode;

    private String licensePlateNumber;
    private String vehicleCategory;
    private String status;
    private FuelType fuelType;


    @OneToOne
    @JoinColumn(name = "allocatedStaffMemberDetail")
    private StaffEntity allocatedStaffMemberDetails;

    private String remarks;
}

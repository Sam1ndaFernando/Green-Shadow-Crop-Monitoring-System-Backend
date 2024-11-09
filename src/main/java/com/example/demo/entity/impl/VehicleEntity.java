package com.example.demo.entity.impl;

import jakarta.persistence.*;

@Entity
@Table(name = "vahicle")
public class VehicleEntity {
    @Id
    private String vehicleCode;

    private String licensePlateNumber;
    private String vehicleCategory;
    private String status;

    @OneToOne
    @JoinColumn(name = "allocatedStaffMemberDetail")
    private StaffEntity allocatedStaffMemberDetails;

    private String remarks;
}

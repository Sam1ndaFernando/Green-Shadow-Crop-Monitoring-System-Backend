package com.example.demo.entity.impl;


import com.example.demo.entity.EquipmentStatus;
import com.example.demo.entity.EquipmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    private String equipmentId;

    private String equipmentName;
    private EquipmentType equipmentType;
    private EquipmentStatus equipmentStatus;

    @ManyToOne
    @JoinColumn(name = "equipment_assigned_staff_details")
    private StaffEntity assignedStaffDetails;

    @ManyToOne
    @JoinColumn(name = "equipment_assigned_field_details")
    private FieldEntity assignedFieldDetails;
}

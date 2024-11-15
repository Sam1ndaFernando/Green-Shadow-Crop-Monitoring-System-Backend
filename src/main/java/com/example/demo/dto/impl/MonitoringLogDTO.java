package com.example.demo.dto.impl;

import com.example.demo.dto.MonitoringLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements MonitoringLogStatus {
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;
    private List<FieldDTO> field;
    private List<CropDTO> crop;
    private List<StaffDTO> staff;
}

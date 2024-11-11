package com.example.demo.service;


import com.example.demo.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleService getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
}

package com.example.demo.service;


import com.example.demo.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    CropDTO saveCrop(CropDTO cropDTO);

    List<CropDTO> getAllCrops();

    CropDTO getCrop(String cropCode);

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode, CropDTO cropDTO);
}

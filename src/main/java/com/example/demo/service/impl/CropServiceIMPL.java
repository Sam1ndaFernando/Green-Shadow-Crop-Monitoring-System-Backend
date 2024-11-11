package com.example.demo.service.impl;

import com.example.demo.dao.CropDao;
import com.example.demo.dao.FieldDao;
import com.example.demo.dto.impl.CropDTO;
import com.example.demo.entity.impl.CropEntity;
import com.example.demo.entity.impl.FieldEntity;
import com.example.demo.service.CropService;
import com.example.demo.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CropServiceIMPL implements CropService {

    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    FieldDao fieldDao;

    @Override
    public CropDTO saveCrop(CropDTO cropDTO) {
        FieldEntity fieldEntity = fieldDao.findByFieldCode(cropDTO.getField_code());

        // Create CropEntity from CropDTO
        CropEntity cropEntity = new CropEntity();
        cropEntity.setCropCode(cropDTO.getCropCode());
        cropEntity.setCropCommonName(cropDTO.getCropCommonName());
        cropEntity.setCropScientificName(cropDTO.getCropScientificName());
        cropEntity.setCropImage(cropDTO.getCropImage()); // Base64 encoded image
        cropEntity.setCategory(cropDTO.getCategory());
        cropEntity.setCropSeason(cropDTO.getCropSeason());
        cropEntity.setField(fieldEntity);

        cropDao.save(cropEntity);
        return cropDTO;
    }

    @Override
    public List<CropDTO> getAllCrops() {
        // Retrieve all crops and map to CropDTO
        return cropDao.findAll().stream()
                .map(mapping::toCropDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CropDTO getCrop(String cropCode) {
        // Find the crop by cropCode and convert to CropDTO
        CropEntity cropEntity = cropDao.findById(cropCode).orElse(null);
        return cropEntity != null ? mapping.toCropDTO(cropEntity) : null;
    }

    @Override
    public void deleteCrop(String cropCode) {
        // Delete crop by cropCode if exists
        cropDao.deleteById(cropCode);
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        CropEntity cropEntity = cropDao.getReferenceById(cropCode);
        cropEntity.setCropCode(cropDTO.getCropCode());
        cropEntity.setCropCommonName(cropDTO.getCropCommonName());
        cropEntity.setCropScientificName(cropDTO.getCropScientificName());
        cropEntity.setCropImage(cropDTO.getCropImage()); // Base64 encoded image
        cropEntity.setCategory(cropDTO.getCategory());
        cropEntity.setCropSeason(cropDTO.getCropSeason());
        cropDao.save(cropEntity);
    }
}

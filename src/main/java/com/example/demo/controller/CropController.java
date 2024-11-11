package com.example.demo.controller;

import com.example.demo.dto.impl.CropDTO;
import com.example.demo.exception.DataPersistException;
import com.example.demo.service.CropService;
import com.example.demo.util.IdGenerate;
import com.example.demo.util.PicEncorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
public class CropController {

    private static final Logger logger = LoggerFactory.getLogger(CropController.class);

    @Autowired
    private CropService cropService;

    // Save new crop
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestPart("cropName") String cropName,
                                         @RequestPart("cropScientificName") String scientificName,
                                         @RequestPart("cropImage") MultipartFile cropImage,
                                         @RequestPart("category") String category,
                                         @RequestPart("cropSeason") String season,
                                         @RequestPart("field") String field) {

        try {
            // Generate unique Crop ID and encode image
            String cropId = IdGenerate.generateCropId();
            String cropImageBase64 = PicEncorder.generateProfilePicToBase64(cropImage);

            // Create a CropDTO object and populate it
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropId);
            cropDTO.setCropCommonName(cropName);
            cropDTO.setCropScientificName(scientificName);
            cropDTO.setCropImage(cropImageBase64);
            cropDTO.setCategory(category);
            cropDTO.setCropSeason(season);
            cropDTO.setField_code(field);

            // Save the crop to the service layer
            cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            logger.error("Data persistence error while saving crop: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal error while saving crop: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete crop by its code
    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        try {
            // Delete the crop and handle potential errors
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            logger.error("Error deleting crop with code {}: {}", cropCode, e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal error while deleting crop with code {}: {}", cropCode, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all crops
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CropDTO>> getAllCrops() {
        try {
            List<CropDTO> crops = cropService.getAllCrops();
            return new ResponseEntity<>(crops, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching all crops: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

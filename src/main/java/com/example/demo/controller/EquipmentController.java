package com.example.demo.controller;

import com.example.demo.dto.impl.EquipmentDTO;
import com.example.demo.entity.EquipmentStatus;
import com.example.demo.entity.EquipmentType;
import com.example.demo.exception.DataPersistException;
import com.example.demo.service.EquipmentService;
import com.example.demo.util.IdGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestPart("equipmentName") String equipmentName,
                                              @RequestPart("equipmentType") String equipmentType,
                                              @RequestPart("equipmentStatus") String equipmentStatus,
                                              @RequestPart("staff") String staff,
                                              @RequestPart("field") String field) {
        try {
            String equipmentID = IdGenerate.generateEquipmentID();
            EquipmentDTO equipmentDTO = new EquipmentDTO();
            equipmentDTO.setEquipmentId(equipmentID);
            equipmentDTO.setEquipmentName(equipmentName);
            equipmentDTO.setEquipmentType(EquipmentType.valueOf(equipmentType));
            equipmentDTO.setEquipmentStatus(EquipmentStatus.valueOf(equipmentStatus));
            equipmentDTO.setAssignedStaffDetails(staff);
            equipmentDTO.setAssignedFieldDetails(field);

            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
        List<EquipmentDTO> equipmentList = equipmentService.getAllEquipments();
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    @GetMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable("equipmentId") String equipmentId) {
        EquipmentDTO equipmentDTO = equipmentService.getEquipment(equipmentId);
        if (equipmentDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(equipmentDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipmentById(@PathVariable("equipmentId") String equipmentId) {
        EquipmentDTO equipmentDTO = equipmentService.getEquipment(equipmentId);
        if (equipmentDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        equipmentService.deleteEquipment(equipmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{equipmentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentId") String equipmentId,
                                                @RequestBody EquipmentDTO equipmentDTO) {
        if (equipmentService.getEquipment(equipmentId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            equipmentService.updateEquipment(equipmentId, equipmentDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.example.demo.service.impl;

import com.example.demo.dao.EquipmentDao;
import com.example.demo.dao.FieldDao;
import com.example.demo.dao.StaffDao;
import com.example.demo.dto.impl.EquipmentDTO;
import com.example.demo.entity.impl.EquipmentEntity;
import com.example.demo.exception.DataPersistException;
import com.example.demo.service.EquipmentService;
import com.example.demo.util.IdGenerate;
import com.example.demo.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipmenrServiceIMPL implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private FieldDao fieldDao;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(IdGenerate.generateEquipmentID());
        EquipmentEntity equipmentEntity = mapping.toEquipmentEntity(equipmentDTO);
        equipmentEntity.setAssignedStaffDetails(staffDao.getReferenceById(equipmentDTO.getAssignedStaffDetails()));
        equipmentEntity.setAssignedFieldDetails(fieldDao.getReferenceById(equipmentDTO.getAssignedFieldDetails()));
        EquipmentEntity savedEquipment = equipmentDao.save(equipmentEntity);
        if (savedEquipment == null) {
            throw new DataPersistException("Equipment Not Found");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return mapping.asEquipmentDtoList(equipmentDao.findAll());
    }

    @Override
    public EquipmentDTO getEquipment(String equipmentId) {
        EquipmentEntity equipmentEntity = equipmentDao.getReferenceById(equipmentId);
        return mapping.toEquipmentDTO(equipmentEntity);
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        equipmentDao.deleteById(equipmentId);
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        EquipmentEntity equipmentEntity = equipmentDao.getReferenceById(equipmentId);

        // Update fields from EquipmentDTO
        equipmentEntity.setEquipmentName(equipmentDTO.getEquipmentName());
        equipmentEntity.setEquipmentType(equipmentDTO.getEquipmentType());
        equipmentEntity.setEquipmentStatus(equipmentDTO.getEquipmentStatus());
        equipmentEntity.setAssignedStaffDetails(staffDao.getReferenceById(equipmentDTO.getAssignedStaffDetails()));
        equipmentEntity.setAssignedFieldDetails(fieldDao.getReferenceById(equipmentDTO.getAssignedFieldDetails()));

        // Save updated equipment entity
        equipmentDao.save(equipmentEntity);
    }
}

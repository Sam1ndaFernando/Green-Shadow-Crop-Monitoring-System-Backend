package com.example.demo.dao;


import com.example.demo.entity.impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringLogDao extends JpaRepository<MonitoringLogEntity,String> {
}

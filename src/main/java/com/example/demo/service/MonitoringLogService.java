package com.example.demo.service;


import com.example.demo.dto.MonitoringLogStatus;
import com.example.demo.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveLog(MonitoringLogDTO monitoringLogDTO);

    List<MonitoringLogDTO> getAllLogs();

    MonitoringLogStatus getLog(String logCode);

    void deleteLog(String logCode);

    void updateLog(String logCode, MonitoringLogDTO monitoringLogDTO);
}

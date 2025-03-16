package com.company.helpdesk.service;

import com.company.helpdesk.entity.EquipmentType;
import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.entity.RepairRequestHistory;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RepairRequestService {

    @Autowired
    private DataManager dataManager;



    @Transactional
    public void completeRepairRequest(RepairRequest repairRequest) {
        // Создаем запись в истории
        RepairRequestHistory history = dataManager.create(RepairRequestHistory.class);
        history.setDescription(repairRequest.getDescription());
        history.setCompletionDate(new Date());
        history.setUser(repairRequest.getUser());
        history.setLocation(repairRequest.getLocation());
        history.setRoom(repairRequest.getRoom());
        history.setEquipmentType(repairRequest.getEquipmentType());
        history.setEquipment(repairRequest.getEquipment());
        history.setFaultType(repairRequest.getFaultType());

        // Сохраняем запись в истории
        dataManager.save(history);

        // Удаляем заявку из основной таблицы
        dataManager.remove(repairRequest);
    }
}
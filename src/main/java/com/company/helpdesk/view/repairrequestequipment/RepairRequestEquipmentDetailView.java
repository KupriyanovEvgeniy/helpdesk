package com.company.helpdesk.view.repairrequestequipment;

import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.entity.RepairRequestEquipment;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "repairRequestEquipments/:id", layout = MainView.class)
@ViewController(id = "RepairRequestEquipment.detail")
@ViewDescriptor(path = "repair-request-equipment-detail-view.xml")
@EditedEntityContainer("repairRequestEquipmentDc")
public class RepairRequestEquipmentDetailView extends StandardDetailView<RepairRequestEquipment> {

    @Autowired
    private DataManager dataManager;

    // Поле для хранения идентификатора родительской заявки,
    // которое должно быть установлено из родительского экрана перед открытием этого экрана
    private String repairRequestId;

    // Публичный сеттер для передачи идентификатора родительской заявки
    public void setRepairRequestId(String repairRequestId) {
        this.repairRequestId = repairRequestId;
    }

    @Subscribe
    public void onAfterCommitChanges(AfterSaveEvent event) {
        RepairRequestEquipment equipment = getEditedEntity();
        // Если родительская заявка не установлена, и у нас есть идентификатор родительской заявки,
        // загружаем её и устанавливаем в оборудование.
        if (equipment.getRepairRequest() == null && repairRequestId != null) {
            RepairRequest parentRequest = dataManager.load(RepairRequest.class)
                    .id(repairRequestId)
                    .one();
            equipment.setRepairRequest(parentRequest);
        }
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<RepairRequestEquipment> event) {
        RepairRequestEquipment equipment = event.getEntity();
        // Аналогично, при инициализации сущности, если родительская заявка не задана,
        // загружаем её по переданному идентификатору.
        if (equipment.getRepairRequest() == null && repairRequestId != null) {
            RepairRequest parentRequest = dataManager.load(RepairRequest.class)
                    .id(repairRequestId)
                    .one();
            equipment.setRepairRequest(parentRequest);
        }
    }
}
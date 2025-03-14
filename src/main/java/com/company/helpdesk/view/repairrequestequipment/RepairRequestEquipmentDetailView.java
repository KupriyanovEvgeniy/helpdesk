package com.company.helpdesk.view.repairrequestequipment;

import com.company.helpdesk.entity.RepairRequestEquipment;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "repairRequestEquipments/:id", layout = MainView.class)
@ViewController(id = "RepairRequestEquipment.detail")
@ViewDescriptor(path = "repair-request-equipment-detail-view.xml")
@EditedEntityContainer("repairRequestEquipmentDc")
public class RepairRequestEquipmentDetailView extends StandardDetailView<RepairRequestEquipment> {
}
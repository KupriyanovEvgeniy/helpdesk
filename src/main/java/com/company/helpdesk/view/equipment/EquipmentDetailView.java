package com.company.helpdesk.view.equipment;

import com.company.helpdesk.entity.Equipment;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "equipments/:id", layout = MainView.class)
@ViewController(id = "Equipment.detail")
@ViewDescriptor(path = "equipment-detail-view.xml")
@EditedEntityContainer("equipmentDc")
public class EquipmentDetailView extends StandardDetailView<Equipment> {
}
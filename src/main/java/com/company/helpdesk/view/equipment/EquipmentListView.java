package com.company.helpdesk.view.equipment;

import com.company.helpdesk.entity.Equipment;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "equipments", layout = MainView.class)
@ViewController(id = "Equipment.list")
@ViewDescriptor(path = "equipment-list-view.xml")
@LookupComponent("equipmentsDataGrid")
@DialogMode(width = "64em")
public class EquipmentListView extends StandardListView<Equipment> {
}
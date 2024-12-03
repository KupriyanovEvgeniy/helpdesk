package com.company.helpdesk.view.faulttype;

import com.company.helpdesk.entity.FaultType;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "faultTypes", layout = MainView.class)
@ViewController(id = "FaultType.list")
@ViewDescriptor(path = "fault-type-list-view.xml")
@LookupComponent("faultTypesDataGrid")
@DialogMode(width = "64em")
public class FaultTypeListView extends StandardListView<FaultType> {
}
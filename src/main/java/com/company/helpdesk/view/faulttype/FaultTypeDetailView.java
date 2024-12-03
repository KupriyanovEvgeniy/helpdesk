package com.company.helpdesk.view.faulttype;

import com.company.helpdesk.entity.FaultType;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "faultTypes/:id", layout = MainView.class)
@ViewController(id = "FaultType.detail")
@ViewDescriptor(path = "fault-type-detail-view.xml")
@EditedEntityContainer("faultTypeDc")
public class FaultTypeDetailView extends StandardDetailView<FaultType> {
}
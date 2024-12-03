package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "repairRequests/:id", layout = MainView.class)
@ViewController(id = "RepairRequest.detail")
@ViewDescriptor(path = "repair-request-detail-view.xml")
@EditedEntityContainer("repairRequestDc")
public class RepairRequestDetailView extends StandardDetailView<RepairRequest> {
}
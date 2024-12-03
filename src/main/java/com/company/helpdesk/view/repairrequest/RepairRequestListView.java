package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "repairRequests", layout = MainView.class)
@ViewController(id = "RepairRequest.list")
@ViewDescriptor(path = "repair-request-list-view.xml")
@LookupComponent("repairRequestsDataGrid")
@DialogMode(width = "64em")
public class RepairRequestListView extends StandardListView<RepairRequest> {
}
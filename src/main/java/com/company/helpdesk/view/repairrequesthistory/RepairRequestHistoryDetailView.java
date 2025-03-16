package com.company.helpdesk.view.repairrequesthistory;

import com.company.helpdesk.entity.RepairRequestHistory;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "repairRequestHistories/:id", layout = MainView.class)
@ViewController(id = "RepairRequestHistory.detail")
@ViewDescriptor(path = "repair-request-history-detail-view.xml")
@EditedEntityContainer("repairRequestHistoryDc")
public class RepairRequestHistoryDetailView extends StandardDetailView<RepairRequestHistory> {
}
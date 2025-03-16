package com.company.helpdesk.view.repairrequesthistory;

import com.company.helpdesk.entity.RepairRequestHistory;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "repairRequestHistories", layout = MainView.class)
@ViewController(id = "RepairRequestHistory.list")
@ViewDescriptor(path = "repair-request-history-list-view.xml")
@LookupComponent("repairRequestHistoriesDataGrid")
@DialogMode(width = "64em")
public class RepairRequestHistoryListView extends StandardListView<RepairRequestHistory> {
}
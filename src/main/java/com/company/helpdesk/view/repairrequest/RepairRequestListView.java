package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.entity.TaskStatus;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.view.*;

@Route(value = "repairRequests", layout = MainView.class)
@ViewController(id = "RepairRequest.list")
@ViewDescriptor(path = "repair-request-list-view.xml")
@LookupComponent("repairRequestsDataGrid")
@DialogMode(width = "64em")
public class RepairRequestListView extends StandardListView<RepairRequest> {

    @ViewComponent
    private DataGrid<RepairRequest> repairRequestsDataGrid;

    @Override
    public void onAttach(AttachEvent event) {
        super.onAttach(event);

        // Добавляем столбец для TaskStatus с возможностью сортировки
        repairRequestsDataGrid.addColumn(new ComponentRenderer<>(request -> {
                    TaskStatus status = request.getTaskStatus();
                    Span statusSpan = new Span(status != null ? status.toString() : "");

                    switch (status) {
                        case CREATED:
                            statusSpan.getStyle().set("color", "blue");
                            statusSpan.setText("Создана");
                            break;
                        case IN_PROGRESS:
                            statusSpan.getStyle().set("color", "orange");
                            statusSpan.setText("В работе");
                            break;
                        case COMPLITED:
                            statusSpan.getStyle().set("color", "green");
                            statusSpan.setText("Завершена");
                            break;
                        default:
                            statusSpan.setText("Неизвестно");
                            break;
                    }

                    return statusSpan;
                }))
                .setHeader("Статус заявки")
                .setAutoWidth(true)
                .setComparator((request1, request2) -> {
                    // Сортировка по значению перечисления TaskStatus
                    TaskStatus status1 = request1.getTaskStatus();
                    TaskStatus status2 = request2.getTaskStatus();
                    return status1 != null && status2 != null ? status1.compareTo(status2) : 0;
                });
    }
}

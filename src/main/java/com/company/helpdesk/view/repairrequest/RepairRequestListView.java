package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.entity.RepairRequestEquipment;
import com.company.helpdesk.entity.TaskStatus;
import com.company.helpdesk.entity.User;
import com.company.helpdesk.service.RepairRequestService;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Route(value = "repairRequests", layout = MainView.class)
@ViewController(id = "RepairRequest.list")
@ViewDescriptor(path = "repair-request-list-view.xml")
@LookupComponent("repairRequestsDataGrid")
@DialogMode(width = "64em")
public class RepairRequestListView extends StandardListView<RepairRequest> {

    @ViewComponent
    private DataGrid<RepairRequest> repairRequestsDataGrid;

    @ViewComponent
    private DataContext dataContext;

    @ViewComponent
    private CollectionLoader<RepairRequest> repairRequestsDl;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private ViewNavigators viewNavigators;

    @Autowired
    private com.company.helpdesk.service.RepairRequestService repairRequestService;

    @Override
    public void onAttach(AttachEvent event) {
        super.onAttach(event);

        // Отображение статуса
        repairRequestsDataGrid.addColumn(new ComponentRenderer<>(request -> {
                    TaskStatus status = request.getTaskStatus();
                    Span statusSpan = new Span(status != null ? status.toString() : "");

                    switch (status) {
                        case CREATED:
                            statusSpan.getStyle().set("color", "orange");
                            statusSpan.setText("Создана");
                            break;
                        case IN_PROGRESS:
                            statusSpan.getStyle().set("color", "blue");
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
                .setComparator((r1, r2) -> {
                    TaskStatus s1 = r1.getTaskStatus();
                    TaskStatus s2 = r2.getTaskStatus();
                    return s1 != null && s2 != null ? s1.compareTo(s2) : 0;
                });
    }

    @Subscribe("repairRequestsDataGrid.create")
    public void onCreateButtonClick(ActionPerformedEvent event) {
        Collection<String> roles = currentAuthentication.getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (roles.contains("ROLE_user-role")) {
            // Открываем пошаговый мастер
            viewNavigators.view(this, RepairRequestWizardView.class).navigate();
        } else {
            // Открываем стандартный экран создания заявки
            viewNavigators.detailView(this, RepairRequest.class)
                    .newEntity()
                    .navigate();
        }
    }

    @Subscribe("completeButton")
    public void onCompleteButtonClick(ClickEvent<Button> event) {
        RepairRequest repairRequest = repairRequestsDataGrid.getSingleSelectedItem();
        if (repairRequest != null) {
            repairRequestService.completeRepairRequest(repairRequest);
            dataContext.save();
            repairRequestsDl.load(); // Обновление списка
        } else {
            Notification.show("Выберите заявку для завершения");
        }
    }
}

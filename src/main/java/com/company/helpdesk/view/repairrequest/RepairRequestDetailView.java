package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.entity.User;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "repairRequests/:id", layout = MainView.class)
@ViewController(id = "RepairRequest.detail")
@ViewDescriptor(path = "repair-request-detail-view.xml")
@EditedEntityContainer("repairRequestDc")
public class RepairRequestDetailView extends StandardDetailView<RepairRequest> {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInitEntity(InitEntityEvent<RepairRequest> event) {
        // Получаем текущего пользователя
        User user = (User) currentAuthentication.getUser();

        // Устанавливаем пользователя в ремонтный запрос
        RepairRequest repairRequest = event.getEntity();
        repairRequest.setUser(user);
    }
}

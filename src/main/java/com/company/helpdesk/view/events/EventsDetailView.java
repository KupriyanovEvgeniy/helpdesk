package com.company.helpdesk.view.events;

import com.company.helpdesk.entity.Events;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "events/:id", layout = MainView.class)
@ViewController(id = "Events.detail")
@ViewDescriptor(path = "events-detail-view.xml")
@EditedEntityContainer("eventsDetailDc")

public class EventsDetailView extends StandardDetailView<Events> {
}

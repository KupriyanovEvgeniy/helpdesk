package com.company.helpdesk.view.events;


import com.company.helpdesk.entity.Events;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.data.grid.ContainerDataGridItems;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

import java.util.Date;


@Route(value = "eventsView", layout = MainView.class)
@ViewController(id = "Eventsview")
@ViewDescriptor(path = "eventsView.xml")
@DialogMode(width = "64em")
public class Eventsview extends StandardView {
    private String query = "SELECT e FROM Events e WHERE ";
    @ViewComponent
    private DataGrid<Events> eventsTable;
    @ViewComponent
    private CollectionContainer<Events> eventsDc;
    @ViewComponent
    private CollectionLoader<Events> eventsDl;
    @Subscribe
    public void selectQuery(String param){
        query += "e.date='" + param + "'";
    }
    @Subscribe
    public void onInit(InitEvent event){
        eventsTable.setItems(new ContainerDataGridItems<>(eventsDc));
    }
    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        eventsDl.setQuery(query);
        eventsDl.load();
    }
    
}
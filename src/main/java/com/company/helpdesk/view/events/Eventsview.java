package com.company.helpdesk.view.events;
import com.company.helpdesk.entity.Events;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.data.grid.ContainerDataGridItems;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import java.sql.Date;
import java.time.LocalDate;


@Route(value = "eventsView", layout = MainView.class)
@ViewController(id = "Eventsview")
@ViewDescriptor(path = "eventsView.xml")
@LookupComponent("eventsTable")
@DialogMode(width = "64em")
public class Eventsview extends StandardView {
    private String param;
    private String query = "SELECT e FROM Events e WHERE ";
    @ViewComponent
    private DataGrid<Events> eventsTable;
    @ViewComponent
    private CollectionContainer<Events> eventsDc;
    @ViewComponent
    private CollectionLoader<Events> eventsDl;

    @Subscribe
    public void selectQuery(String param){
        this.param = param;
        query += "e.date='" + param + "'";
    }
    @Subscribe
    public void onInit(InitEvent event){eventsTable.setItems(new ContainerDataGridItems<>(eventsDc));}
    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        eventsDl.setQuery(query);
        eventsDl.load();
    }

    @ViewComponent("eventsTable.create")
    @Install(to = "eventsTable.create", subject = "initializer")
    public void setEventsTableCreateInit(Events events){
        events.setDate(Date.valueOf(LocalDate.of(Integer.parseInt(param.split("-")[0]), Integer.parseInt(param.split("-")[1]), Integer.parseInt(param.split("-")[2]))));
    }
}
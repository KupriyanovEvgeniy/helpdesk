package com.company.helpdesk.view.calendar;

import com.company.helpdesk.view.events.Eventsview;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Route(value = "calendar", layout = MainView.class)
@ViewController("Calendar")
@ViewDescriptor("calendar.xml")

public class Calendar extends StandardView {
    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private Section mainSection;
    @ViewComponent
    private H1 yearPanel;
    @ViewComponent
    private H3 monthLabel;
    @Autowired
    private UiComponents uiComponents;
    public GetDate my_data = new GetDate();
    @Autowired
    public DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onInit(final InitEvent event){
        my_data.setStandartView(this);
        my_data.getCurrentDate();
        my_data.setDialogWindow(dialogWindows);
        my_data.setDataManager(dataManager);
        my_data.setNotifications(notifications);
        monthLabel.setText(my_data.getMonthName());
        yearPanel.setText(my_data.getYearName());
        for(int i = 0; i < my_data.numberOfRows(); i++){
            Div calendar_rows = new Div();
            Collection<Component> buttons_data = my_data.getButtons(uiComponents);
            calendar_rows.add(buttons_data);
            mainSection.add(calendar_rows);
        }
    }
    @Subscribe(id = "buttonRight", subject = "clickListener")
    public void onButtonRightClick(final ClickEvent<JmixButton> event) {
        my_data.moveRigth();
        mainSection.removeAll();
        monthLabel.setText(my_data.getMonthName());
        yearPanel.setText(my_data.getYearName());
        for(int i = 0; i < my_data.numberOfRows(); i++){
            Div calendar_rows = new Div();
            Collection<Component> buttons_data = my_data.getButtons(uiComponents);
            calendar_rows.add(buttons_data);
            mainSection.add(calendar_rows);
        }
    }
    @Subscribe(id = "buttonLeft", subject = "clickListener")
    public void onButtonLeftClick(final ClickEvent<JmixButton> event) {
        my_data.moveLeft();
        mainSection.removeAll();
        monthLabel.setText(my_data.getMonthName());
        yearPanel.setText(my_data.getYearName());
        for(int i = 0; i < my_data.numberOfRows(); i++){
            Div calendar_rows = new Div();
            Collection<Component> buttons_data = my_data.getButtons(uiComponents);
            calendar_rows.add(buttons_data);
            mainSection.add(calendar_rows);
        }
    }
}
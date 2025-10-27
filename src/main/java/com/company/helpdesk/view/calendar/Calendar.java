package com.company.helpdesk.view.calendar;

import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Route(value = "calendar", layout = MainView.class)
@ViewController("Calendar")
@ViewDescriptor("calendar.xml")

public class Calendar extends StandardView {
    @ViewComponent
    private Section mainSection;
    @ViewComponent
    private Button buttonRight;
    @ViewComponent
    private Button buttonLeft;

    @ViewComponent
    private H1 yearPanel;
    @ViewComponent
    private H3 monthLabel;
    @Autowired
    private UiComponents uiComponents;
    public GetDate my_data = new GetDate();
    @Subscribe
    public void onInit(final InitEvent event){
        my_data.getCurrentDate();
        monthLabel.setText(my_data.getMonthName());
        yearPanel.setText(my_data.getYearName());
        for(int i = 0; i < my_data.numbeOfRows(); i++){
            Div calendar_rows = new Div();
            Collection<Component> buttons_data = my_data.getButtons(uiComponents);
            calendar_rows.add(buttons_data);
            mainSection.add(calendar_rows);
        }
    }
    @Subscribe(id = "buttonRight", subject = "clickListener")
    public void onButtonRightClick(final ClickEvent<JmixButton> event) {
        my_data.moveRigth();
        monthLabel.setText(my_data.getMonthName());
        yearPanel.setText(my_data.getYearName());
        mainSection.removeAll();
        for(int i = 0; i < my_data.numbeOfRows(); i++){
            Div calendar_rows = new Div();
            Collection<Component> buttons_data = my_data.getButtons(uiComponents);
            calendar_rows.add(buttons_data);
            mainSection.add(calendar_rows);
        }
    }
    @Subscribe(id = "buttonLeft", subject = "clickListener")
    public void onButtonLeftClick(final ClickEvent<JmixButton> event) {
        my_data.moveLeft();
        monthLabel.setText(my_data.getMonthName());
        yearPanel.setText(my_data.getYearName());
        mainSection.removeAll();
        for(int i = 0; i < my_data.numbeOfRows(); i++){
            Div calendar_rows = new Div();
            Collection<Component> buttons_data = my_data.getButtons(uiComponents);
            calendar_rows.add(buttons_data);
            mainSection.add(calendar_rows);
        }
    }
}
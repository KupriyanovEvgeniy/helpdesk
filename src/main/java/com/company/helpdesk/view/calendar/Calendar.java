package com.company.helpdesk.view.calendar;


import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "calendar", layout = MainView.class)
@ViewController(id = "Calendar")
@ViewDescriptor(path = "calendar.xml")
public class Calendar extends StandardView {
}
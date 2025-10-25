package com.company.helpdesk.view.calendar;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetDate {
    public LocalDate date = LocalDate.now();
    public LocalDate lastDay = date.withDayOfMonth(date.lengthOfMonth());
    public String[] month_lenght_str = lastDay.toString().split("-");
    public int month_lenght_int = Integer.parseInt(month_lenght_str[2]);
    public int monthDay;
    public int dayOfWeek;
    public int value = 0;
    public String[] dates = new String[month_lenght_int];
    public void get_all_month(){
        for(int i=1; i<=month_lenght_int; i++){
            monthDay = date.withDayOfMonth(i).getDayOfMonth();
            dayOfWeek = date.withDayOfMonth(i).getDayOfWeek().getValue();
            dates[i-1] = monthDay + "-" + dayOfWeek;
        }
    }
    public Collection<Component> getButtons(UiComponents uiComponent){
        get_all_month();
        Collection<Component> list = new ArrayList<>();
        System.out.println("HIGIT");
        for (int i = 1; i <= 7; i++) {//не трогай, это дни недели, они статичны
            if(value < dates.length){
                int header = Integer.valueOf(dates[value].split("-")[1]);
                if (i == header) {
                    JmixButton button = uiComponent.create(JmixButton.class);
                    button.setText(dates[value].split("-")[0]);
                    list.add(button);
                    value++;
                }
                else {
                    JmixButton button = uiComponent.create(JmixButton.class);
                    button.setText("-");
                    list.add(button);
                }
            }
            else{
                JmixButton button = uiComponent.create(JmixButton.class);
                button.setText("-");
                list.add(button);
            }
        }
        return list;
    }
}
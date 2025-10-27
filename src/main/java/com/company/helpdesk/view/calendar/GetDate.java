package com.company.helpdesk.view.calendar;

import com.vaadin.flow.component.Component;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class GetDate {
    public LocalDate date;
    public Integer monthLength;
    public int value = 0;
    public String[] monthDates;
    public void resetToZeroValue(){
        value = 0;
    }
    public Integer numbeOfRows(){
        int rowCount;
        String first_date = date.withDayOfMonth(1).getDayOfMonth() + "-" + date.withDayOfMonth(1).getDayOfWeek().getValue();
        String month = date.getMonth().toString();
        int monthLength = date.lengthOfMonth();
        System.out.println(month);
        if((first_date.equals("1-6")&&monthLength==31)||(first_date.equals("1-7")&&!month.equals("FEBRUARY"))){
            rowCount = 6;
        }
        else{
            rowCount = 5;
        }
        return rowCount;
    }
    public void getCurrentDate(){
        date = LocalDate.now();
        monthLength = date.lengthOfMonth();
        monthDates = getMonthDays();
    }
    public String[] getMonthDays(){
        String[] dates = new String[monthLength];
        for(int i=1; i<=monthLength; i++){
            dates[i-1] = date.withDayOfMonth(i).getDayOfMonth() + "-" + date.withDayOfMonth(i).getDayOfWeek().getValue();
        }
        return dates;
    }
    public Collection<Component> getButtons(UiComponents uiComponent){
        Collection<Component> list = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {//не трогай, это дни недели, они статичны
            if(value < monthDates.length){
                int header = Integer.valueOf(monthDates[value].split("-")[1]);
                if (i == header) {
                    JmixButton button = uiComponent.create(JmixButton.class);
                    button.setText(monthDates[value].split("-")[0]);
                    button.addClassName("calendar-buttons");
                    list.add(button);
                    value++;
                }
                else {
                    JmixButton button = uiComponent.create(JmixButton.class);
                    button.setText("-");
                    button.addClassName("calendar-buttons");
                    list.add(button);
                }
            }
            else{
                JmixButton button = uiComponent.create(JmixButton.class);
                button.setText("-");
                button.addClassName("calendar-buttons");
                list.add(button);
            }
        }
        return list;
    }
    public String getMonthName(){
        String month = date.getMonth().toString();
        switch (month){
            case "JANUARY":month = "Январь";break;
            case "FEBRUARY":month = "Февраль";break;
            case "MARCH":month = "Март";break;
            case "APRIL":month = "Апрель";break;
            case "MAY":month = "Май";break;
            case "JUNE":month = "Июнь";break;
            case "JULY":month = "Июль";break;
            case "AUGUST":month = "Август";break;
            case "SEPTEMBER":month = "Сентябрь";break;
            case "OCTOBER":month = "Октябрь";break;
            case "NOVEMBER":month = "Ноябрь";break;
            case "DECEMBER":month = "Декабрь";break;
        }
        return month;
    }
    public String getYearName(){
        String year = Integer.toString(date.getYear());
        return year;
    }
    public void moveLeft(){
        resetToZeroValue();
        date = date.minusMonths(1);
        monthLength = date.lengthOfMonth();
        monthDates = getMonthDays();
    }
    public void moveRigth(){
        resetToZeroValue();
        date = date.plusMonths(1);
        monthLength = date.lengthOfMonth();
        monthDates = getMonthDays();
    }
}
package com.company.helpdesk.view.calendar;
import com.company.helpdesk.entity.Events;
import com.company.helpdesk.view.events.Eventsview;
import com.vaadin.flow.component.Component;
import io.jmix.core.DataManager;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.DialogWindow;
import io.jmix.flowui.view.StandardView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GetDate {/*Этот класс отвечает за создание кнопок*/
    private StandardView parnetsClass;/*Переменная под родительский класс*/
    private DialogWindows parentsClassDialogWindow;/*Переменная под диалоговое окно*/
    private DataManager parentsdataManager; /*Для получения данных из БД*/
    private Notifications notifications;
    public void setStandartView(StandardView standardView){
        this.parnetsClass = standardView;
    }
    public void setDialogWindow(DialogWindows viewNavigators){
        this.parentsClassDialogWindow = viewNavigators;
    }
    public void setDataManager(DataManager dataManager){
        this.parentsdataManager = dataManager;
    }
    public void setNotifications(Notifications notifications){
        this.notifications = notifications;
    }
    private LocalDate date;
    private Integer monthLength;
    private String[] monthDates;

    /*Чисто техничесий момент для построения чисел по дням недели*/
    private int value = 0;
    public void resetToZeroValue(){
        value = 0;
    }
    /*Метод, который выводит количество строк в календаре для месяца*/
    public Integer numberOfRows(){
        int rowCount;
        String first_date = date.withDayOfMonth(1).getDayOfMonth() + "-" + date.withDayOfMonth(1).getDayOfWeek().getValue();
        String month = date.getMonth().toString();
        int monthLength = date.lengthOfMonth();
        if((first_date.equals("1-6")&&monthLength==31)||(first_date.equals("1-7")&&!month.equals("FEBRUARY"))){rowCount = 6;}
        else{rowCount = 5;}
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
    public void ButtonClick (JmixButton button){/*Тут определяем функционал кнопки*/
        button.addClickListener(event -> {
            String[] getData = date.toString().split("-");
            JmixButton clickedButton = (JmixButton) event.getSource();
            String buttonId = clickedButton.getId().orElse("noButtonId");
            int year = Integer.valueOf(getData[0]), month = Integer.valueOf(getData[1]), day = Integer.valueOf(buttonId);
            String queryDateParam = year + "-" + month + "-" + day;
            List<Events> DB_objects = parentsdataManager.load(Events.class).query("SELECT c FROM Events c WHERE c.date = '"+queryDateParam+"'").list();
            if(DB_objects.isEmpty()){
                notifications.create("На эту дату нет никаких событий").show();
            }
            else{
                DialogWindow<Eventsview> window = parentsClassDialogWindow.view(parnetsClass, Eventsview.class).build();
                window.getView().selectQuery(queryDateParam);
                window.open();
            }
        });
    }
    public Collection<Component> getButtons(UiComponents uiComponent){/*Тут формируем список кнопок*/
        Collection<Component> list = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {//не трогать, это дни недели, они статичны
            if(value < monthDates.length){
                int header = Integer.valueOf(monthDates[value].split("-")[1]);
                if (i == header) {
                    JmixButton button = uiComponent.create(JmixButton.class);
                    button.setId(monthDates[value].split("-")[0]);
                    button.setText(monthDates[value].split("-")[0]);
                    button.addClassName("calendar-buttons");
                    ButtonClick(button);/*Добавление хэндлера для кнопки в отдельном методе*/
                    list.add(button);/*Тут я добавляю саму кнопку в компонент*/
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
    /*Название месяца для шапки*/
    public String getMonthName(){
        String month = date.getMonth().toString();
        switch (month){case "JANUARY":month = "Январь";break;case "FEBRUARY":month = "Февраль";break;case "MARCH":month = "Март";break;case "APRIL":month = "Апрель";break;case "MAY":month = "Май";break;case "JUNE":month = "Июнь";break;case "JULY":month = "Июль";break;case "AUGUST":month = "Август";break;case "SEPTEMBER":month = "Сентябрь";break;case "OCTOBER":month = "Октябрь";break;case "NOVEMBER":month = "Ноябрь";break;case "DECEMBER":month = "Декабрь";break;}
        return month;
    }
    /*Год для боковой панели*/
    public String getYearName(){
        String year = Integer.toString(date.getYear());
        return year;
    }
    /*Функционал левой кнопки*/
    public void moveLeft(){
        resetToZeroValue();
        date = date.minusMonths(1);
        monthLength = date.lengthOfMonth();
        monthDates = getMonthDays();
    }
    /*Функционал правой кнопки*/
    public void moveRigth(){
        resetToZeroValue();
        date = date.plusMonths(1);
        monthLength = date.lengthOfMonth();
        monthDates = getMonthDays();
    }
}
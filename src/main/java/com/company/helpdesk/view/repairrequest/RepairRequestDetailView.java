package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.*;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.jmix.core.DataManager;  // Импортируем DataManager
import java.util.List;
import java.util.Collections;  // Импортируем Collections

@Route(value = "repairRequests/:id", layout = MainView.class)
@ViewController(id = "RepairRequest.detail")
@ViewDescriptor(path = "repair-request-detail-view.xml")
@EditedEntityContainer("repairRequestDc")
public class RepairRequestDetailView extends StandardDetailView<RepairRequest> {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private DataManager dataManager; // Внедряем DataManager

    @ViewComponent
    private EntityComboBox<Room> roomsComboBox;
    @ViewComponent
    private EntityComboBox<Equipment> equipmentsComboBox;

    @Subscribe
    public void onInitEntity(InitEntityEvent<RepairRequest> event) {
        // Получаем текущего пользователя
        User user = (User) currentAuthentication.getUser();

        // Устанавливаем значения по умолчанию
        RepairRequest repairRequest = event.getEntity();
        repairRequest.setUser(user);
        repairRequest.setTaskStatus(TaskStatus.CREATED); // Устанавливаем статус задачи в "CREATED"
    }

    @Subscribe("locationsComboBox")
    public void onLocationsComboBoxComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<Location>, Location> event) {
        Location selectedLocation = event.getValue();

        // Очистить комбобокс комнат
        roomsComboBox.clear(); // Очистить текущую выбранную комнату

        if (selectedLocation != null) {
            // Загружаем комнаты для выбранной локации
            List<Room> rooms = dataManager.load(Room.class)
                    .query("select r from Room r where r.location.id = :locationId")
                    .parameter("locationId", selectedLocation.getId())
                    .list();

            // Устанавливаем список комнат в roomsComboBox
            roomsComboBox.setItems(rooms);
        } else {
            // Если локация не выбрана, устанавливаем пустой список
            roomsComboBox.setItems(Collections.emptyList());
        }
    }

    @Subscribe("roomsComboBox")
    public void onRoomsComboBoxComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<Room>, Room> event) {
        Room selectedRoom = event.getValue();

        // Очистить комбобокс оборудования
        equipmentsComboBox.clear(); // Очистить текущий выбор оборудования

        if (selectedRoom != null) {
            // Загружаем оборудование для выбранной комнаты
            List<Equipment> equipmentList = dataManager.load(Equipment.class)
                    .query("select e from Equipment e where e.room.id = :roomId")
                    .parameter("roomId", selectedRoom.getId())
                    .list();

            // Устанавливаем список оборудования в equipmentsComboBox
            equipmentsComboBox.setItems(equipmentList);
        } else {
            // Если комната не выбрана, устанавливаем пустой список
            equipmentsComboBox.setItems(Collections.emptyList());
        }
    }

}

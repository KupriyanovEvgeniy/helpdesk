package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.*;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.jmix.core.DataManager;

import java.util.Collections;
import java.util.List;

@Route(value = "repairRequests/:id", layout = MainView.class)
@ViewController(id = "RepairRequest.detail")
@ViewDescriptor(path = "repair-request-detail-view.xml")
@EditedEntityContainer("repairRequestDc")
public class RepairRequestDetailView extends StandardDetailView<RepairRequest> {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private DataManager dataManager;

    @ViewComponent
    private EntityComboBox<Location> locationsComboBox;

    @ViewComponent
    private EntityComboBox<Room> roomsComboBox;

    @ViewComponent
    private EntityComboBox<Equipment> equipmentsComboBox;

    @ViewComponent
    private JmixSelect<EquipmentType> equipmentTypeField;

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

        roomsComboBox.clear(); // Очистить текущую выбранную комнату
        equipmentsComboBox.clear(); // Очистить текущий выбор оборудования

        if (selectedLocation != null) {
            // Загружаем комнаты для выбранной локации
            List<Room> rooms = dataManager.load(Room.class)
                    .query("select r from Room r where r.location.id = :locationId")
                    .parameter("locationId", selectedLocation.getId())
                    .list();

            roomsComboBox.setItems(rooms);
        } else {
            // Если локация не выбрана, очищаем список комнат
            roomsComboBox.setItems(Collections.emptyList());
        }

        // Также очищаем список оборудования, если меняется локация
        equipmentsComboBox.setItems(Collections.emptyList());
    }

    @Subscribe("roomsComboBox")
    public void onRoomsComboBoxComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<Room>, Room> event) {
        Room selectedRoom = event.getValue();
        EquipmentType selectedType = equipmentTypeField.getValue(); // Получаем текущий тип оборудования

        equipmentsComboBox.clear(); // Очистить текущий выбор оборудования

        if (selectedRoom != null) {
            // Если выбран тип оборудования, фильтруем по типу и комнате
            List<Equipment> equipmentList = dataManager.load(Equipment.class)
                    .query("select e from Equipment e where e.room.id = :roomId and (:equipmentType is null or e.equipmentType = :equipmentType)")
                    .parameter("roomId", selectedRoom.getId())
                    .parameter("equipmentType", selectedType)
                    .list();

            equipmentsComboBox.setItems(equipmentList);
        } else {
            // Если комната не выбрана, очищаем список оборудования
            equipmentsComboBox.setItems(Collections.emptyList());
        }
    }

    @Subscribe("equipmentTypeField")
    public void onEquipmentTypeFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixSelect<EquipmentType>, EquipmentType> event) {
        EquipmentType selectedType = event.getValue();
        Room selectedRoom = roomsComboBox.getValue(); // Получаем текущую выбранную комнату

        equipmentsComboBox.clear(); // Очистить текущий выбор оборудования

        if (selectedRoom != null) {
            // Если выбрана комната и тип оборудования, фильтруем по обоим критериям
            List<Equipment> filteredEquipmentList = dataManager.load(Equipment.class)
                    .query("select e from Equipment e where e.room.id = :roomId and (:equipmentType is null or e.equipmentType = :equipmentType)")
                    .parameter("roomId", selectedRoom.getId())
                    .parameter("equipmentType", selectedType)
                    .list();

            equipmentsComboBox.setItems(filteredEquipmentList);
        } else if (selectedType != null) {
            // Если выбран только тип оборудования
            List<Equipment> filteredEquipmentList = dataManager.load(Equipment.class)
                    .query("select e from Equipment e where e.equipmentType = :equipmentType")
                    .parameter("equipmentType", selectedType)
                    .list();

            equipmentsComboBox.setItems(filteredEquipmentList);
        } else {
            // Если ничего не выбрано, очищаем комбобокс оборудования
            equipmentsComboBox.setItems(Collections.emptyList());
        }
    }
}

package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.*;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.DataComponents;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "repairRequestWizards", layout = MainView.class)
@ViewController("RepairRequestWizard")
@ViewDescriptor("repair-request-wizard-view.xml")
@DialogMode(width = "40em")
public class RepairRequestWizardView extends StandardView {

    @ViewComponent
    private Div contentBox;

    @Autowired
    private DataComponents dataComponents;

    @Autowired
    private UiComponents uiComponents;

    @Autowired
    private Metadata metadata;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CurrentAuthentication currentAuthentication;

    private InstanceContainer<RepairRequest> repairRequestDc;
    private int step = 1;

    @Subscribe
    public void onInit(InitEvent event) {
        repairRequestDc = dataComponents.createInstanceContainer(RepairRequest.class);
        repairRequestDc.setItem(metadata.create(RepairRequest.class));

        showStep1();
    }

    private void resetContentBox() {
        contentBox.removeAll();
        contentBox.getStyle()
                .clear()
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("justify-content", "center")
                .set("align-items", "center")
                .set("height", "100%")
                .set("width", "100%")
                .set("gap", "20px")
                .set("padding", "20px");
    }

    private void showStep1() {
        resetContentBox();

        Span title = uiComponents.create(Span.class);
        title.setText("Шаг 1/3: Выберите сломанное оборудование");
        title.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold")
                .set("width", "100%")
                .set("text-align", "center")
                .set("margin-bottom", "24px");

        // Центрирование контента и удаление лишнего отступа
        contentBox.getStyle()
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center")
                .set("gap", "16px")
                .remove("padding");

        contentBox.add(title);

        Div cardsContainer = uiComponents.create(Div.class);
        cardsContainer.setWidthFull();
        cardsContainer.getStyle()
                .set("display", "flex")
                .set("flex-wrap", "wrap")
                .set("justify-content", "center")
                .set("gap", "20px");

        for (EquipmentType type : EquipmentType.values()) {
            Div card = uiComponents.create(Div.class);
            card.setWidth("200px");
            card.setHeight("250px");
            card.getStyle()
                    .set("border", "2px solid #ccc")
                    .set("border-radius", "12px")
                    .set("padding", "16px")
                    .set("background-color", "#f9f9f9")
                    .set("display", "flex")
                    .set("flex-direction", "column")
                    .set("align-items", "center")
                    .set("justify-content", "center")
                    .set("cursor", "pointer")
                    .set("box-shadow", "2px 2px 6px rgba(0,0,0,0.1)")
                    .set("transition", "transform 0.1s ease-in-out");

            card.getElement().executeJs(
                    "this.addEventListener('mouseenter', () => this.style.transform = 'scale(1.03)');" +
                            "this.addEventListener('mouseleave', () => this.style.transform = 'scale(1)');"
            );

            Image image = uiComponents.create(Image.class);
            image.setSrc("/images/equipment/" + type.name().toLowerCase() + ".png");
            image.setAlt(type.getId());
            image.setWidth("150px");
            image.setHeight("150px");

            Span label = uiComponents.create(Span.class);
            label.setText(type.getId());
            label.getStyle()
                    .set("font-size", "1.2em")
                    .set("margin-top", "12px");

            card.add(image, label);

            card.addClickListener(click -> {
                repairRequestDc.getItem().setEquipmentType(type);
                showStep2();
            });

            cardsContainer.add(card);
        }

        contentBox.add(cardsContainer);
    }




    private void showStep2() {
        resetContentBox();

        Span title = uiComponents.create(Span.class);
        title.setText("Шаг 2: Выберите кабинет и модель оборудования");
        title.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold")
                .set("margin-bottom", "20px")
                .set("text-align", "center");

        ComboBox<Room> roomComboBox = uiComponents.create(ComboBox.class);
        roomComboBox.setLabel("Кабинет");
        roomComboBox.setItems(dataManager.load(Room.class).all().list());
        roomComboBox.setItemLabelGenerator(Room::getRoomName);
        roomComboBox.getStyle()
                .set("width", "300px")
                .set("font-size", "18px");

        if (repairRequestDc.getItem().getRoom() == null) {
            User currentUser = (User) currentAuthentication.getUser();
            if (currentUser.getRoom() != null) {
                repairRequestDc.getItem().setRoom(currentUser.getRoom());
            }
        }

        if (repairRequestDc.getItem().getRoom() != null) {
            roomComboBox.setValue(repairRequestDc.getItem().getRoom());
        }

        Span errorRoom = uiComponents.create(Span.class);
        errorRoom.getStyle().set("color", "red").set("font-size", "14px").set("margin-top", "-10px");
        errorRoom.setVisible(false);

        ComboBox<Equipment> modelComboBox = uiComponents.create(ComboBox.class);
        modelComboBox.setLabel("Модель оборудования");
        modelComboBox.setItems(
                dataManager.load(Equipment.class)
                        .query("select e from Equipment e where e.equipmentType = :type")
                        .parameter("type", repairRequestDc.getItem().getEquipmentType())
                        .list()
        );
        modelComboBox.setItemLabelGenerator(Equipment::getModel);
        modelComboBox.getStyle()
                .set("width", "300px")
                .set("font-size", "18px");

        if (repairRequestDc.getItem().getEquipment() != null) {
            modelComboBox.setValue(repairRequestDc.getItem().getEquipment());
        }

        Span errorModel = uiComponents.create(Span.class);
        errorModel.getStyle().set("color", "red").set("font-size", "14px").set("margin-top", "-10px");
        errorModel.setVisible(false);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonLayout.setSpacing(true);

        Button back = uiComponents.create(Button.class);
        back.setText("Назад");
        back.addThemeVariants(ButtonVariant.LUMO_ERROR);
        back.getStyle()
                .set("font-size", "18px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "140px");

        back.addClickListener(e -> {
                showStep1();
                repairRequestDc.getItem().setEquipment(null);
                repairRequestDc.getItem().setDescription(null);});

        Button next = uiComponents.create(Button.class);
        next.setText("Далее");
        next.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        next.getStyle()
                .set("font-size", "18px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "140px");

        next.addClickListener(e -> {
            boolean valid = true;

            if (roomComboBox.getValue() == null) {
                roomComboBox.getStyle().set("border", "2px solid red").set("background-color", "#ffebee");
                errorRoom.setText("Пожалуйста, выберите кабинет");
                errorRoom.setVisible(true);
                valid = false;
            } else {
                roomComboBox.getStyle().remove("border").remove("background-color");
                errorRoom.setVisible(false);
            }

            if (modelComboBox.getValue() == null) {
                modelComboBox.getStyle().set("border", "2px solid red").set("background-color", "#ffebee");
                errorModel.setText("Пожалуйста, выберите модель оборудования");
                errorModel.setVisible(true);
                valid = false;
            } else {
                modelComboBox.getStyle().remove("border").remove("background-color");
                errorModel.setVisible(false);
            }

            if (valid) {
                repairRequestDc.getItem().setRoom(roomComboBox.getValue());
                repairRequestDc.getItem().setEquipment(modelComboBox.getValue());
                showStep3();
            }
        });

        buttonLayout.add(back, next);

        contentBox.add(title, roomComboBox, errorRoom, modelComboBox, errorModel, buttonLayout);
    }




    private void showStep3() {
        resetContentBox();

        Span title = uiComponents.create(Span.class);
        title.setText("Шаг 3/3: Опишите проблему");
        title.getStyle()
                .set("font-size", "26px")
                .set("font-weight", "bold")
                .set("margin-bottom", "10px")
                .set("text-align", "center");

        TextArea descriptionField = uiComponents.create(TextArea.class);
        descriptionField.setLabel("Описание поломки");
        descriptionField.setPlaceholder("Напишите, что случилось...");
        descriptionField.setWidthFull();
        descriptionField.setHeight("250px");
        descriptionField.getStyle()
                .set("max-width", "600px")
                .set("font-size", "18px");

        if (repairRequestDc.getItem().getDescription() != null) {
            descriptionField.setValue(repairRequestDc.getItem().getDescription());
        }

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buttonLayout.setSpacing(true);

        Button back = uiComponents.create(Button.class);
        back.setText("Назад");
        back.addThemeVariants(ButtonVariant.LUMO_ERROR);
        back.getStyle()
                .set("font-size", "18px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "140px");

        back.addClickListener(e -> {
            repairRequestDc.getItem().setDescription(descriptionField.getValue());
            showStep2();
        });

        Button done = uiComponents.create(Button.class);
        done.setText("Готово");
        done.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        done.getStyle()
                .set("font-size", "18px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "140px");

        done.addClickListener(e -> {
            if (descriptionField.getValue() == null || descriptionField.getValue().isBlank()) {
                notifications.create("Пожалуйста, опишите поломку перед отправкой.").show();
                return;
            }

            User currentUser = (User) currentAuthentication.getUser();

            repairRequestDc.getItem().setTaskStatus(TaskStatus.CREATED);
            repairRequestDc.getItem().setUser(currentUser);
            repairRequestDc.getItem().setPhoneNumber(currentUser.getPhoneNumber());
            repairRequestDc.getItem().setLocation(currentUser.getLocation());
            repairRequestDc.getItem().setPriority(currentUser.getPriority());
            repairRequestDc.getItem().setDescription(descriptionField.getValue());

            dataManager.save(repairRequestDc.getItem());
            notifications.create("Заявка успешно создана!").show();

            showStep1(); // Сброс или переход
        });

        buttonLayout.add(back, done);
        contentBox.add(title, descriptionField, buttonLayout);
    }





}

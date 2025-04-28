package com.company.helpdesk.view.repairrequest;

import com.company.helpdesk.entity.Equipment;
import com.company.helpdesk.entity.EquipmentType;
import com.company.helpdesk.entity.RepairRequest;
import com.company.helpdesk.entity.Room;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.DataComponents;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

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

    private InstanceContainer<RepairRequest> repairRequestDc;
    private int step = 1;

    @Subscribe
    public void onInit(InitEvent event) {
        repairRequestDc = dataComponents.createInstanceContainer(RepairRequest.class);
        repairRequestDc.setItem(metadata.create(RepairRequest.class));

        showStep1();
    }

    private void showStep1() {
        contentBox.removeAll();

        contentBox.getStyle()
                .set("display", "flex")
                .set("flex-wrap", "wrap")
                .set("flex-direction", "row")
                .set("justify-content", "center")
                .set("align-items", "center")
                .set("gap", "20px")
                .set("width", "100%")
                .set("height", "100%")
                .set("padding", "20px");

        for (EquipmentType type : EquipmentType.values()) {
            // Карточка
            Div card = uiComponents.create(Div.class);
            card.setWidth("200px");
            card.setHeight("250px");
            card.getStyle().set("border", "2px solid #ccc");
            card.getStyle().set("border-radius", "12px");
            card.getStyle().set("padding", "16px");
            card.getStyle().set("background-color", "#f9f9f9");
            card.getStyle().set("display", "flex");
            card.getStyle().set("flex-direction", "column");
            card.getStyle().set("align-items", "center");
            card.getStyle().set("justify-content", "center");
            card.getStyle().set("cursor", "pointer");
            card.getStyle().set("box-shadow", "2px 2px 6px rgba(0,0,0,0.1)");
            card.getStyle().set("transition", "transform 0.1s ease-in-out");


            // Наведение — эффект увеличения
            card.getElement().executeJs(
                    "this.addEventListener('mouseenter', () => this.style.transform = 'scale(1.03)');" +
                            "this.addEventListener('mouseleave', () => this.style.transform = 'scale(1)');"
            );

            // Картинка
            Image image = uiComponents.create(Image.class);
            image.setSrc("/images/equipment/" + type.name().toLowerCase() + ".png");
            image.setAlt(type.getId());
            image.setWidth("150px");
            image.setHeight("150px");

            // Подпись
            Span label = uiComponents.create(Span.class);
            label.setText(type.getId());
            label.getStyle().set("font-size", "1.2em");
            label.getStyle().set("margin-top", "12px");

            card.add(image, label);

            card.addClickListener(click -> {
                repairRequestDc.getItem().setEquipmentType(type);
                showStep2();
            });

            contentBox.add(card);
        }
    }

    private void showStep2() {
        contentBox.removeAll();

        contentBox.getStyle()
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("gap", "20px")
                .set("width", "100%")
                .set("height", "100%")
                .set("padding", "20px")
                .set("font-size", "18px");

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

        Span errorRoom = uiComponents.create(Span.class);
        errorRoom.getStyle()
                .set("color", "red")
                .set("font-size", "14px")
                .set("margin-top", "-10px");
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

        Span errorModel = uiComponents.create(Span.class);
        errorModel.getStyle()
                .set("color", "red")
                .set("font-size", "14px")
                .set("margin-top", "-10px");
        errorModel.setVisible(false);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.getStyle()
                .set("display", "flex")
                .set("gap", "20px")
                .set("margin-top", "30px");

        Button back = uiComponents.create(Button.class);
        back.setText("Назад");
        back.getStyle()
                .set("background-color", "#f44336")
                .set("color", "white")
                .set("font-size", "20px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "150px");

        back.addClickListener(e -> showStep1());

        Button next = uiComponents.create(Button.class);
        next.setText("Далее");
        next.getStyle()
                .set("background-color", "#4CAF50")
                .set("color", "white")
                .set("font-size", "20px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "150px");

        next.addClickListener(e -> {
            boolean valid = true;

            if (roomComboBox.getValue() == null) {
                roomComboBox.getStyle()
                        .set("border", "2px solid red")
                        .set("background-color", "#ffebee");
                errorRoom.setText("Пожалуйста, выберите кабинет");
                errorRoom.setVisible(true);
                valid = false;
            } else {
                roomComboBox.getStyle()
                        .remove("border")
                        .remove("background-color");
                errorRoom.setVisible(false);
            }

            if (modelComboBox.getValue() == null) {
                modelComboBox.getStyle()
                        .set("border", "2px solid red")
                        .set("background-color", "#ffebee");
                errorModel.setText("Пожалуйста, выберите модель оборудования");
                errorModel.setVisible(true);
                valid = false;
            } else {
                modelComboBox.getStyle()
                        .remove("border")
                        .remove("background-color");
                errorModel.setVisible(false);
            }

            if (valid) {
                repairRequestDc.getItem().setRoom(roomComboBox.getValue());
                repairRequestDc.getItem().setEquipment(modelComboBox.getValue());
                showStep3();
            }
        });

        buttonLayout.add(back, next);

        contentBox.add(
                title,
                roomComboBox, errorRoom,
                modelComboBox, errorModel,
                buttonLayout
        );
    }



    private void showStep3() {
        contentBox.removeAll();

        contentBox.getStyle()
                .set("display", "flex")
                .set("flex-direction", "column")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("gap", "20px")
                .set("width", "100%")
                .set("height", "100%")
                .set("padding", "20px");

        TextArea descriptionField = new TextArea("Опишите поломку");
        descriptionField.setWidth("500px");
        descriptionField.setHeight("200px");
        descriptionField.getStyle().set("font-size", "18px");

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.getStyle()
                .set("display", "flex")
                .set("gap", "20px");

        Button back = uiComponents.create(Button.class);
        back.setText("Назад");
        back.getStyle()
                .set("background-color", "#f44336")
                .set("color", "white")
                .set("font-size", "20px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "150px");

        back.addClickListener(e -> showStep2());

        Button done = uiComponents.create(Button.class);
        done.setText("Готово");
        done.getStyle()
                .set("background-color", "#4CAF50")
                .set("color", "white")
                .set("font-size", "20px")
                .set("padding", "10px 20px")
                .set("border-radius", "8px")
                .set("width", "150px");

        done.addClickListener(e -> {
            repairRequestDc.getItem().setDescription(descriptionField.getValue());
            System.out.println("Готово: " + repairRequestDc.getItem());
            // TODO: перейти на детальную форму, сохранить или передать дальше
        });

        buttonLayout.add(back, done);

        contentBox.add(descriptionField, buttonLayout);
    }


}

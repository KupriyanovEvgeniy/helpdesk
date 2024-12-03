package com.company.helpdesk.view.room;

import com.company.helpdesk.entity.Room;
import com.company.helpdesk.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "rooms/:id", layout = MainView.class)
@ViewController(id = "Room.detail")
@ViewDescriptor(path = "room-detail-view.xml")
@EditedEntityContainer("roomDc")
public class RoomDetailView extends StandardDetailView<Room> {
}
module Bibliotek {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens Client;
    opens Client.Controller;
    opens Client.Controller.Staff;
    opens Client.Controller.Visitor;
    opens view;
    opens view.staff;
    opens view.visitor;
    opens img;
}
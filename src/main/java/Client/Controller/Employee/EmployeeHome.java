package Client.Controller.Employee;

import Client.Controller.ControllerUtil;
import Model.UserTest;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Toshiko Kuno
 * Date: 2020-11-29
 * Time: 17:21
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class EmployeeHome {
    public AnchorPane loggedInEmployee;
    public Button employeeTop;
    public Button history;
    public static UserTest currentLoggedInUser;


    public void goToEmployeeTop() {
    }

    public void goToHistory() {
    }

    public void goToBookManage() {
    }

    public void goToUserManage() {
    }

    public void goToRegisterBooks() {
    }

    public void goToLogOut() {
        loggedInEmployee.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "logIn"));
    }

    public void goToSearch() {
    }
}
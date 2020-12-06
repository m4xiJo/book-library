package Client.Controller.Employee;

import Client.Controller.ControllerUtil;
import Client.UserUtil;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Created by Miwa Guhrés
 * Date: 2020-12-05
 * Time: 10:18
 * Project: Bibliotek
 * Copyright: MIT
 */
public class RegisterUser {
    public AnchorPane registerUserPane;
    public ToggleGroup userCat;
    public TextField firstNameT;
    public TextField lastNameT;
    public TextField ssnT;
    public TextField passwdT;
    public TextField telT;
    public TextField addressT;
    public TextField emailT;
    public Text message;

    public void goToLogOut() {
        registerUserPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "logIn"));

    }

    public void goToEmployeeTop() {
    }

    public void goToHistory() {
    }

    public void goToBookManage() {
    }

    public void goToUserManage() {
    }

    public void goToRegisterBooks() {
        registerUserPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "employee/registerBook"));
    }

    public void goToRegisterUser() {
        registerUserPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "employee/registerUser"));
    }

    public void goToSearch() {
    }

    public void actionRegister() {
        RadioButton userCategoryChoice = (RadioButton) userCat.getSelectedToggle();
        System.out.println(userCategoryChoice.getText());
        System.out.println(firstNameT.getText());

        try {
            //TODO hämta inmatning värde och kontrollera dem. t.ex Personnummer(ÅÅMMDDXXXX), email osv


            //Anropa registerUser metod och skicka alla user info
            UserUtil.registerUser();

        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Det gick något fel. Försök igen");
        }

    }
}

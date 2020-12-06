package Client.Controller;

import Client.UserUtil;
import Model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class LogIn implements Initializable {
    public AnchorPane loginPane;
    public TextField socialId;
    public PasswordField psw;
    public Button logInBtn;
    public Text message;
    public static User currentLoggedInUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Disable login knappen om man inte skriver personnummer eller löseord
        logInBtn.disableProperty().bind(socialId.textProperty().isEmpty().or(psw.textProperty().isEmpty()));
    }


    public void logIn() {
        //Hämta user list
        List<User> userList = UserUtil.getUserList();

        User tempUser = userList.stream()
                .filter(user -> user.getSsn().equalsIgnoreCase(socialId.getText())
                        && user.getPasswd().equals(psw.getText()))
                .findFirst()
                .orElse(null);

        if (tempUser != null) {
            if (tempUser.isStaff()) {
                currentLoggedInUser = tempUser;
                loginPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "employee/employeeHome"));
            } else {
                currentLoggedInUser = tempUser;
                loginPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "visitor/visitorHome"));
            }

        } else {
            message.setText("Vi hittar inte dig i systemet.  Försök logga in igen.");
            socialId.setText("");
            psw.setText("");
        }


    }

    public void goToTop() {
        loginPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "home"));
    }

    public void goToSearch() {
        loginPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "search"));
    }

    public void goToLogIn() {
        loginPane.getChildren().setAll(ControllerUtil.loadFMXLFiles(getClass(), "logIn"));
    }

    //Ta bort felmeddelande så fort användare skriver något
    public void onChangeText() {
        message.setText("");
    }
}

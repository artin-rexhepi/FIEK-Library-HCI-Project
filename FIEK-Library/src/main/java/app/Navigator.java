package app;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.UserService;
import repository.UserRepository;
import java.io.IOException;

public class Navigator {
    public final static String LOGIN_PAGE = "login_form.fxml";
    public final static String HOME_PAGE = "home.fxml";
    public final static String CREATE_ACCOUNT_PAGE = "CreateAccountForm.fxml";
    public final static String REGISTER_BOOK_PAGE = "register-book.fxml";
    public final static String ADMIN_PAGE = "admin_page.fxml";

    public static void navigate(Stage stage, String form) {
        Pane formPane = loadPane(form);
        if (formPane != null) {
            Scene newScene = new Scene(formPane);
            stage.setScene(newScene);
            stage.show();
        } else {
            System.err.println("Failed to load FXML file: " + form);
        }
    }

    public static void navigate(Node node, String form) {
        Stage stage = (Stage) node.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Pane pane, String form) {
        Pane formPane = loadPane(form);
        if (formPane != null) {
            pane.getChildren().clear();
            pane.getChildren().add(formPane);
        } else {
            System.err.println("Failed to load FXML file: " + form);
        }
    }

    private static Pane loadPane(String form) {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(form));
        try {
            Pane pane = loader.load();

            // Set the UserService on the controller
            Object controller = loader.getController();
            if (controller instanceof LoginController) {
                UserRepository userRepository = new UserRepository();
                UserService userService = new UserService(userRepository);
                ((LoginController) controller).setUserService(userService);
            }

            return pane;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
package pedronunesdev.javafxjdbc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import pedronunesdev.javafxjdbc.HelloApplication;
import pedronunesdev.javafxjdbc.service.DepartmentService;
import pedronunesdev.javafxjdbc.util.Alerts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;
    @FXML
    private MenuItem menuItemDepartment;
    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSellerAction(){

        System.out.println("onMenuItemSellerAction");
    }

    @FXML
    public void onMenuItemDepartmentAction(){

        loadView("/pedronunesdev/javafxjdbc/DepartmentList.fxml", (DepartmentListController controller) -> {
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemAboutAction(){
        loadView("/pedronunesdev/javafxjdbc/About.fxml", x -> {});
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private synchronized  <T> void loadView(String absoluteName, Consumer<T> initializingAction) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = HelloApplication.getMainScene();
            VBox mainVBOX = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBOX.getChildren().get(0);

            mainVBOX.getChildren().clear();
            mainVBOX.getChildren().add(mainMenu);
            mainVBOX.getChildren().addAll(newVbox.getChildren());

            T conrtoller = loader.getController();

            initializingAction.accept(conrtoller);

        }
        catch (IOException e){
            Alerts.showAlert("Io Exception", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }
}

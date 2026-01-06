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

        loadView2("/pedronunesdev/javafxjdbc/DepartmentList.fxml");
    }

    @FXML
    public void onMenuItemAboutAction(){
        loadView("/pedronunesdev/javafxjdbc/About.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadView(String absoluteName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = HelloApplication.getMainScene();
            VBox mainVBOX = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBOX.getChildren().get(0);

            mainVBOX.getChildren().clear();
            mainVBOX.getChildren().add(mainMenu);
            mainVBOX.getChildren().addAll(newVbox.getChildren());

        }
        catch (IOException e){
            Alerts.showAlert("Io Exception", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private void loadView2(String absoluteName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = HelloApplication.getMainScene();
            VBox mainVBOX = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBOX.getChildren().get(0);

            mainVBOX.getChildren().clear();
            mainVBOX.getChildren().add(mainMenu);
            mainVBOX.getChildren().addAll(newVbox.getChildren());

            DepartmentListController controller = loader.getController();
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        }
        catch (IOException e){
            Alerts.showAlert("Io Exception", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }
}

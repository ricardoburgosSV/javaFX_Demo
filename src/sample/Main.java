package sample;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.Scene; // Scenes are window's contents
import javafx.stage.Stage; // Stages are windows

public class Main extends Application {

    Stage window;
    Button btnAdd;
    Button btnRemove;
    Button btnSave;
    Label lblFilter;
    Label lblLeft;
    Label lblRight;
    ListView lstLeftList;
    ListView lstRightList;
    ComboBox cmbFilter;

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        window.setTitle("Dual list selection - JAVAFX DEMO");

        // Add Button
        btnAdd = new Button();
        btnAdd.setText("Add >>");
        btnAdd.setOnAction(e -> {
            lstRightList.getItems().addAll(lstLeftList.getSelectionModel().getSelectedItems());
            lstLeftList.getItems().removeAll(lstLeftList.getSelectionModel().getSelectedItems());
            lstLeftList.refresh();
            lstRightList.refresh();
        });

        btnRemove = new Button();
        btnRemove.setText("<< Remove");
        btnRemove.setOnAction(e -> {
            lstLeftList.getItems().addAll(lstRightList.getSelectionModel().getSelectedItems());
            lstRightList.getItems().removeAll(lstRightList.getSelectionModel().getSelectedItems());
            lstRightList.refresh();
            lstLeftList.refresh();
        });

        lblFilter = new Label("Filter");
        lblLeft = new Label("Available Players [Multiple selection is allowed with ctrl+left click and/or shift+left click]");
        lblRight = new Label("Your Team");

        // List manipulation
        lstLeftList = new ListView();
        lstLeftList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstLeftList.getItems().add("ITEM 1");
        lstLeftList.getItems().add("ITEM 2");
        lstLeftList.getItems().add("ITEM 3");
        lstLeftList.getItems().add("ITEM 4");
        lstLeftList.getItems().add("ITEM 5");
        lstLeftList.getItems().add("OBJECT A");
        lstLeftList.getItems().add("OBJECT B");
        lstLeftList.getItems().add("OBJECT C");
        lstLeftList.getItems().add("OBJECT D");
        lstLeftList.getItems().add("OBJECT E");

        lstRightList = new ListView();
        lstRightList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        cmbFilter = new ComboBox();
        cmbFilter.getItems().addAll("All", "Goal Keepers", "Defense", "Midfielders", "Forwards");
        cmbFilter.getSelectionModel().selectFirst();

        btnSave = new Button();
        btnSave.setText("Save & Close");
        btnSave.setOnAction(e -> window.close());

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(btnAdd, btnRemove, lblFilter, cmbFilter,
                lblLeft, lstLeftList, lblRight, lstRightList, btnSave);

        // Scene
        Scene scene = new Scene(layout,800, 600);
        window.setScene(scene);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

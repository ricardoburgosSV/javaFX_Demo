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
        for (availableItems items : availableItems.values()){
            lstLeftList.getItems().add(items);
        }

        lstRightList = new ListView();
        lstRightList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        cmbFilter = new ComboBox();
        cmbFilter.getItems().addAll("All", "Goal Keepers", "Defense", "Midfielders", "Forwards");
        cmbFilter.getSelectionModel().selectFirst();
        cmbFilter.setOnAction(e -> filterSelection());

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

    private void filterSelection(){
        switch (cmbFilter.getValue().toString()){
            case "All":
                System.out.println("ALL PLAYERS");
                break;
            case "Goal Keepers":
                lstLeftList.getItems().clear();
                lstLeftList.getItems().add("FILL WITH GOAL KEEPERS"); break;
            case "Defense":
                lstLeftList.getItems().clear();
                lstLeftList.getItems().add("FILL WITH DEFENSE"); break;
            case "Midfielders":
                lstLeftList.getItems().clear();
                lstLeftList.getItems().add("FILL WITH MIDFIELDERS"); break;
            case "Forwards":
                lstLeftList.getItems().clear();
                lstLeftList.getItems().add("FILL WITH FORWARDS"); break;
        }
    }

    private enum availableItems {
        ITEM_1,
        ITEM_2,
        ITEM_3,
        ITEM_4,
        ITEM_5,
        OBJECT_A,
        OBJECT_B,
        OBJECT_C,
        OBJECT_D,
        OBJECT_E
    }

    public static void main(String[] args) {
        launch(args);
    }

}

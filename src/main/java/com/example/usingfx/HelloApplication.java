package com.example.usingfx;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    private Menu m1, m2, m3;
    public TextArea text = null;
    private FileChooser fileChooser =null;
    private VBox vBMenu = null;
    private NotepadOperations note;
    @Override
    public void start(Stage stage) throws IOException {
        String file[] = {"File", "New", "Open", "Save", "Exit"};
        String edit[] = {"Edit","Undo", "Cut", "Copy", "Paste", "Delete", "Select All"};
        String about[] = {"About","About NOte"};
        text = new TextArea();
        vBMenu = new VBox();
        note = new NotepadOperations(text, fileChooser, vBMenu);
        text = new TextArea();
        MenuBar menuBar = new MenuBar();
        m1 = menu(file);
        m2 = menu(edit);
        m3 = menu(about);
        m1.getItems().forEach(item->{
            item.setOnAction(action->{
                this.handelEventM1(action, item);
            });
        });
        m2.getItems().forEach(item->{
            item.setOnAction(action->{
                this.handelEventM2(action, item);
            });
        });
        m3.getItems().forEach(item->{
            item.setOnAction(action->{
                this.handelEventM3(action, item);
            });
        });
        menuBar.getMenus().addAll(m1, m2, m3);
        BorderPane pane = new BorderPane();
        pane.setCenter(text);
        pane.setTop(menuBar);
        pane.setBottom(vBMenu);
        Scene scene = new Scene(pane,400,500);
        stage.setTitle("Notepad");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.show();
    }
    public void handelEventM1(ActionEvent event, MenuItem item){
        if(event.getSource().equals(item)){
            if (item.getText().equals("New"))text.clear();
            else if (item.getText().equals("Open"))this.openFile();
            else if (item.getText().equals("Save"))this.saveFile();
            else Platform.exit();
        }
    }

    public void handelEventM2(ActionEvent event, MenuItem item){
        if(event.getSource().equals(item)){
            if (item.getText().equals("Undo"))text.undo();
            else if (item.getText().equals("Cut"))note.cutFile();
            else if (item.getText().equals("Copy"))note.copyFile();
            else if (item.getText().equals("Paste"))note.pasteFile();
            else if (item.getText().equals("Delete"))note.deleteFile();
            else note.selectAllFile();
        }
    }

    public void handelEventM3(ActionEvent event, MenuItem item){
        if(event.getSource().equals(item)) note.aboutNode();
    }
    @Override
    public void init() throws Exception {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("H:\\ITI Programming\\java"));
    }

    public Menu menu(String[] arr){
        Menu menu = new Menu(arr[0]);
        MenuItem menuItem;
        for(int i = 1; i<arr.length; i++){
            menuItem = new MenuItem(arr[i]);
            menuItem.setId(arr[i]);
            menu.getItems().add(menuItem);
        }
        return menu;
    }
    public static void main(String[] args) {
        launch();
    }

    public void openFile(){
        Window stage = vBMenu.getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.setInitialFileName("fileSave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        try {
            File file = fileChooser.showOpenDialog(stage);
            FileInputStream fis = new FileInputStream(file);
            byte []b = new byte[fis.available()];
            fis.read(b);
            text.setText(new String(b));
            fis.close();
            fileChooser.setInitialDirectory(file.getParentFile()); //save the chosen directory
        }catch (Exception e){}
    }


    public void saveFile(){
        Window stage = vBMenu.getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save text file");
        fileChooser.setInitialFileName("fileSave");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));
        try {
            File file = fileChooser.showSaveDialog(stage);
            DataOutputStream d = new DataOutputStream(new FileOutputStream(file));
            d.writeUTF((text.getText()));
            fileChooser.setInitialDirectory(file.getParentFile()); //save the chosen directory
        }catch (Exception e){}
    }
}
package com.example.usingfx;


import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class NotepadOperations {
    private TextArea text;
    private FileChooser fileChooser;
    private VBox vBMenu;
    public NotepadOperations(TextArea text,FileChooser fileChooser,VBox vBMenu) {
        this.text = text;
        this.fileChooser = fileChooser;
        this.vBMenu = vBMenu;
    }


    public void undoFile() {
        text.undo();
    }
    public void cutFile(){
        text.cut();
    }
    public void copyFile(){
        text.copy();
    }
    public void pasteFile(){
        text.paste();
    }
    public void deleteFile(){
        text.cut();
    }
    public void selectAllFile(){
        text.selectAll();
    }
    public void aboutNode(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NotePad");
        alert.setHeaderText("Notepad developed in 12 jun 2022");
        alert.setContentText("copyright developed by mohamed samy");
        alert.showAndWait();
    }
}

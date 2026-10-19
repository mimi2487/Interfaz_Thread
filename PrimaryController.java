package com.mycompany.conteo;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML private ProgressBar progreso;
    @FXML private TextField clics;
    @FXML private Button btnEsperar;
    
    private int cont = 0;
    
    @FXML
    private void btnContar_click () {
        cont++;
        clics.setText(cont + "");
    }
    
    @FXML
    private void btnEsperar_click(){
        btnEsperar.setDisable(true);
        progreso.setProgress(0);
        
        Thread hilo = new Thread(()->{
        for(int i = 1; i<= 100; i++){
            try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            return;
        }
                final double avance = i/100.0;
                Platform.runLater(() ->progreso.setProgress(avance));
        }
        Platform.runLater(() -> btnEsperar.setDisable(false));
        });
        hilo.setDaemon(true);
        hilo.start();
    }
}

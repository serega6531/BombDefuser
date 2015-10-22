package ru.serega6531.bombefuser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.serega6531.bombefuser.enums.DigitType;
import ru.serega6531.bombefuser.enums.IndicatorLabel;
import ru.serega6531.bombefuser.enums.PortType;

import java.util.List;

public class Main extends Application {

    public static List<PortType> ports;
    public static List<Indicator> indicators;
    public static int batteries;
    public static String serial;

    public static Stage primaryStage, chooseStage, opened;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.opened = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("design/load.fxml"));
        primaryStage.setTitle("Data load");
        Scene scene = new Scene(root, 400, 270);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(270);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static boolean hasIndicator(IndicatorLabel label){
        for(Indicator i : indicators)
            if(i.getLabel() == label) return true;

        return false;
    }

    public static boolean hasIndicator(IndicatorLabel label, boolean light){
        for(Indicator i : indicators)
            if(i.getLabel() == label && i.isLight() == light) return true;

        return false;
    }

    public static boolean lastSerialIs(DigitType type){
        char c = Main.serial.charAt(Main.serial.length() - 1);
        return Character.isDigit(c) && ((int)c) % 2 == type.ordinal();   //based on ascii table
    }

}

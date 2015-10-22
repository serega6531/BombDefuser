package ru.serega6531.bombefuser;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.serega6531.bombefuser.enums.IndicatorLabel;
import ru.serega6531.bombefuser.enums.PortType;
import ru.serega6531.bombefuser.enums.WireColor;
import ru.serega6531.bombefuser.solvers.SimpleWiresSolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public void handleStartButtonAction(ActionEvent e) throws IOException {
        Button load = (Button) e.getTarget();
        Parent parent = load.getParent();
        TextField serialEdit = (TextField) parent.lookup("#SerialField");
        TextField batteriesEdit = (TextField) parent.lookup("#BatteriesField");
        TextField indicatorsEdit = (TextField) parent.lookup("#IndicatorsField");

        String serial = serialEdit.getText();
        String batteries = batteriesEdit.getText();
        String indicatorsstr = indicatorsEdit.getText();

        if(serial.isEmpty() || batteries.isEmpty()){
            return;
        }

        for(char ch : serial.toCharArray()){
            if(!Character.isLetterOrDigit(ch)){
                load.setText("Wrong serial");
                serialEdit.setText("");
                return;
            }
        }

        for(char ch : batteries.toCharArray()){
            if(!Character.isDigit(ch)){
                load.setText("Wrong batteries count");
                batteriesEdit.setText("");
                return;
            }
        }

        List<Indicator> indicators = new ArrayList<>();

        String[] bylight = indicatorsstr.split(",");
        if(!bylight[0].isEmpty()){
            String[] inds = bylight[0].split(" ");
            if(!inds[0].isEmpty()) {
                for (String ind : inds) {
                    indicators.add(new Indicator(IndicatorLabel.byName(ind), true));
                }
            }

            if(bylight.length > 1) {
                inds = bylight[1].split(" ");
                if (!inds[0].isEmpty()) {
                    for (String ind : inds) {
                        indicators.add(new Indicator(IndicatorLabel.byName(ind), false));
                    }
                }
            }
        }

        CheckBox dvi = (CheckBox) parent.lookup("#DVIBox");
        CheckBox parallel = (CheckBox) parent.lookup("#ParallelBox");
        CheckBox ps2 = (CheckBox) parent.lookup("#PS2Box");
        CheckBox rj45 = (CheckBox) parent.lookup("#RJ45Box");
        CheckBox serialbox = (CheckBox) parent.lookup("#SerialBox");
        CheckBox stereo = (CheckBox) parent.lookup("#StereoBox");

        List<PortType> ports = new ArrayList<>();

        if(dvi.isSelected())
            ports.add(PortType.DVI_I);

        if(parallel.isSelected())
            ports.add(PortType.PARALLEL);

        if(ps2.isSelected())
            ports.add(PortType.PS_2);

        if(rj45.isSelected())
            ports.add(PortType.RJ_45);

        if(serialbox.isSelected())
            ports.add(PortType.SERIAL);

        if(stereo.isSelected())
            ports.add(PortType.STEREO_RCA);

        Main.ports = ports;
        Main.batteries = Integer.parseInt(batteries);
        Main.serial = serial;
        Main.indicators = indicators;

        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("design/choose.fxml"));
        stage.setTitle("Simple wires");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();
        Main.opened = stage;
        Main.chooseStage = stage;
        Main.primaryStage.hide();
    }

    public void handleSimpleWiresButton(ActionEvent e) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("design/wire.fxml"));
        stage.setTitle("Simple wires");
        Scene scene = new Scene(root, 430, 120);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setMinWidth(400);
        stage.setMinHeight(150);
        stage.show();
        Main.chooseStage.hide();
        Main.opened = stage;
    }

    public void handleButtonButton(ActionEvent e) {

    }

    public void handleSimpleWiresSolveButton(ActionEvent e) {
        Button load = (Button) e.getTarget();
        Parent parent = load.getParent();
        TextField serialEdit = (TextField) parent.lookup("#WiresField");
        Label result = (Label) parent.lookup("#ResultLabel");

        if(serialEdit.getText().isEmpty())
            return;

        String[] colors = serialEdit.getText().split(" ");
        List<WireColor> wires = new ArrayList<>();

        for(String color : colors){
            WireColor wire = WireColor.byName(color);
            if(wire == null){
                result.setText("Wrong color name: " + color);
                result.setVisible(true);
                return;
            } else
                wires.add(wire);
        }

        if(wires.size() < 3 || wires.size() > 6){
            result.setText("Wires count must be in range [3,6], found " + wires.size());
            result.setVisible(true);
            return;
        }

        result.setText(new SimpleWiresSolver(wires.toArray(new WireColor[wires.size()])).solve());
        result.setVisible(true);
    }

}

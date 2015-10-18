package ru.serega6531.bombefuser;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import ru.serega6531.bombefuser.enums.IndicatorLabel;
import ru.serega6531.bombefuser.enums.PortType;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public void handleStartButtonAction(ActionEvent e) {
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

            inds = bylight[1].split(" ");
            if(!inds[0].isEmpty()) {
                for (String ind : inds) {
                    indicators.add(new Indicator(IndicatorLabel.byName(ind), false));
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
    }

    public void handleSimpleWiresButton(ActionEvent e) {

    }

    public void handleButtonButton(ActionEvent e) {

    }
}

package ru.serega6531.bombefuser.solvers;

import ru.serega6531.bombefuser.Main;
import ru.serega6531.bombefuser.enums.ButtonColor;
import ru.serega6531.bombefuser.enums.ButtonLabel;
import ru.serega6531.bombefuser.enums.ButtonStripeColor;
import ru.serega6531.bombefuser.enums.IndicatorLabel;

public class ButtonSolver implements Solver {

    private ButtonColor color;
    private ButtonLabel label;

    private ButtonStripeColor stripe;

    public ButtonSolver(ButtonColor color, ButtonLabel label) {
        this.color = color;
        this.label = label;
    }

    public void setStripeColor(ButtonStripeColor stripe){
        this.stripe = stripe;
    }

    @Override
    public String solve() {
        if(color == ButtonColor.BLUE && label == ButtonLabel.ABORT){
            return getReleaseString();
        } else if(Main.batteries > 1 && label == ButtonLabel.DETONATE){
            return "Press and immediately release the button";
        } else if(color == ButtonColor.WHITE && Main.hasIndicator(IndicatorLabel.CAR, true)){
            return getReleaseString();
        } else if(Main.batteries > 2 && Main.hasIndicator(IndicatorLabel.FRK, true)){
            return "Press and immediately release the button";
        } else if(color == ButtonColor.YELLOW){
            return getReleaseString();
        } else if(color == ButtonColor.RED && label == ButtonLabel.HOLD){
            return "Press and immediately release the button";
        } else {
            return getReleaseString();
        }
    }

    private String getReleaseString(){
        if(stripe != null) {
            switch (stripe) {
                case BLUE:
                    return "Stop holding when timer has 4 in any position";
                case OTHER: //same as white
                case WHITE:
                    return "Stop holding when timer has 1 in any position";
                case YELLOW:
                    return "Stop holding when timer has 5 in any position";
            }
        } else return "Hold button and input stripe color";

        return "Internal error";
    }

}

package ru.serega6531.bombefuser.solvers;

import ru.serega6531.bombefuser.Main;
import ru.serega6531.bombefuser.enums.DigitType;
import ru.serega6531.bombefuser.enums.WireColor;

public class SimpleWiresSolver implements Solver {

    private WireColor[] wires;

    public SimpleWiresSolver(WireColor[] wires) {
        this.wires = wires;
    }

    @Override
    public String solve() {
        switch (wires.length){
            case 3:
                if(noWires(WireColor.RED))
                    return "Cut the first fire";

                else if(wires[2] == WireColor.WHITE)
                    return "Cut the last wire";

                else if(hasAtLeastWires(WireColor.BLUE, 2))
                    return "Cut the last BLUE wire";

                return "Cut the last wire";
            case 4:
                if(hasAtLeastWires(WireColor.RED, 2) && Main.lastSerialIs(DigitType.ODD))
                    return "Cut the last RED wire";

                else if(wires[wires.length - 1] == WireColor.YELLOW && noWires(WireColor.RED))
                    return "Cut the first wire";

                else if(hasExactlyWires(WireColor.BLUE, 1))
                    return "Cut the first fire";

                else if(hasAtLeastWires(WireColor.YELLOW, 2))
                    return "Cut the last wire";

                else
                    return "Cut the second wire";
            case 5:
                if(wires[wires.length - 1] == WireColor.BLACK && Main.lastSerialIs(DigitType.ODD))
                    return "Cut the fourth wire";

                else if(hasExactlyWires(WireColor.RED, 1) && hasAtLeastWires(WireColor.YELLOW, 2))
                    return "Cut the first wire";

                else if(noWires(WireColor.BLACK))
                    return "Cut the second wire";

                else
                    return "Cut the last wire";
            case 6:
                if(noWires(WireColor.YELLOW) && Main.lastSerialIs(DigitType.ODD))
                    return "Cut the third wire";

                else if(hasExactlyWires(WireColor.YELLOW, 1) && hasAtLeastWires(WireColor.WHITE, 2))
                    return "Cut the fourth wire";

                else if(noWires(WireColor.RED))
                    return "Cut the last wire";

                else
                    return "Cut the fourth wire";
        }

        return "Internal error";
    }

    private boolean noWires(WireColor color){
        for(WireColor wire : wires)
            if(wire == color) return false;

        return true;
    }

    private boolean hasWire(WireColor color){
        return hasAtLeastWires(color, 1);
    }

    private boolean hasAtLeastWires(WireColor color, int need){
        int amount = 0;

        for(WireColor wire : wires) {
            if (wire == color){
                amount++;
            }

            if(amount == need)
                return true;
        }

        return false;
    }

    private boolean hasExactlyWires(WireColor color, int need){
        int amount = 0;

        for(WireColor wire : wires) {
            if (wire == color){
                amount++;
            }
        }

        return amount == need;
    }

}

package ru.serega6531.bombefuser.enums;

public enum IndicatorLabel {

    SND, CLR, CAR, IND, FRQ, SIG, NSA, MSA, TRN, BOB, FRK;

    public static IndicatorLabel byName(String name){
        for(IndicatorLabel label : values()){
            if(label.toString().equalsIgnoreCase(name))
                return label;
        }

        return null;
    }

}

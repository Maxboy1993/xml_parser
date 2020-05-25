package by.nareiko.xml.entity;

public enum  PeriodicalType {
    MONTHLY("MONTHLY"),
    WEEKLY("WEEKLY");

    private String periodicalType;

    PeriodicalType(String periodicalType){
        this.periodicalType = periodicalType;
    }

    public String getPeriodicalType() {
        return periodicalType;
    }

    public static PeriodicalType getPeriodicalTypeByValue(String value) {
        PeriodicalType[] periodicalTypes = PeriodicalType.values();
        for (PeriodicalType periodicalType : periodicalTypes) {
            if(periodicalType.getPeriodicalType().equals(value)){
                return periodicalType;
            }
        }
        throw new IllegalArgumentException();
    }
}

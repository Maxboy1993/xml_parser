package by.nareiko.xml.entity;

public enum PaperType {
    NEWSPAPER("newspaper"),
    MAGAZINE("magazine"),
    BOOKLET("booklet");

    private String paperType;

    PaperType(String paperType){
        this.paperType = paperType;
    }

    public String getPaperType() {
        return paperType;
    }

    public static PaperType getPaperTypeTypeByValue(String value) {
        PaperType[] paperTypes = PaperType.values();
        for (PaperType paperType : paperTypes) {
            if(paperType.getPaperType().equals(value)){
                return paperType;
            }
        }
        throw new IllegalArgumentException();
    }
}

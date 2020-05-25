package by.nareiko.xml.parser;

import java.util.Arrays;
import java.util.Optional;

public enum  PaperGeneralType {
    PAPERS("papers"),
    ID("id"),
    TITLE("title"),
    PAPER("paper"),
    PAPER_TYPE("paper_type"),
    PERIODOCAL_TYPE("periodical"),
    SUBSCRIPTION("subscription"),
    PUBLISHING_DATE("publishing_date"),
    PAPER_CHARACTERISTICS("paper_characteristics"),
    IS_COLOUR("is_colour"),
    VOLUME("volume"),
    IS_GLOSSY("is_glossy");

    private String type;

    PaperGeneralType(String tag){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Optional getTypeByValue(String value){
        PaperGeneralType[] paperGeneralTypes = PaperGeneralType.values();
        Optional<PaperGeneralType> paperGeneralType
                = Arrays.stream(paperGeneralTypes).filter(o -> o.getType().equals(value)).findAny();
        return paperGeneralType;
    }
}

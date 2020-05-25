package by.nareiko.xml.parser.dom;

import by.nareiko.xml.entity.Paper;
import by.nareiko.xml.entity.PaperType;
import by.nareiko.xml.entity.PeriodicalType;
import by.nareiko.xml.parser.PaperGeneralType;
import by.nareiko.xml.parser.sax.PaperHandler;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.text.ParseException;
import java.util.ArrayList;

public class Analyzer {

    public static ArrayList<Paper> listBuilder(Element root) {
        ArrayList<Paper> papers  = new ArrayList<>();
        NodeList paperNodes = root.getElementsByTagName("paper");
        Paper paper = null;
        for (int i = 0; i < paperNodes.getLength(); i++) {
            paper = new Paper();
            Element paperElement = (Element) paperNodes.item(i);
            paper.setId(Long.parseLong(paperElement.getAttribute("id")));
            paper.setTitle(paperElement.getAttribute("title"));
            paper.setPaperType(PaperType.valueOf(getBabyValue(paperElement, PaperGeneralType.PAPER_TYPE.getType())));
            paper.setPeriodical(PeriodicalType.valueOf(getBabyValue(paperElement, PaperGeneralType.PERIODOCAL_TYPE.getType())));
            paper.setSubscription(Boolean.parseBoolean(getBabyValue(paperElement, PaperGeneralType.SUBSCRIPTION.getType())));
            try {
                paper.getPublishingDate().setPublishingDate(PaperHandler.dateFormat.parse(getBabyValue(paperElement, "publishing_date")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Paper.PaperCharacteristics characteristics = paper.getPaperCharacteristics();
            Element characteristicElement =
                    getBaby(paperElement, PaperGeneralType.PAPER_CHARACTERISTICS.getType());
            characteristics.setColour(Boolean.getBoolean(getBabyValue(characteristicElement, PaperGeneralType.IS_COLOUR.getType())));
            characteristics.setVolume(Integer.parseInt(getBabyValue(characteristicElement, PaperGeneralType.VOLUME.getType())));
            characteristics.setColour(Boolean.getBoolean(getBabyValue(characteristicElement, PaperGeneralType.IS_GLOSSY.getType())));
            papers.add(paper);
        }
        return papers;
    }

    private static Element getBaby(Element parent, String childName) {
        NodeList nlist = parent.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }

    private static String getBabyValue(Element parent, String childName) {
        Element child = getBaby(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }


}

package by.nareiko.xml.parser.sax;

import by.nareiko.xml.entity.Paper;
import by.nareiko.xml.entity.PaperType;
import by.nareiko.xml.entity.PeriodicalType;
import by.nareiko.xml.entity.PublishingDate;
import by.nareiko.xml.parser.PaperGeneralType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class PaperHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger(PaperHandler.class);
    private Set<Paper> papers;
    private Paper current;
    private PaperGeneralType currentType;
    private static final int ID_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final String DATE_FORMAT = "yyyy-mm-dd";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public PaperHandler(){
        papers = new HashSet<>();
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if(PaperGeneralType.PAPER.getType().equals(localName)){
            current = new Paper();
            current.setId(Long.parseLong(attributes.getValue(ID_INDEX)));
            currentType = (PaperGeneralType) PaperGeneralType.getTypeByValue(localName).get();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if(PaperGeneralType.PAPER.getType().equals(localName)){
            papers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch,start,length).trim();
        if(currentType != null){
            switch (currentType){
                case PAPERS:
                    break;
                case PAPER_TYPE:
                    current.setPaperType(PaperType.getPaperTypeTypeByValue(value));
                    break;
                case PERIODOCAL_TYPE:
                    current.setPeriodical(PeriodicalType.getPeriodicalTypeByValue(value));
                    break;
                case SUBSCRIPTION:
                    current.setSubscription(Boolean.parseBoolean(value));
                    break;
                case PUBLISHING_DATE:current.setPublishingDate(new PublishingDate());
                    try {
                        current.getPublishingDate().setPublishingDate((dateFormat.parse(value)));
                    } catch (ParseException e) {
                        logger.error(e);
                    }
                    break;
                case PAPER_CHARACTERISTICS: current.setPaperCharacteristics(new Paper.PaperCharacteristics());
                    break;
                case IS_COLOUR:
                    current.getPaperCharacteristics().setColour(Boolean.parseBoolean(value));
                    break;
                case VOLUME:
                    current.getPaperCharacteristics().setVolume(Integer.parseInt(value));
                    break;
                case IS_GLOSSY:
                    current.getPaperCharacteristics().setGlossy(Boolean.parseBoolean(value));
                    break;
                default:
                    throw new SAXException("Unexpected type");
            }
            currentType = null;
        }

    }
}

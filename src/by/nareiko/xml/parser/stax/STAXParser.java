package by.nareiko.xml.parser.stax;

import by.nareiko.xml.entity.Paper;
import by.nareiko.xml.entity.PaperType;
import by.nareiko.xml.entity.PeriodicalType;
import by.nareiko.xml.entity.PublishingDate;
import by.nareiko.xml.parser.PaperGeneralType;
import by.nareiko.xml.parser.sax.PaperHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class STAXParser {
    static final Logger logger = LogManager.getLogger(STAXParser.class);
    private Set<Paper> papers = new HashSet<>();
    private XMLInputFactory inputFactory;


    public STAXParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void createBanksSet(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {

                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    switch (reader.getLocalName()) {
                        case "bank":
                            papers.add(createPaper(reader, new Paper()));
                            break;
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("StAX parsing error! " + e);
        } catch (FileNotFoundException e) {
            logger.error("File " + fileName + " not found! " + e);
        } catch (IOException e) {
            logger.error("Error while closing InputStream" + e);
        }
    }

    protected Paper createPaper(XMLStreamReader reader, Paper paper) throws XMLStreamException {

        paper.setId(Long.parseLong(reader.getAttributeValue(null, PaperGeneralType.ID.getType())));
        paper.setTitle(reader.getAttributeValue(null, PaperGeneralType.TITLE.getType()));
        while (reader.hasNext()) {
            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    switch (PaperGeneralType.valueOf(name)) {
                        case PAPER_TYPE:
                            paper.setPaperType(PaperType.getPaperTypeTypeByValue(getXMLText(reader)));
                            break;
                        case PERIODOCAL_TYPE:
                            paper.setPeriodical(PeriodicalType.getPeriodicalTypeByValue(getXMLText(reader)));
                            break;
                        case SUBSCRIPTION:
                            paper.setSubscription(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case PUBLISHING_DATE:
                            paper.setPublishingDate(new PublishingDate());
                            try {
                                paper.getPublishingDate().setPublishingDate((PaperHandler.dateFormat.parse(getXMLText(reader))));
                            } catch (ParseException e) {
                                logger.error(e);
                            }
                            break;
                        case PAPER_CHARACTERISTICS:
                            paper.setPaperCharacteristics(new Paper.PaperCharacteristics());
                            break;
                        case IS_COLOUR:
                            paper.getPaperCharacteristics().setColour(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case VOLUME:
                            paper.getPaperCharacteristics().setVolume(Integer.parseInt(getXMLText(reader)));
                            break;
                        case IS_GLOSSY:
                            paper.getPaperCharacteristics().setGlossy(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                name = reader.getLocalName();
                if (PaperGeneralType.valueOf(reader.getLocalName()) == PaperGeneralType.PAPER) {
                    return paper;
                }
            }
        }
        throw new XMLStreamException("Unknown element");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

package by.nareiko.xml.parser.sax;

import by.nareiko.xml.entity.Paper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SAXParser {
    private Set<Paper> papers;
    private PaperHandler handler;
    private XMLReader reader;
    private static final Logger logger = LogManager.getLogger(SAXParser.class);

    public SAXParser(){
        handler = new PaperHandler();
        papers = new HashSet<>();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            logger.error(e);
        }
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void buildPapers(String file){
        try {
            File xmlFile = new File(String.valueOf(getClass().getClassLoader().getResource(file).getPath()));
            reader.parse(new InputSource(String.valueOf(xmlFile)));
            papers.addAll(handler.getPapers());
        } catch (IOException | SAXException e) {
            logger.error(e);
        }
    }
}

package by.nareiko.xml.parser.dom;

import by.nareiko.xml.entity.Paper;
import by.nareiko.xml.parser.PaperGeneralType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DOMParser {
    private  static final Logger logger = LogManager.getLogger(DOMParser.class);
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;

    public DOMParser() {
        DocumentBuilderFactory.newInstance();
        try {
            this.db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Configuration mistake" + e);        }
    }

    public void parse(String file){
        try {
            Document document = db.parse(file);
            Element root = document.getDocumentElement();
            ArrayList<Paper> students = Analyzer.listBuilder(root);
        } catch (SAXException e) {
            logger.error("Sax parser mistake" + e);
        } catch (IOException e) {
            logger.error("IO mistake" + e);
        }
    }
}




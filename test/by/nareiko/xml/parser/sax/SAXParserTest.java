package by.nareiko.xml.parser.sax;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SAXParserTest {
    private static SAXParser parser;

    @BeforeClass
    public static void initBuilder() {
        parser = new SAXParser();
        parser.buildPapers("resources/data/papers.xml");
    }


    @Test
    public void testGetPapers() {

    }

    @Test
    public void testBuildPapers() {
        Assert.assertEquals(9, parser.getPapers().size());
    }
}
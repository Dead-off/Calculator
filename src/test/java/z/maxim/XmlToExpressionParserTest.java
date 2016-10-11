package z.maxim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import z.maxim.operations.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlToExpressionParserTest extends Assert {
    private final static String DIRECTORY = "src\\test\\resources\\";

    private final static String CORRECT_XSD = DIRECTORY + "Calculator.xsd";
    private final static String INCORRECT_XSD = DIRECTORY + "CalculatorIncorrect.xsd";

    private final static String INCORRECT_XML = DIRECTORY + "sampleIncorrectXML.xml";
    private final static String INCORRECT_XML_FOR_XSD = DIRECTORY + "sampleIncorrectForXSD.xml";
    private final static String INCORRECT_XML_FOR_XSD_SECOND = DIRECTORY + "sampleIncorrectForXSDSec.xml";

    private final static String SAMPLE_CORRECT = DIRECTORY + "sampleCorrect.xml";
    private final static String SAMPLE_CORRECT_SECOND = DIRECTORY + "sampleCorrectSec.xml";

    private XmlToExpressionParser xmlToTreeParser;

    @Before
    public void init() {
        xmlToTreeParser = new XmlToExpressionParser();
    }

    @Test(expected = SAXException.class)
    public void testIncorrectXsd() throws ParserConfigurationException, SAXException, IOException {
        xmlToTreeParser.parseFile(new File(INCORRECT_XSD), null);
    }

    @Test(expected = SAXException.class)
    public void parseFileTestIncorrectXML() throws ParserConfigurationException, SAXException, IOException {
        xmlToTreeParser.parseFile(new File(CORRECT_XSD), new File(INCORRECT_XML));
    }

    @Test(expected = SAXException.class)
    public void parseFileTestIncorrectXMLForSchema() throws ParserConfigurationException, SAXException, IOException {
        xmlToTreeParser.parseFile(new File(CORRECT_XSD), new File(INCORRECT_XML_FOR_XSD));
    }

    @Test(expected = SAXException.class)
    public void parseFileTestIncorrectXMLForSchemaSecond() throws ParserConfigurationException, SAXException, IOException {
        xmlToTreeParser.parseFile(new File(CORRECT_XSD), new File(INCORRECT_XML_FOR_XSD_SECOND));
    }

    @Test
    public void parseFileTest() throws ParserConfigurationException, SAXException, IOException {
        List<Expression> sampleFirst = xmlToTreeParser.parseFile(new File(CORRECT_XSD), new File(SAMPLE_CORRECT));
        List<Expression> sampleSecond = xmlToTreeParser.parseFile(new File(CORRECT_XSD), new File(SAMPLE_CORRECT_SECOND));

        List<Expression> firstFile = new ArrayList<>();
        Expression firstExpressionInFirstFile = new Mul(new Sum(new NumberExpression(5), new NumberExpression(1)), new Sub(new NumberExpression(67), new NumberExpression(4)));
        Expression secondExpressionInFirstFile = new Mul(new Sub(new NumberExpression(12), new NumberExpression(55)), new Sum(new NumberExpression(10), new NumberExpression(22)));
        Expression firstExpressionInSecondFile = new Div(new NumberExpression(10), new NumberExpression(0));

        firstFile.add(firstExpressionInFirstFile);
        firstFile.add(secondExpressionInFirstFile);
        List<Expression> secondFile = new ArrayList<>();
        secondFile.add(firstExpressionInSecondFile);
        assertEquals(sampleFirst, firstFile);
        assertEquals(sampleSecond, secondFile);
    }
}

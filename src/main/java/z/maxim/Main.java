package z.maxim;

import org.xml.sax.SAXException;
import z.maxim.operations.Expression;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("you must get in first arg path to directory with Calculator.xsd and sampleCorrect.xml files");
            return;
        }
        String resourceDirectory = args[0];
        String xsdFile = resourceDirectory + "\\Calculator.xsd";
        String xmlFile = resourceDirectory + "\\sampleCorrect.xml";
        String resultFile = resourceDirectory + "\\result.xml";

        Calculator calculator = new Calculator();
        XmlToExpressionParser xmlToExpressionParser = new XmlToExpressionParser();
        XmlResultWriter resultWriter;
        try {
            resultWriter = new XmlResultWriter(new FileOutputStream(resultFile));
        } catch (IOException e) {
            System.out.println("don't find file for write result");
            return;
        }

        List<Double> result;
        List<Expression> exceptionsFromFile;
        try {
            exceptionsFromFile = xmlToExpressionParser.parseFile(new File(xsdFile), new File(xmlFile));
        } catch (IOException e) {
            System.out.println("Error in access to file. Maybe file doesn't exist");
            return;
        } catch (ParserConfigurationException | SAXException e) {
            System.out.println("Parse file error. File must be valid to xsd schema");
            return;
        }
        result = calculator.calculate(exceptionsFromFile);
        try {
            resultWriter.writeResult(result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

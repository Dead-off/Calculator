package z.maxim;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.util.List;

public class XmlResultWriter {

    private final static String ROOT_NODE_NAME = "simpleCalculator";
    private final static String EXPRESSIONS_NODE_NAME = "expressionResults";
    private final static String EXPRESSION_NODE_NAME = "expressionResult";
    private final static String RESULT_NODE_NAME = "result";

    private final OutputStream resultStream;

    public XmlResultWriter(OutputStream resultStream) {
        this.resultStream = resultStream;
    }

    public void writeResult(List<Double> results)
            throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        doc.setXmlStandalone(true);
        Element simpleCalculator = doc.createElement(ROOT_NODE_NAME);
        doc.appendChild(simpleCalculator);
        Element expressionResults = doc.createElement(EXPRESSIONS_NODE_NAME);
        simpleCalculator.appendChild(expressionResults);
        for (Double value : results) {
            expressionResults.appendChild(getExpressionResult(value, doc));
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(resultStream);
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(source, streamResult);
    }

    private Element getExpressionResult(double value, Document doc) {
        Element result = doc.createElement(EXPRESSION_NODE_NAME);
        Element resValue = doc.createElement(RESULT_NODE_NAME);
        resValue.appendChild(doc.createTextNode("" + value));
        result.appendChild(resValue);
        return result;
    }
}

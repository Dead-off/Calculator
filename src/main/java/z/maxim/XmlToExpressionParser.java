package z.maxim;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import z.maxim.operations.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlToExpressionParser {
    private final static String typeOperation = "operation";
    private final static String typeArgument = "arg";

    private final static String nameOfOperationTypeAttribute = "OperationType";
    private final static String sumOperation = "SUM";
    private final static String subOperation = "SUB";
    private final static String mulOperation = "MUL";
    private final static String divOperation = "DIV";

    private List<Expression> getExpressions(Document doc) {
        List<Expression> result = new ArrayList<>();
        Node root = doc.getChildNodes().item(0);//simpleCalculator
        Node expressions = getChildWithType(root, Node.ELEMENT_NODE).get(0);
        List<Node> expressionsList = getChildWithType(expressions, Node.ELEMENT_NODE);//list of expression tags

        for (Node expressionNode : expressionsList) {
            Node firstOperation = getChildWithType(expressionNode, Node.ELEMENT_NODE).get(0);//firstNode - operation in expression
            result.add(getTreeExpression(firstOperation));
        }

        return result;
    }

    private Expression getTreeExpression(Node expressionNode) {

        String expressionType = expressionNode.getNodeName();
        if (expressionType.equalsIgnoreCase(typeOperation)) {
            String operationType = getOperationType(expressionNode);
            List<Node> childNodes = getChildWithType(expressionNode, Node.ELEMENT_NODE);
            Expression firstArg = getTreeExpression(childNodes.get(0));
            Expression secondArg = getTreeExpression(childNodes.get(1));
            Expression expression;
            if (operationType.equalsIgnoreCase(subOperation)) {
                expression = new Sub(firstArg, secondArg);
            } else if (operationType.equalsIgnoreCase(sumOperation)) {
                expression = new Sum(firstArg, secondArg);
            } else if (operationType.equalsIgnoreCase(mulOperation)) {
                expression = new Mul(firstArg, secondArg);
            } else {
                expression = new Div(firstArg, secondArg);
            }
            return expression;
        } else {
            return new NumberExpression(getArgumentValue(expressionNode));
        }
    }

    private long getArgumentValue(Node argumentNode) {
        return Long.parseLong(argumentNode.getChildNodes().item(0).getTextContent());
    }

    private String getOperationType(Node operationNode) {
        return operationNode.getAttributes().getNamedItem(nameOfOperationTypeAttribute).getNodeValue();
    }

    public List<Expression> parseFile(File xsdFile, File xmlFile) throws ParserConfigurationException, SAXException, IOException {

        SchemaFactory factory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(xsdFile);
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xmlFile));

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        return getExpressions(doc);
    }

    private List<Node> getChildWithType(Node node, short type) {
        List<Node> result = new ArrayList<>();
        NodeList childs = node.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            Node currentNode = childs.item(i);
            if (currentNode.getNodeType() == type) {
                result.add(currentNode);
            }
        }

        return result;
    }
}

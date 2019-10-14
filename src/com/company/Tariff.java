package com.company;

import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashSet;
import java.util.Set;

/* TariffDOMBuilder */
public class Tariff {

    private ArrayList<Tariffs> tariff;
    private XMLReader reader;
    private DocumentBuilder docBuilder;

    public Tariff() {
        this.tariff = new ArrayList<Tariffs>();
        //создание DOM анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public ArrayList<Tariffs> getTariff() {
        return tariff;
    }

    public void tariffBuilder(String fileName) {
        Document doc = null;
        try {
            // parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            // получение списка дочерних элементов <Tariffs>
            NodeList tariffList = root.getElementsByTagName("tatiff_name");
            for (int i = 0; i < tariffList.getLength(); i++) {
                Element tariffElement = (Element) tariffList.item(i);
                Tariffs tariffs = buildTariff(tariffElement);
                tariff.add(tariffs);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private Tariffs buildTariff(Element tarifElement) {
        Tariffs tariffs = new Tariffs();

        // заполнение объекта tariff
        tariffs.setTatiff_name(tarifElement.getAttribute("name"));
        tariffs.setOperator(getElementTextContent(tarifElement,"operator"));
        tariffs.setPayroll(getElementTextContent(tarifElement,"payroll"));
        tariffs.setSmsPrices(getElementTextContent(tarifElement,"sms_price"));

        // заполнение объекта Цен на звонки
        Element callPrices = (Element) tarifElement.getElementsByTagName("call_prices").item(0);
        tariffs.getCallPrices().setFixedPhone(getElementTextContent(callPrices,"fixedPhone"));
        tariffs.getCallPrices().setWithinNetwork(getElementTextContent(callPrices,"withinNetwork"));
        tariffs.getCallPrices().setOutsideNetwork(getElementTextContent(callPrices,"outsideNetwork"));

        // заполнение объекта Параметры
        Element  parameters = (Element) tarifElement.getElementsByTagName("parameters").item(0);
        tariffs.getParameters().setFavoriteNumber(getElementTextContent(parameters,"favoriteNumber"));
        tariffs.getParameters().setPaymentConnection(getElementTextContent(parameters,"paymentConnection"));
        tariffs.getParameters().setTariffication(getElementTextContent(parameters,"tariffication"));

        return tariffs;
    }


    private static String getElementTextContent(Element element, String elementName){
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

package com.company.app.services;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public interface XmlFileDataLoader<T> extends DataLoader<T> {

    default public Document getXmlDocument(String fileName) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(xmlFile);
    }

    default public List<T> processNodes(NodeList children, Function<Node,T> nodeProcessor){
        List<T> dataList = new ArrayList<>();
        for(int i=0;i<children.getLength();i++){
            Node node = children.item(i);
            T entity  = nodeProcessor.apply(node);
            dataList.add(entity);
        }

        return dataList;
    }

}

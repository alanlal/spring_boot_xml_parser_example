package com.company.app.services;

import com.company.app.models.Address;
import com.company.app.models.Salary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalaryLoader implements XmlFileDataLoader<Salary>{

    @Value("${userdata.salary.filepath}")
    String filePath;

    public SalaryLoader() {
    }

    @Override
    public List<Salary> loadData() {
        try{
            Document doc = getXmlDocument(filePath);
            NodeList salaries = doc.getElementsByTagName("person");
            return processNodes(salaries,this::process);
        } catch(Exception e){
            System.out.println(e);
        }

        return null;
    }

    public Salary process(Node node){
        Salary salary = new Salary();

        if(node.getNodeType()==Node.ELEMENT_NODE){
            Element personElement = (Element) node;
            salary.setName(personElement.getAttribute("name"));

            salary.setSalary(personElement.getElementsByTagName("salary").item(0).getTextContent());
            salary.setPension(personElement.getElementsByTagName("pension").item(0).getTextContent());
        }

        return salary;
    }
}

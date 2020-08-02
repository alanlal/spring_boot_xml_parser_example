package com.company.app.services;

import com.company.app.models.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class AddressLoader implements XmlFileDataLoader<Address> {

    @Value("${userdata.address.filepath}")
    String filePath;

    public AddressLoader(){

    }

    @Override
    public List<Address> loadData() {
        try{
            Document doc = getXmlDocument(filePath);
            NodeList persons = doc.getElementsByTagName("person");
            return processNodes(persons,this::process);
        }catch(Exception e){
            System.out.println(e);
        }

        return null;
    }

    public Address process(Node node){
        Address address = new Address();

        if(node.getNodeType()==Node.ELEMENT_NODE){
            Element personElement = (Element) node;
            address.setName(personElement.getAttribute("name"));

            address.setAddress(personElement.getElementsByTagName("address").item(0).getTextContent());
            address.setPhoneNumber(personElement.getElementsByTagName("phonenumber").item(0).getTextContent());
        }

        return address;
    }

}

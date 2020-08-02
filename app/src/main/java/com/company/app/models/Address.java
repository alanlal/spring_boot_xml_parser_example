package com.company.app.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

//<geodata>
//    <person name="tom">
//        <address>2344 States Drive, MA 01213, USA</address>
//        <phonenumber>333-222-2222</phonenumber>
//    </person>
//    <person name="mat">
//        <address>2345 Gates Drive, PA 11213, USA</address>
//        <phonenumber>444-222-2222</phonenumber>
//    </person>
//</geodata>



public class Address extends AbstractUserEntity implements Serializable {

    private String address;
    private String phoneNumber;

    public Address() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

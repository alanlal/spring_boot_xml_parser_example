package com.company.app.services;

import com.company.app.models.AbstractUserEntity;
import com.company.app.models.Address;
import com.company.app.models.Salary;
import com.company.app.models.User;
import com.company.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//<persondata>
//    <person name="tom">
//        <address>2344 States Drive, MA 01213, USA</address>
//        <phonenumber>333-222-2222</phonenumber>
//        <salary>$3500</salary>
//        <pension>$700</pension>
//    </person>
//    <person name="mat">
//        <address>2345 Gates Drive, PA 11213, USA</address>
//        <phonenumber>444-222-2222</phonenumber>
//        <salary>$3000</salary>
//        <pension>$500</pension>
//    </person>
//</persondata>

@Service
public class PersonDataLoader implements DataLoader<User>{

    @Autowired
    AddressLoader addressLoader;

    @Autowired
    SalaryLoader salaryLoader;

    @Autowired
    UserRepository userRepository;

    public PersonDataLoader() {
    }

    public PersonDataLoader(AddressLoader addressLoader, UserRepository userRepository) {
        this.addressLoader = addressLoader;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> loadData() {
        List<Address> addressData =  addressLoader.loadData();
        List<Salary> salaryData  =  salaryLoader.loadData();

        Map<String,List<Address>> addressMap = addressData.stream().collect(Collectors.groupingBy(AbstractUserEntity::getName));
        Map<String,List<Salary>> salaryMap = salaryData.stream().collect(Collectors.groupingBy(AbstractUserEntity::getName));

        List<User> usersList = addressMap.keySet().stream().map(key->{
            User user = new User();
            user.setName(key);
            user.setAddress(addressMap.get(key).get(0).getAddress());
            user.setPhoneNumber(addressMap.get(key).get(0).getPhoneNumber());
            user.setPension(salaryMap.get(key).get(0).getSalary());
            user.setSalary(salaryMap.get(key).get(0).getPension());

            userRepository.save(user);
            return user;
        }).collect(Collectors.toList());

        return  usersList;

    }

    public AddressLoader getAddressLoader() {
        return addressLoader;
    }

    public void setAddressLoader(AddressLoader addressLoader) {
        this.addressLoader = addressLoader;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}

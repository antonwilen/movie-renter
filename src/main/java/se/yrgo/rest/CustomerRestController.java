package se.yrgo.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.yrgo.data.CustomerRepository;
import se.yrgo.domain.Customer;

@RestController
public class CustomerRestController {

    @RequestMapping("/customer")
    public CustomerList allCustomer(){
        List<Customer> all = data.findAll();
        return new CustomerList(all);
    }

    @Autowired
    private CustomerRepository data;

    @RequestMapping(value="/customer", method=RequestMethod.POST)
    public ResponseEntity createNewCustomer(@RequestBody Customer customer) {
        data.save(customer);
        return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
    }
}

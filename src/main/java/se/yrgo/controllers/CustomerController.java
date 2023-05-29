package se.yrgo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.yrgo.data.CustomerRepository;
import se.yrgo.domain.Customer;


@Controller
@RequestMapping("/website/customers")
public class CustomerController {
	@Autowired
	private CustomerRepository data;

      @RequestMapping(value="/newCustomer.html",method=RequestMethod.POST)
      public String newCustomer(Customer customer) {
		data.save(customer);
		return "redirect:/website/customers/list.html";
	}
	
	@RequestMapping(value="/newCustomer.html",method=RequestMethod.GET)
	public ModelAndView renderNewCustomerForm(){
		Customer newCustomer = new Customer();
		return new ModelAndView("newCustomer", "form", newCustomer);
	} 
	
	@RequestMapping(value="/list.html", method=RequestMethod.GET)	
	public ModelAndView customers(){
		List<Customer> allCustomers = data.findAll();
		return new ModelAndView("allCustomers" , "customers", allCustomers);
	}
	  
	@RequestMapping(value="/customer/{name}")
	public ModelAndView showCustomerByName(@PathVariable("name") String name) {
		Customer customer = data.findByName(name);
        return new ModelAndView("CustomerInfo", name, customer);
	}	

}

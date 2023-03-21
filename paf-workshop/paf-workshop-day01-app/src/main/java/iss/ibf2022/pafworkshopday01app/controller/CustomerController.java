package iss.ibf2022.pafworkshopday01app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.ibf2022.pafworkshopday01app.model.Customer;
import iss.ibf2022.pafworkshopday01app.model.Order;
import iss.ibf2022.pafworkshopday01app.service.CustomerApiService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path={"", "/"})
public class CustomerController {

    @Autowired
    CustomerApiService custApiSvc;

    @GetMapping
    public String getHomePage() {

        return "home";
    }

    @GetMapping(path={"/customers"})
    public String getCustomerList(@RequestParam MultiValueMap<String, String> form, Model model, HttpSession session) {

        String limit = "";
        String offset = "";

        String sessionLimit = (String) session.getAttribute("sessionLimit");
        String sessionOffset = (String) session.getAttribute("sessionOffset");

        // System.out.println(">>> session limit : " + sessionLimit);

        // set to default value if either limit or offset is not provided
        if (null == form.getFirst("limit") || form.getFirst("limit").isEmpty()) {
            if (null == sessionLimit) {
                limit = String.valueOf(5);
                session.setAttribute("sessionLimit", limit);
            }
        } else {
            limit = form.getFirst("limit");
            session.setAttribute("sessionLimit", limit);
        }
        

        if (null == form.getFirst("offset") || form.getFirst("offset").isEmpty()) {
            if (null == sessionOffset) {
                offset = String.valueOf(0);
                session.setAttribute("sessionOffset", offset);
            }
        } else {
            offset = form.getFirst("offset");
            session.setAttribute("sessionOffset", offset);
        }

        try {
            List<Customer> customerList = custApiSvc.getCustomerList((String) session.getAttribute("sessionLimit"), (String) session.getAttribute("sessionOffset"));
            model.addAttribute("customerList", customerList);

        } catch (Exception ex) {

            String error = ex.getMessage();
            model.addAttribute("customerList", new LinkedList<Customer>());
            model.addAttribute("error", error);

        }

        return "customerlist";
    }

    @GetMapping(path={"/customers/{id}"})
    public String retrieveCustomerDetails(@PathVariable Integer id, Model model, HttpSession session) {

        try {
            Customer customer = custApiSvc.getCustomerById(id);
            model.addAttribute("customer", customer);

            return "customerdetails";

        } catch (Exception ex) {

            String error = ex.getMessage();
            model.addAttribute("customer", new Customer());
            model.addAttribute("error", error);

            return "customerdetails";
        }
        
    }

    @GetMapping(path={"customers/{id}/orders"})
    public String retrieveOrderByCustomerId(@PathVariable Integer id, Model model) {

        Customer customer = custApiSvc.getCustomerById(id);

        try {
            List<Order> orderList = custApiSvc.getOrderByCustomerId(id);
            model.addAttribute("customer", customer);
            model.addAttribute("orderList", orderList);

            return "orderdetails";

        } catch (Exception ex) {

            String error = ex.getMessage();
            model.addAttribute("customer", customer);
            model.addAttribute("error", error);
            model.addAttribute("orderList", new LinkedList<Order>());

            return "orderdetails";
        }
    }

    @GetMapping(path={"/invalidate"})
    public String invalidateSession(HttpSession session) {

        session.invalidate();

        return "home";

    }
    
}

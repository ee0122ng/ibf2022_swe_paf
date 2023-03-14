package iss.paf.pafday03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iss.paf.pafday03.payload.SearchRequest;
import iss.paf.pafday03.service.OrderRestService;

@Controller
@RequestMapping(path={"/orders"})
public class OrderController {

    @Autowired
    OrderRestService orderRestSvc;
    
    @GetMapping()
    public String searchOrder(Model model) {

        SearchRequest sr = new SearchRequest();

        model.addAttribute("searchObject", sr);

        return "searchorder";
        
    }
}

package iss.ibf2022.pafworkshopday03app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.ibf2022.pafworkshopday03app.model.Order;
import iss.ibf2022.pafworkshopday03app.payload.RequestPayload;
import iss.ibf2022.pafworkshopday03app.service.OrderApiService;

@Controller
@RequestMapping(path={""})
public class OrderController {

    @Autowired
    OrderApiService orderApiSvc;

    @GetMapping(path={"/order"})
    public String getMainPage() {

        return "retrieveorder";
    }

    @GetMapping(path={"/order/total"})
    public String getOrderById(@RequestParam(name="orderId", required=true) String orderId, Model model) {

        RequestPayload payload = orderApiSvc.getOrderById(orderId);

         if (payload.getStatus() == 200) {
            List<Order> orderList = orderApiSvc.getObjectFromPayload(payload);
            model.addAttribute("status", payload.getStatus());
            model.addAttribute("orderId", orderId);
            model.addAttribute("orderList", orderList);
         } else {
            model.addAttribute("orderId", orderId);
            model.addAttribute("orderList", new LinkedList<Order>());
            model.addAttribute("status", payload.getStatus());
            model.addAttribute("error", payload.getBody());
         }

        return "displayorder";

    }
    
}

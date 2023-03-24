package iss.ibf2022.pafworkshopday04.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import iss.ibf2022.pafworkshopday04.exception.MissingDetailsException;
import iss.ibf2022.pafworkshopday04.model.Order;
import iss.ibf2022.pafworkshopday04.model.OrderDetails;
import iss.ibf2022.pafworkshopday04.payload.RequestPayload;
import iss.ibf2022.pafworkshopday04.repository.OrderRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Transactional
    public RequestPayload insertNewOrder(MultiValueMap<String, String> form) {

        // check if new order id created
        // if yes, check if order details is updated
        // if yes, return number of updates and key generated for order details
        Boolean createdOrder = false;
        Boolean updatedOrderDetails = false;

        // 1. create a new order, then update if order is created
        Order order = new Order();
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setCustomerName(form.getFirst("customerName"));
        order.setShipAddress(form.getFirst("shipAddress"));
        order.setTax(Float.valueOf(form.getFirst("tax")));
        order.setNotes(form.getFirst("notes"));
        try {
            Integer orderId = orderRepo.insertNewOrder(order);
            createdOrder = orderId > 0 ? true : false;

            // 2. batch update the order details, then update if successful
            Integer updated = 0;
            List<Integer> returnedIdList = new LinkedList<>();

            if (createdOrder) {
                order.setOrderId(orderId);

                // update the order details based on the generated order id
                try {
                    List<String> productList = form.get("product");
                    List<Float> unitPriceList = form.get("unitPrice").stream().map(i -> Float.valueOf(i)).toList();
                    List<Float> discountList = form.get("discount").stream().map(i -> Float.valueOf(i)).toList();
                    List<Integer> quantityList = form.get("quantity").stream().map(i -> Integer.valueOf(i)).toList();

                    // create a list of orderdetails based on the form information
                    List<OrderDetails> orderDetailList = new LinkedList<OrderDetails>();
                    for (int i = 0; i < productList.size(); i++) {
                        OrderDetails orderDetails = new OrderDetails();
                        orderDetails.setOrderId(orderId);
                        orderDetails.setProduct(productList.get(i));
                        orderDetails.setQuantity(quantityList.get(i));
                        orderDetails.setUnitPrice(unitPriceList.get(i));
                        orderDetails.setDiscount(discountList.get(i));
                        orderDetailList.add(orderDetails);
                    }
                    // tag the order details to the order
                    order.setDetails(orderDetailList);

                    // generate list of order details id
                    returnedIdList = orderRepo.insertOrderDetailsList(orderDetailList);
                    updatedOrderDetails = returnedIdList.size() > 0 ? true : false;

                    Integer idx = 0;
                    for (Integer id : returnedIdList) {
                        orderDetailList.get(idx).setId(id);
                        idx += 1;
                    }

                    updated = returnedIdList.size();

                } catch (Exception ex) {
                    throw new MissingDetailsException("Incomplete fields for order details");

                }
            }

            RequestPayload payload = new RequestPayload(createdOrder && updatedOrderDetails, orderId, updated, returnedIdList);

            return payload;

        } catch (Exception ex) {
            String error = ex.getLocalizedMessage().trim().split(";")[1];
            throw new MissingDetailsException(error);

        }

    }
    
}

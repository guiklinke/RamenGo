package com.RamenGo.RamenGo.controllers;


import com.RamenGo.RamenGo.domain.Order;
import com.RamenGo.RamenGo.dtos.OrderRequest;
import com.RamenGo.RamenGo.exceptions.ApiKeyMissingException;
import com.RamenGo.RamenGo.exceptions.InvalidOrderRequestException;
import com.RamenGo.RamenGo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order>
    placeOrder(@RequestBody OrderRequest orderRequest,
                    @RequestHeader(required = false, value = "x-api-key") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new ApiKeyMissingException();
        }
        HttpHeaders headers = new HttpHeaders();
        if (orderRequest.brothId() == null || orderRequest.proteinId() == null) {
            throw new InvalidOrderRequestException("both brothId and proteinId are required");
        }

        headers.add("X-Status-Message", "Order placed successfully");

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(orderService.placeOrder(orderRequest));
    }
}

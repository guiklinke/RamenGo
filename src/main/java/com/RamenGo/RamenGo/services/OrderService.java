package com.RamenGo.RamenGo.services;

import com.RamenGo.RamenGo.domain.Broth;
import com.RamenGo.RamenGo.domain.Order;
import com.RamenGo.RamenGo.domain.Protein;
import com.RamenGo.RamenGo.dtos.OrderIdResponse;
import com.RamenGo.RamenGo.dtos.OrderRequest;
import com.RamenGo.RamenGo.exceptions.OrderPlacementException;
import com.RamenGo.RamenGo.repositories.BrothRepository;
import com.RamenGo.RamenGo.repositories.OrderRepository;
import com.RamenGo.RamenGo.repositories.ProteinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    BrothRepository brothRepository;

    @Autowired
    ProteinRepository proteinRepository;

    @Autowired
    OrderRepository orderRepository;

    public OrderIdResponse generateOrderId() {
        String url = "https://api.tech.redventures.com.br/orders/generate-id";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "ZtVdh8XQ2U8pWI2gmZ7f796Vh8GllXoN7mr0djNf");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        return restTemplate.postForObject(
                url, httpEntity, OrderIdResponse.class);
    }

    public Order placeOrder(OrderRequest orderRequest) {
        OrderIdResponse orderIdResponse = this.generateOrderId();
        if(orderRepository.existsById(orderIdResponse.orderId())){
            throw new OrderPlacementException ("could not place order");
        }

        Order order = new Order();
        order.setId(orderIdResponse.orderId());

        Broth broth = brothRepository.findById(orderRequest.brothId()).orElseThrow(
                        () -> new OrderPlacementException
                                ("could not place order"));
        Protein protein = proteinRepository.findById(orderRequest.proteinId()).orElseThrow(
                () -> new OrderPlacementException
                        ("could not place order"));
        String orderDescription = broth.getName() + " and " + protein.getName() + " Ramen";

        if(orderRepository.existsByDescription(orderDescription)){
            throw new OrderPlacementException ("could not place order");
        }

        order.setDescription(orderDescription);
        order.setImage("https://tech.redventures.com.br/icons/ramen/ramen" + protein.getName());
        orderRepository.save(order);


        return order;
    }
}



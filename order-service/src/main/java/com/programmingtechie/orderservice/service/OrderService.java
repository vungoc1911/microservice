package com.programmingtechie.orderservice.service;

import com.programmingtechie.orderservice.dto.OrderLineItemDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = request.getOrderLineItemDtos()
                .stream()
                .map(orderLineItemDto -> mapToDto(orderLineItemDto))
                .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItems;
    }
}

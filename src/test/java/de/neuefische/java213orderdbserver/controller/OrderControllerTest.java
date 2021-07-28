package de.neuefische.java213orderdbserver.controller;

import de.neuefische.java213orderdbserver.model.Order;
import de.neuefische.java213orderdbserver.model.Product;
import de.neuefische.java213orderdbserver.repository.OrderRepository;
import de.neuefische.java213orderdbserver.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getAllOrdersShouldReturnAllOrders() {

        String addOrderUrl = "http://localhost:" + port + "/order";
        ResponseEntity<Order> orderResponseEntity = testRestTemplate.postForEntity(addOrderUrl, List.of("1"), Order.class);

        Order expected = new Order("1", List.of(new Product("1","piano")));

        assertEquals(HttpStatus.OK, orderResponseEntity.getStatusCode());
        assertEquals(expected.getProducts(), orderResponseEntity.getBody().getProducts());


        //given
        String url = "http://localhost:" + port + "/order/all";
        //when

        ResponseEntity<Order[]> response = testRestTemplate.getForEntity(url, Order[].class);
        //then
        Order[] expectedOrders = {
            expected
        };

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders[0].getProducts(),response.getBody()[0].getProducts());
    }
    @Test
    @DisplayName("Order-ID-Generierung wird mit Mockito getestet")
    public void testOrderIdGenerator(){
        //given
        OrderService orderService = new OrderService();
        OrderRepository orderRepositoryMock = mock(OrderRepository.class);
        when(orderRepositoryMock.)
        //when
        //then
    }
}



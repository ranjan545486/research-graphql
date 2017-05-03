package me.roybailey.springboot.graphql.fetchers;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.OrderDto;
import me.roybailey.springboot.ApplicationContextProvider;
import me.roybailey.springboot.service.OrderAdaptor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
@Component
public class OrderListFetcher implements DataFetcher<List<OrderDto>> {

    OrderAdaptor orderAdaptor;

    public OrderListFetcher() {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        orderAdaptor = context.getBean(OrderAdaptor.class);
    }

    @Override
    public List<OrderDto> get(DataFetchingEnvironment environment) {
        log.info("getArguments {}", environment.getArguments());
        List<OrderDto> allOrders = orderAdaptor.getAllOrders();
        return allOrders;
    }

}
package mate.academy.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import mate.academy.dao.OrderDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Order;
import mate.academy.model.ShoppingCart;
import mate.academy.model.User;
import mate.academy.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        if (shoppingCart.getTickets().isEmpty()) {
            throw new RuntimeException("There is no tickets in shopping cart " + shoppingCart);
        }

        Order order = new Order();
        order.setTickets(shoppingCart.getTickets());
        order.setUser(shoppingCart.getUser());
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        List<Order> orders = orderDao.getByUser(user);

        if (orders.isEmpty()) {
            throw new RuntimeException("User: " + user + " has no orders");
        }
        return orders;
    }
}

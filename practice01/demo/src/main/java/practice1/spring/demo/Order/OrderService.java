package practice1.spring.demo.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}

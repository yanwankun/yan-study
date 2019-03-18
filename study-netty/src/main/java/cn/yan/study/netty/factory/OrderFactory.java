package cn.yan.study.netty.factory;

import cn.yan.study.netty.domain.Address;
import cn.yan.study.netty.domain.Customer;
import cn.yan.study.netty.domain.Order;

import java.util.Arrays;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class OrderFactory {
    public static Object create(int i) {
        Customer customer = new Customer();
        customer.setFirstName("yan");
        customer.setLastName("kun");
        customer.setMiddleNames(Arrays.asList("wan", "hao"));

        Address billTo = new Address();
        billTo.setCity("hangzhou");
        billTo.setCountry("china");
        billTo.setPostCode("123456");
        billTo.setState("test");

        Address shipTo = new Address();
        shipTo.setCity("suzhou");
        shipTo.setCountry("china");
        shipTo.setPostCode("123446");
        shipTo.setState("test");

        Order order = new Order();
        order.setCustomer(customer);
        order.setBillTo(billTo);
        order.setShipTo(shipTo);

        return order;
    }
}

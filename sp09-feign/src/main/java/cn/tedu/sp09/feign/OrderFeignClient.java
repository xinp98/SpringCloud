package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author XinP
 * @Date 2020/8/29 10:52
 */
@FeignClient(value = "order-service",fallback = OrderFeignClientFB.class)
public interface OrderFeignClient {
    @GetMapping("/{orderId}")
    JsonResult<Order> getOrder(@PathVariable String orderId);

    @GetMapping("/order-service/")
    JsonResult addOrder();
}

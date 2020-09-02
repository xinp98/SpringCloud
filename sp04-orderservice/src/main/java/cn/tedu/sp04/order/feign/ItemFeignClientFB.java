package cn.tedu.sp04.order.feign;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XinP
 * @Date 2020/8/31 10:36
 */
@Component
public class ItemFeignClientFB implements ItemFeignClient{
    @Override
    public JsonResult<List<Item>> getItems(String orderId) {
        //模拟缓存数据
        if (Math.random() < 0.5){
            ArrayList<Item> items = new ArrayList<>();
            items.add(new Item(1,"缓存商品1",1));
            items.add(new Item(2,"缓存商品1",4));
            items.add(new Item(3,"缓存商品1",2));
            items.add(new Item(4,"缓存商品1",6));
            items.add(new Item(5,"缓存商品1",1));
            return JsonResult.ok().data(items);
        }
        return JsonResult.err("获取商品列表失败");
    }

    @Override
    public JsonResult decreaseNumber(List<Item> items) {

        return JsonResult.err("减少商品库存失败");
    }
}

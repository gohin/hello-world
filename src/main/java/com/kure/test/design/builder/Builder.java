package com.kure.test.design.builder;

import com.kure.test.design.springboot.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {
    // 生产者
    private final Supplier<T> supplier;
    // 消费者
    private List<Consumer<T>> consumers = new ArrayList<>();
    // 构建者
    public Builder(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    // 构架方法
    public static <T> Builder<T> of(Supplier<T> supplier){
        return new Builder<>(supplier);
    }

    // 赋值
    public <P> Builder<T> with(BiConsumer<T, P> consumer1, P p){
        Consumer<T> c = (instance -> consumer1.accept(instance, p));
        consumers.add(c);
        return this;
    }

    // 构建
    public T builder(){
        T value = supplier.get();
        consumers.forEach(consumer -> {
            consumer.accept(value);
        });
        return value;
    }

    public static void main(String[] args) {
        BiConsumer<Consumer<String>, String> biConsumer = (consumer, p)->{
            consumer.accept(p);
        };
        Order order2 = new Order();
        biConsumer.accept(order2::setCode,"order2");
        System.out.println(order2.getCode());

        Order order = new Order();
        Order order1 = Builder.of(()-> order).with(Order::setCode,"order").builder();
        System.out.println(order1.getCode());
    }

}

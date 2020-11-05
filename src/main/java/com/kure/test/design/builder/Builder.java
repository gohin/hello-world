package com.kure.test.design.builder;

import com.kure.test.design.springboot.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {
    private final Supplier<T> supplier;
    private List<Consumer<T>> consumers = new ArrayList<>();
    public Builder(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    public static <T> Builder<T> of(Supplier<T> supplier){
        return new Builder<>(supplier);
    }

    public <P> Builder<T> with(BiConsumer<T, P> consumer1, P p){
        Consumer<T> c = (instance -> consumer1.accept(instance, p));
        consumers.add(c);
        return this;
    }

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

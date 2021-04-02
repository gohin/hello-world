package com.kure.test.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        Iterator<IShout> it = shouts.iterator();
        for (IShout s : shouts) {
            s.shout();
        }
    }
}

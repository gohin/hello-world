package com.kure.test.design.facade.serivce;

import com.kure.test.design.facade.serivce.impl.Circle;

public class ShapeFacade {

    public Shape getCircle() {
        return new Circle();
    }
}

package com.mycomp.market.ecommerce.health;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeartBeatEndPoint extends AbstractEndpoint {


    public HeartBeatEndPoint() {
        super("health", false, true);
    }

    @Override
    public List<String> invoke() {
        List<String> list = new ArrayList<String>();
        list.add("App message 1");
        list.add("App message 2");
        list.add("App message 3");
        list.add("App message 4");
        return list;
    }

}

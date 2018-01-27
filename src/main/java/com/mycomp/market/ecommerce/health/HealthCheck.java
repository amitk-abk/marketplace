package com.mycomp.market.ecommerce.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

//    @Override
//    protected void doHealthCheck(Health.Builder builder) throws Exception {
//        builder.up();
//
//    }

    private int check() {
        // Your logic to check health
        return 0;
    }

}
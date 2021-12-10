package com.example.demo.projection;

import java.math.BigDecimal;

public interface CustomerSpentMoney extends CustomerInfo {
    BigDecimal getSpent();
}

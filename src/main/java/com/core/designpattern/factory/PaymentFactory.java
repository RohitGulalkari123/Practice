package com.core.designpattern.factory;

import com.core.designpattern.factory.impl.GooglePay;
import com.core.designpattern.factory.impl.PayTm;
import com.core.designpattern.factory.impl.PhonePay;
import com.core.designpattern.factory.inter.PaymentProcessor;

public class PaymentFactory {
    public static PaymentProcessor create(String type) {
        switch (type.toLowerCase()) {
            case "googlepay":
                return new GooglePay();
            case "paytm":
                return new PayTm();
            case "phonepay":
                return new PhonePay();
            default:
                throw new IllegalArgumentException("Unknown: " + type);
        }
    }
}

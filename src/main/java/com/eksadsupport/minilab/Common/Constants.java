package com.eksadsupport.minilab.Common;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static String STATUS = "S";
    public static String CODE = "201";
    public static String MESSAGE = "Process Successed";
    public static List<String> SALES_ARGS = Arrays.asList(new String[]{"salesId", "salesName", "dealerId", "supervisorId", "salesGender", "salesEmail", "salesStatus"});
    public static List<String> ORDER_ARGS = Arrays.asList(new String[]{"orderId", "unitCode", "dealerCode", "salesId", "customerId", "minimumPayment", "totalValue", "orderValue", "offtheroadValue", "orderDiscount", "ppn", "platNomor", "nomorMesin", "nomorRangka", "isLeasing", "paymentStatus", "unitStatus"});
}
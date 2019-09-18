package com.moredian.entrance.guard.utils;

import android.util.Log;

import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.entity.SimpleExpense;

import android_serialport_api.ChangeTool;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/18 14:10
 */
public class SerialPortApi {

    /**
     * descirption: 拼接name
     */
    public static String getNameHex(String name) {
        String namehex = ChangeTool.toChineseHex(name);
        StringBuilder stringBuilder = new StringBuilder();
        if (namehex.length() < 18) {
            for (int i = 0; i < (18 - namehex.length()); i++) {
                stringBuilder.append("0");
            }
        } else if (namehex.length() > 18) {
            namehex = namehex.substring(0, 18);
        }
        namehex = namehex + stringBuilder.toString();
        return namehex;
    }

    /**
     * descirption: 消费成功，拼接字符，数据下行
     */
    public static void consumeSenddown(SimpleExpense simpleExpense, int status, String name) {
        double amount = simpleExpense.getContent().getExpenseDetail().getAmount();
        double oamount = simpleExpense.getContent().getExpenseDetail().getOriginalAmount();
        double balance = simpleExpense.getContent().getExpenseDetail().getBalance();
        int paycount = simpleExpense.getContent().getExpenseDetail().getPayCount();
        int discountrate = simpleExpense.getContent().getExpenseDetail().getDiscountRate();
        String consumestatus = ChangeTool.numToHex1(simpleExpense.getContent().getTradingState());
        String discountratehex = ChangeTool.numToHex1(discountrate);
        String namehex = getNameHex(name);
        String balancehex = ChangeTool.numToHex3((int) (balance * 100));
        String amounthex = ChangeTool.numToHex3((int) (amount * 100));
        String oamounthex = ChangeTool.numToHex3((int) (oamount * 100));
        String paycounthex = ChangeTool.numToHex2(paycount);
        String statushex = ChangeTool.numToHex1(status);
        String sum = "0301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus;
        MainApplication.getSerialPortUtils().sendSerialPort("A1B1030301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus + ChangeTool.makeChecksum(sum));
    }
}
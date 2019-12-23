package com.moredian.entrance.guard.utils;

import android.util.Log;

import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.entity.DefiniteExpense;
import com.moredian.entrance.guard.entity.FaceExpense;
import com.moredian.entrance.guard.entity.QRCodeExpense;
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
     * descirption: 下发卡密码
     */
    public static void givePassword(String password) {
        //12位
        /*String cp = ChangeTool.strTo16(password);
        Log.d("givePassword", "givePassword: " + cp);
        if (cp.length() > 24) {
            cp = cp.substring(0,24);
        }
        StringBuilder sb = new StringBuilder();
        if (cp.length() < 24) {
            int count = 24 - cp.length();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            sb.append(cp);
            String sum = "650101000C" + sb.toString();
            MainApplication.getSerialPortUtils().sendSerialPort("A1B103" + sum + ChangeTool.makeChecksum(sum));
            Log.d("givePassword", "givePassword: " + "A1B103" + sum + ChangeTool.makeChecksum(sum));
            return;
        }
        String sum = "650101000C" + cp;
        MainApplication.getSerialPortUtils().sendSerialPort("A1B103" + sum + ChangeTool.makeChecksum(sum));
        Log.d("givePassword", "givePassword: " + "A1B103" + sum + ChangeTool.makeChecksum(sum));*/
        String sum = "6501010006" + password;
        MainApplication.getSerialPortUtils().sendSerialPort("A1B103" + sum + ChangeTool.makeChecksum(sum));
        Log.d("givePassword", "givePassword: " + "A1B103" + sum + ChangeTool.makeChecksum(sum));
    }

    /**
     * descirption: 消费成功，拼接字符，数据下行
     */
    public static void consumeSenddown(Object simpleExpense, int status, String name) {
        if (simpleExpense instanceof SimpleExpense) {
            double amount = ((SimpleExpense) simpleExpense).getContent().getExpenseDetail().getAmount();
            double oamount = ((SimpleExpense) simpleExpense).getContent().getExpenseDetail().getOriginalAmount();
            double balance = ((SimpleExpense) simpleExpense).getContent().getExpenseDetail().getBalance();
            int paycount = ((SimpleExpense) simpleExpense).getContent().getExpenseDetail().getPayCount();
            int discountrate = ((SimpleExpense) simpleExpense).getContent().getExpenseDetail().getDiscountRate();
            String consumestatus = ChangeTool.numToHex1(((SimpleExpense) simpleExpense).getContent().getTradingState());
            String discountratehex = ChangeTool.numToHex1(discountrate);
            String namehex = getNameHex(name);
            String balancehex = ChangeTool.numToHex3((int) (balance * 100));
            String amounthex = ChangeTool.numToHex3((int) (amount * 100));
            String oamounthex = ChangeTool.numToHex3((int) (oamount * 100));
            String paycounthex = ChangeTool.numToHex2(paycount);
            String statushex = ChangeTool.numToHex1(status);
            String sum = "0301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus;
            MainApplication.getSerialPortUtils().sendSerialPort("A1B1030301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus + ChangeTool.makeChecksum(sum));
        } else if (simpleExpense instanceof DefiniteExpense) {
            double amount = ((DefiniteExpense) simpleExpense).getContent().getExpenseDetail().getAmount();
            double oamount = ((DefiniteExpense) simpleExpense).getContent().getExpenseDetail().getOriginalAmount();
            double balance = ((DefiniteExpense) simpleExpense).getContent().getExpenseDetail().getBalance();
            int paycount = ((DefiniteExpense) simpleExpense).getContent().getExpenseDetail().getPayCount();
            int discountrate = ((DefiniteExpense) simpleExpense).getContent().getExpenseDetail().getDiscountRate();
            String consumestatus = ChangeTool.numToHex1(((DefiniteExpense) simpleExpense).getContent().getTradingState());
            String discountratehex = ChangeTool.numToHex1(discountrate);
            String namehex = SerialPortApi.getNameHex(name);
            String balancehex = ChangeTool.numToHex3((int) (balance * 100));
            String amounthex = ChangeTool.numToHex3((int) (amount * 100));
            String oamounthex = ChangeTool.numToHex3((int) (oamount * 100));
            String paycounthex = ChangeTool.numToHex2(paycount);
            String statushex = ChangeTool.numToHex1(status);
            String sum = "0301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus;
            MainApplication.getSerialPortUtils().sendSerialPort("A1B1030301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus + ChangeTool.makeChecksum(sum));
        } else if (simpleExpense instanceof FaceExpense) {
            double amount = ((FaceExpense) simpleExpense).getContent().getExpenseDetail().getAmount();
            double oamount = ((FaceExpense) simpleExpense).getContent().getExpenseDetail().getOriginalAmount();
            double balance = ((FaceExpense) simpleExpense).getContent().getExpenseDetail().getBalance();
            int paycount = ((FaceExpense) simpleExpense).getContent().getExpenseDetail().getPayCount();
            int discountrate = ((FaceExpense) simpleExpense).getContent().getExpenseDetail().getDiscountRate();
            String consumestatus = ChangeTool.numToHex1(((FaceExpense) simpleExpense).getContent().getTradingState());
            String discountratehex = ChangeTool.numToHex1(discountrate);
            String namehex = SerialPortApi.getNameHex(name);
            String balancehex = ChangeTool.numToHex3((int) (balance * 100));
            String amounthex = ChangeTool.numToHex3((int) (amount * 100));
            String oamounthex = ChangeTool.numToHex3((int) (oamount * 100));
            String paycounthex = ChangeTool.numToHex2(paycount);
            String statushex = ChangeTool.numToHex1(status);
            String sum = "0301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus;
            MainApplication.getSerialPortUtils().sendSerialPort("A1B1030301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus + ChangeTool.makeChecksum(sum));
        } else if (simpleExpense instanceof QRCodeExpense) {
            double amount = ((QRCodeExpense) simpleExpense).getContent().getThirdPartyExpense().getAmount();
            double oamount = ((QRCodeExpense) simpleExpense).getContent().getThirdPartyExpense().getOriginalAmount();
            String qrtype = "0" + ((QRCodeExpense) simpleExpense).getContent().getQRType();
            String consumestatus = ChangeTool.numToHex1(((QRCodeExpense) simpleExpense).getContent().getTradingState());
            String discountratehex = "00";
            String namehex = SerialPortApi.getNameHex(name);
            String balancehex = "000000";
            String amounthex = ChangeTool.numToHex3((int) (amount * 100));
            String oamounthex = ChangeTool.numToHex3((int) (oamount * 100));
            String paycounthex = "0000";
            String statushex = ChangeTool.numToHex1(status);
            String sum = "0501010018" + qrtype + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus;
            MainApplication.getSerialPortUtils().sendSerialPort("A1B1030501010018" + qrtype + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus + ChangeTool.makeChecksum(sum));
        }
    }
}

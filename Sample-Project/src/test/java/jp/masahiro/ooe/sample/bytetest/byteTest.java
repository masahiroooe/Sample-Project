package jp.masahiro.ooe.sample.bytetest;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;


public class byteTest {
    
    @Test
    public void byte2String() throws Exception {
        byte[] b = {0x0F,0x00};
        byte[] b2 = "あいうえお".getBytes();
        String sHex = DatatypeConverter.printHexBinary(b);
        String sBin = DatatypeConverter.printBase64Binary(b);

        System.out.println("hex:"+sHex);
        System.out.println("bin:"+sBin);

        sHex = DatatypeConverter.printHexBinary(b2);
        sBin = DatatypeConverter.printBase64Binary(b2);

        System.out.println("hex:" + sHex);
        System.out.println("bin:" + sBin);
        System.out.println("b[0]:" + Integer.toBinaryString(b[0]));
        System.out.println("b[0]:" + Integer.toBinaryString(b[1]));
        System.out.println("max :" + Integer.toBinaryString(Byte.MAX_VALUE));
        System.out.println("b[0]:" + String.format("%8s", Integer.toBinaryString(b[0])).replace(' ', '0'));
        System.out.println("b[1]:" + String.format("%8s", Integer.toBinaryString(b[1])).replace(' ', '0'));
        System.out.println("for:" + String.format("%02X", b) );
    }

    @Test
    public void hex2bin2StringTest() throws Exception{
    }
}

package tests;

import clasesBasicas.TransferenciaImpl;

import java.util.GregorianCalendar;

public class TransferenciasTests {
    public static void main(String[] args) {
        TransferenciaImpl t = new TransferenciaImpl("AAA",true,"Hola",15,new GregorianCalendar());

        System.out.println(t.toString());
    }
}

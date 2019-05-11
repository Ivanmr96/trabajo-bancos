package tests;

import clasesBasicas.TransferenciaImpl;

import java.util.GregorianCalendar;
//TODO Terminar este test
public class TransferenciasTests {
    public static void main(String[] args) {
        TransferenciaImpl t = new TransferenciaImpl("AAA",true,"Hola",15,new GregorianCalendar());

        System.out.println(t.toString());
    }
}

package cbq.qa.iso.adopter.service;

import java.io.InputStream;

import org.jpos.iso.*;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.stereotype.Service;


@Service
public class ISO8583MessageGenerator {
	private  GenericPackager packager;

//    static {
//        try {
//            // Load the ISO8583 message packager configuration (ISO8583.xml)
//           // packager = new GenericPackager(getClass().getClassLoader().getResourceAsStream("iso8583/iso8583.xml"));
//        	InputStream inputStream = ISO8583MessageGenerator.class.getResourceAsStream("iso8583/iso8583.xml");
//            packager = new GenericPackager(inputStream);
//        } catch (ISOException e) {
//            e.printStackTrace();
//        }
//    }
    public ISOMsg generateMessage(String messageType, String amount, String dateTime) throws ISOException {
       
    	InputStream inputStream = ISO8583MessageGenerator.class.getResourceAsStream("iso8583/iso8583.xml");
        packager = new GenericPackager(inputStream);
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);

        // set header
        isoMsg.setHeader("ISO1987".getBytes());

        // set MTI as financial
        isoMsg.setMTI("0200");

        // set data fields
        isoMsg.set(2, "5642570404782927");
        isoMsg.set(3, "011000");
        isoMsg.set(4, "78000");
        isoMsg.set(7, "1220145711");
        isoMsg.set(11, "101183");
        isoMsg.set(12, "145711");
        isoMsg.set(13, "1220");
        isoMsg.set(14, "2408");
        isoMsg.set(15, "1220");
        isoMsg.set(18, "6011");
        isoMsg.set(22, "051");
        isoMsg.set(25, "00");
        isoMsg.set(26, "04");
        isoMsg.set(28, "C00000000");
        isoMsg.set(30, "C00000000");
        isoMsg.set(32, "56445700");
        isoMsg.set(37, "567134101183");
        isoMsg.set(41, "N1742");
        isoMsg.set(42, "ATM004");
        isoMsg.set(43, "45 SR LEDERSHIP DUABANAT NUEVA ECIJAQ PH");
        isoMsg.set(49, "608");
        isoMsg.set(102, "970630181070041");
        isoMsg.set(120, "BRN015301213230443463");


        return isoMsg;
//        msg.setMTI(messageType);
//        msg.pack(2, amount); // Assuming amount is in the second field
//        msg.pack(11, dateTime); // Assuming date/time is in the eleventh field
//        // Set other fields as needed
//        return msg;
    }
}
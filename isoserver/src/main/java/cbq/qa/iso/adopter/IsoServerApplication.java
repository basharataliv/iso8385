package cbq.qa.iso.adopter;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.messaging.Message;
import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
@SpringBootApplication
public class IsoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsoServerApplication.class, args);
	}
	
	@Bean
    public TcpNetServerConnectionFactory tcpServer() {
        TcpNetServerConnectionFactory serverFactory = new TcpNetServerConnectionFactory(5051);  // TCP Port for ISO server
        serverFactory.setSerializer(new ByteArrayLengthHeaderSerializer());
        serverFactory.setDeserializer(new ByteArrayRawSerializer());
        return serverFactory;
    }

    @ServiceActivator(inputChannel = "tcpInboundChannel")
    public void handleTcpMessage(Message<byte[]> message) throws Exception {
        byte[] isoMessageBytes = message.getPayload();
        
        // Parse the ISO 8583 message
        GenericPackager packager = new GenericPackager(getClass().getClassLoader().getResourceAsStream("iso8583/iso8583.xml")); // ISO 8583 XML definition
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);
        isoMsg.unpack(isoMessageBytes);
        
        // Log the parsed message
        System.out.println("ISO Message Received:");
        for (int i = 1; i <= isoMsg.getMaxField(); i++) {
            if (isoMsg.hasField(i)) {
                System.out.println("Field " + i + ": " + isoMsg.getString(i));
            }
        }
    }
}
//	@Bean
//     TcpNetServerConnectionFactory tcpNetServerConnectionFactory() {
//        TcpNetServerConnectionFactory serverFactory = new TcpNetServerConnectionFactory(5051);  // Port 9999
//        serverFactory.setSerializer(new ByteArrayRawSerializer()); // Raw serializer for byte stream
//        serverFactory.setDeserializer(new ByteArrayRawSerializer());
//        return serverFactory;
//    }
//
//    @ServiceActivator(inputChannel = "tcpInboundChannel")
//    public void handleTcpMessage(Message<byte[]> message) throws Exception {
//        byte[] payload = message.getPayload();
//        
//        // Parse the received ISO8583 message
//        ISOMsg isoMsg = parseIso8583Message(payload);
//
//        // Print the ISO8583 message fields
//        printIsoMessage(isoMsg);
//    }
//
//    private ISOMsg parseIso8583Message(byte[] data) throws Exception {
//        GenericPackager packager = new GenericPackager("iso8583/iso8583.xml");  // Load the ISO8583 configuration
//        ISOMsg isoMsg = new ISOMsg();
//        isoMsg.setPackager(packager);
//        isoMsg.unpack(data);  // Unpack the received data
//        return isoMsg;
//    }
//
//    private void printIsoMessage(ISOMsg isoMsg) throws Exception {
//        System.out.println("----ISO MESSAGE-----");
//        for (int i = 0; i <= isoMsg.getMaxField(); i++) {
//            if (isoMsg.hasField(i)) {
//                System.out.println("Field " + i + " : " + isoMsg.getString(i));
//            }
//        }
//    }
//}

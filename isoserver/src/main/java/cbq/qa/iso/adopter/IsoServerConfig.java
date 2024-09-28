package cbq.qa.iso.adopter;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;

import org.springframework.messaging.MessageChannel;

@Configuration
public class IsoServerConfig {

    @Bean
    public MessageChannel tcpInboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public TcpInboundGateway tcpInboundGateway(TcpNetServerConnectionFactory tcpServer) {
        TcpInboundGateway gateway = new TcpInboundGateway();
        gateway.setConnectionFactory(tcpServer);
        gateway.setRequestChannel(tcpInboundChannel());
        return gateway;
    }
}

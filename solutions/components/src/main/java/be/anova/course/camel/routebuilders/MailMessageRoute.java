package be.anova.course.camel.routebuilders;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MailMessageRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("timer:per-second?period=30000").process(new Processor() {

			public void process(Exchange exchange) throws Exception {
                exchange.getOut().setBody("<html><body>This is a generated test mail every 30 sec</body></html>");				
			}
			
		}).to("file:target/data/mail-out");
		
		from("file:target/data/mail-out").to("jms:queue:msgsout");
		
		from("jms:queue:msgsout")
		    .setHeader("timestamp").simple("${date:now:yyyyMMdd.hhss}").convertBodyTo(String.class)
		    .to("xslt:transform.xsl").process(new Processor() {

				public void process(Exchange exchange) throws Exception {
					Map<String, Object> headers = exchange.getIn().getHeaders();
					headers.put("From", "test.anova.rd@gmail.com");
					headers.put("To", "hans.couder@gmail.com");
					headers.put("Subject", "Order confirmation");
				}
		    	
		    })
		    .to("log:test")
		    .to("smtps:smtp.gmail.com?password=test1975&username=test.anova.rd@gmail.com");
	}
	
	

}

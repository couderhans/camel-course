package be.anova.course.camel.RouteBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class LogMessageRouteBuilder extends RouteBuilder {
	
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddhhmmssSSS");

	@Override
	public void configure() throws Exception {
    
        from("timer://test?fixedRate=true&period=5000").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
                exchange.getIn().setBody(DATE_FORMAT.format(new Date()));
            }
        }).to("log:test");
	
	}

}

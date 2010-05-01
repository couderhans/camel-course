package be.anova.course.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyProcessor implements Processor {
	
	private static final Log LOG = LogFactory.getLog(MyProcessor.class);
	
	public void process(Exchange exchange) throws Exception {
        LOG.debug("Loggin Message:" + exchange.getIn().getBody());

	}
}

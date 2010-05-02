package be.anova.course.camel.RouteBuilders;

import org.apache.camel.builder.RouteBuilder;

public class OrderRouteBuilder extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		from("file:src/data/order-in")
		    .wireTap("file:src/audit/")
		    .to("direct:orders");
		
		from("direct:orders")
		    .split().xpath("/orders/order")
		    .to("direct:order");
		
		from("direct:order")
		    .choice()
		        .when().xpath("/order/customer/@country='Scotland'")
		        .to("file:src/data/orders-out/Scotland")
		        .when().xpath("/order/customer/@country='Belgium'")
		        .to("file:src/data/orders-out/Belgium");
	
		
	}

}

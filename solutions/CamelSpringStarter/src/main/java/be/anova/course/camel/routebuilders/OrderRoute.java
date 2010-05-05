package be.anova.course.camel.routebuilders;

import org.apache.camel.builder.RouteBuilder;

public class OrderRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {

		
		from("file:src/main/resources/be/anova/course/camel/orders/in")
		    .wireTap("file:src/main/resources/be/anova/course/camel/orders/audit")
		    .to("direct:orders");
		
		from("direct:orders")
		    .split().xpath("/orders/order")
		    .to("direct:order");
		
		from("direct:order")
		    .choice()
		        .when().xpath("/order/customer/@country='Scotland'")
		        .to("file:src/main/resources/be/anova/course/camel/orders/out/Scotland")
		        .when().xpath("/order/customer/@country='Belgium'")
		        .to("file:src/main/resources/be/anova/course/camel/orders/out/Belgium");
		
	}

}

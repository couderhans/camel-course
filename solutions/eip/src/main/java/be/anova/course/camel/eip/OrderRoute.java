/*
 * (c) 2010, anova r&d bvba. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.anova.course.camel.eip;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

import static org.apache.camel.builder.ExpressionBuilder.simpleExpression;
import static org.apache.camel.builder.xml.XPathBuilder.xpath;

public class OrderRoute extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {

        from("file:src/main/resources/be/anova/course/camel/orders/in?noop=true")
                .wireTap("file:target/orders/audit")
                .to("direct:orders");

        from("direct:orders").split().xpath("//orders/order")
                .convertBodyTo(String.class).to("direct:qty").to("log:order");
        
        from("direct:qty")
            .choice().when().xpath("//order/articles/article/quantity='0'")
            .to("log:empty-orders")
            .otherwise().to("direct:order");

        from("direct:order")
                .setHeader("externalid",xpath("/order/@externalid"))
                .setHeader(Exchange.FILE_NAME,simpleExpression("${header.externalid}.xml"))
                .choice().when().xpath("//order/customer/@country='Scotland'")
                .to("file:target/orders/Scotland")
                .when().xpath("//order/customer/@country='Belgium'")
                .to("file:target/orders/Belgium")
                .otherwise().to("file:target/orders/Other");

    }

}

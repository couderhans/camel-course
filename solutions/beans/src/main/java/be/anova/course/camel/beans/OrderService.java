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
package be.anova.course.camel.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.language.Simple;
import org.apache.camel.language.XPath;

public class OrderService {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    public void enrich(Exchange exchange) {
        exchange.getIn().setHeader("timestamp", DATE_FORMAT.format(new Date()));
        System.out.printf("Setting timestamp in header %s: \n", exchange.getIn().getHeader("timestamp"));
    }

    public void process(@Header("timestamp") String timestamp, @Body String body) {
        System.out.printf("Timestamp: %s \nBody: %s \n", timestamp, body);
    }

    @Consume(uri = "direct:order")
    public void log(@Simple("${file:name}") String name, @XPath("order/customer/@name") String customer) {
        System.out.printf("Handling order: %s for customer %s", name, customer);
    }

}

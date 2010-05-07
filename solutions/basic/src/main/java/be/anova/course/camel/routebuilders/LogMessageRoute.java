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

package be.anova.course.camel.routebuilders;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class LogMessageRoute extends RouteBuilder {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
            "yyyyMMddhhmmssSSS");

    @Override
    public void configure() throws Exception {

        from("timer://test?fixedRate=true&period=5000").process(
                new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn()
                                .setBody(DATE_FORMAT.format(new Date()));
                    }
                }).to("log:test");

    }

}

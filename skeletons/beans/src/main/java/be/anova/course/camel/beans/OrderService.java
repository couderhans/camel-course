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
        //TODO: add a timestamp header to the exchange
    }

    // TODO: add annotations to use the timestamp header you added before and the message body
    public void process(String timestamp, String body) {
        //TODO: show body and timestamp in the console
    }

    // TODO: add annotations
    //       - to consume from the direct:order endpoint
    //       - to access the file (using simple expresssion) and customer name (using xpath)
    public void log(String name, String customer) {
        // TODO: show file and customer name in the console
    }

}

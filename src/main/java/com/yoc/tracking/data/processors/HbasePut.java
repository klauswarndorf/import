/**
 *
 */
package com.yoc.tracking.data.processors;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author klaus
 *
 */
public class HbasePut implements Processor {

    private ArrayList<String> mapping;

    @Override
    public void process(Exchange exchange) throws Exception {
        @SuppressWarnings("unchecked")
        ArrayList<String> line = (ArrayList<String>) exchange.getIn().getBody();

        if (line.size() > 1) {
            this.setHeader();

            String id = line.get(1) + line.get(2) + line.get(3) + line.get(4)
                    + line.get(5) + line.get(6) + line.get(7);

            int i = 0;
            for (String value : line) {
                // sets a postfix as camel-hbase expects for header data
                String pf = "";
                if (i != 0) {
                    pf = String.valueOf(i);
                }

                // sets header data for camel-hbase
                exchange.getIn().setHeader("CamelHBaseRowId" + pf, id);
                exchange.getIn().setHeader("CamelHBaseFamily" + pf, "cf");
                exchange.getIn().setHeader("CamelHBaseQualifier" + pf,
                        this.getHeader(i));
                exchange.getIn().setHeader("CamelHBaseValue" + pf, value);
                // increment counter
                i++;
            }
        }
    }

    private String getHeader(int n) {
        return this.mapping.get(n);
    }

    private void setHeader() {
        this.mapping = new ArrayList<String>();
        this.mapping.add("pulp");
        this.mapping.add("affiliate_id");
        this.mapping.add("merchant_id");
        this.mapping.add("adplatform_id");
        this.mapping.add("partnership_id");
        this.mapping.add("curad_id");
        this.mapping.add("cond_id");
        this.mapping.add("programm_currency");
        this.mapping.add("client_ip");
        this.mapping.add("info");
        this.mapping.add("merchant_info");
        this.mapping.add("referer");
        this.mapping.add("call_type");
        this.mapping.add("rotation_list_id");
        this.mapping.add("fingerprint");
        this.mapping.add("useragent");
        this.mapping.add("cookiets");
        this.mapping.add("cookie_typ");
        this.mapping.add("fill");
        this.mapping.add("fill1");
        this.mapping.add("fill2");
        this.mapping.add("fill3");
        this.mapping.add("fill4");
    }

}

package com.project.bushop.bushop;

public class Person {

    private String bus_no;
    private String source;
    private String destination;
    private String time;

        public Person(Object bus_no, Object source, Object destination, Object time)
        {
           this.bus_no= (String) bus_no;
           this.source= (String) source;
            this.destination= (String) destination;
            this.time= (String) time;

        }

    public String getBus_no() {
        return bus_no;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public String getTime() {
        return time;
    }
}

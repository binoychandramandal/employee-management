package com.employee;

public class Url {
    public static void main(String[] args) {
        String str = "http://localhost:8080/expense/236";

        //print host+ protocol+port+ uri
        printInfo(str);
        printInfo("http://192.168.10.1:8989/expense/123");
        HostInfo hi= printInfo("http://localhost:4200w/employee-details/125/erjkhfjkdf/lksdahfjkljhshfds/klsdhjfjkljsadf");

        System.out.println(hi);
    }

    static class HostInfo {
        int port;
        String protocol;
        String host;
        String uri;

        public HostInfo(int port, String protocol, String host, String uri) {
            this.port = port;
            this.protocol = protocol;
            this.host = host;
            this.uri = uri;
        }

        @Override
        public String toString() {
            return "HostInfo{" +
                    "port=" + port +
                    ", protocol='" + protocol + '\'' +
                    ", host='" + host + '\'' +
                    ", uri='" + uri + '\'' +
                    '}';
        }
    }

    static HostInfo printInfo(String url) {

        int index = url.indexOf("//");

        String protocol = url.substring(0, index + 2);
        url = url.substring(protocol.length());
        System.out.println("Protocol: " + protocol);
        // System.out.println(url);

        //   localhost:8080/expense/236

        index = url.indexOf(":");
        String host = url.substring(0, index);
        url = url.substring(host.length() + 1);

        System.out.println("host: " + host);
        //System.out.println(url);

        // 8080/expense/236

        index = url.indexOf("/");
        String port = url.substring(0, index);
        url = url.substring(port.length());

        System.out.println("port: " + port);
        System.out.println("URI: " + url);

        HostInfo hi = new HostInfo(Integer.valueOf(port), protocol, host, url);
        return hi;
    }
}

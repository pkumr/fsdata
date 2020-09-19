package com.jnetworking;
import java.util.Enumeration;
import java.net.*;

public class IAddress {
    public static void main(String[] args) {
        try {
            //Network Interfaces
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            if(interfaceEnumeration == null){
                System.out.println("No Interface Found");
            }else {
                while (interfaceEnumeration.hasMoreElements()){
                    NetworkInterface networkInterface = interfaceEnumeration.nextElement();
                    System.out.println("Interface " + networkInterface.getName() + ":");
                    Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
                    if(!addressEnumeration.hasMoreElements()){
                        System.out.println("\t No Address for the interface");
                    }
                    while (addressEnumeration.hasMoreElements()){
                        InetAddress address = addressEnumeration.nextElement();
                        System.out.print("\t Address " +
                                ((address instanceof Inet4Address ? "(v4)"
                                        : (address instanceof  Inet6Address ? "(v6)"
                                        : "(?)" ))));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            }
        }catch (SocketException se){
            System.out.println("Error getting network interfaces " + se.getMessage());
        }

        //Get name(s)/addresses
        for (String host : args){
            try{
                System.out.println(host + ":");
                InetAddress[] addressList = InetAddress.getAllByName(host);
                for(InetAddress address : addressList){
                    System.out.println("\t" +  address.getHostName() + "/" + address.getHostName());
                }
            }
            catch (UnknownHostException e){
                System.out.println("\tUnable to find address for " + host);
            }
        }
    }
}

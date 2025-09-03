//Exception handling
package com;

import java.io.*;

public class Demo4
{
    public static void main(String[] args) throws FileNotFoundException
    {   
    	A();
    }
    static void A() throws FileNotFoundException{
    	B();
    	System.out.println("This is class A");
    }
    static void B() throws FileNotFoundException {
    	C();
    	System.out.println("This is class B");
    }
    static void C() throws FileNotFoundException{
    	File f= new File("Ratika.txt");
    	FileReader fr= new FileReader(f);
    	
    	
    }
}
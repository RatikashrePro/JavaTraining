package com;


sealed class Human 
{   
    public void printName()
    {
        System.out.println("Default");
    }
}

//sealed class M extends Human{
//	public void printName() {
//		System.out.println("Hello class M");
//	}
//}

non-sealed class Manish extends Human
{
    public void printName()
    {
        System.out.println("Manish Sharma");
    }
}


sealed class Vartika extends Human 
{
    public void printName()
    {
        System.out.println("Vartika Dadheech");
    }
}


final class Anjali extends Vartika
{
    public void printName()
    {
        System.out.println("Anjali Sharma");
    }
}

public class Demo2
{
    public static void main(String[] args)
    {   
        Human h= new Human();
        Human h1 = new Anjali();
        Human h2 = new Vartika();
        Human h3 = new Manish();
//        Human m=new M();
        
        h.printName();
        h1.printName();
        h2.printName();
        h3.printName();
//        m.printName();
    }
}
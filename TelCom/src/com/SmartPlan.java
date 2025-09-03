package com;

import java.util.*;
import java.io.*;

abstract class Plan{
	double total=0;
	int plan;
	int data;
	int voice;
	double vrate;
	double sms;
	boolean netflix, prime, hotstar, spotify;
	public abstract double Usage(int voice, int sms, int data);
	public abstract void PlanQuote(); 
	public abstract boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify); 
	
}

class BasicLite extends Plan{
	String planname="BasicLite";
	double total=0;
	BasicLite(){
		plan=249;
		data= 30;
		voice=100;
		vrate= 0.75;
		sms= 0.2;
		netflix= false;
		prime= false;
		hotstar= false;
		spotify= false;
	}
	public  double Usage(int min, int sms, int data) {
		int mins= min-voice;
		double amt=mins*0.75; //voice amt
		double samt= sms*this.sms; // sms amount
		double d= (data - this.data)*70.0; // data amt
		if(amt<0) {
			amt=0;
		}
		if(samt<0) {
			samt=0;
		}
		if(d<0) {
			d=0;
		}
		 total= amt+samt + d;
		System.out.println(total);
		return  total + plan ;
	}
	public  void PlanQuote() {
		System.out.println("============Recommended plan=========== \nBasicLite");
		System.out.println("Data: 1 GB/day (overage: ₹0.70/10 MB) ");
		System.out.println("Voice: 100 mins included, then ₹0.75/min");
		System.out.println("SMS: ₹0.20/SMS");
		System.out.println("OTT: none");
        System.out.println("---------------------------------------------------");

		
	} 
	public  boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
		if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify) {
			   return true;
			}
		return false;
		
	}
	public String plan () {
		return "BasicLite is the best plan 249! with data :"+ data + " Voice :"+ voice + 
				"mins included, + sms rs.0.2/sms" +
	              "For additional data/ voice/ sms you may have to pay+" + total +"... "  ;
	}

	public String toString() {
        return "This is the best plan"+ planname + "!";
    }

	
	
}

class Saver30 extends Plan{
	String planname="Saver30";
	double total=0;
    int smsfree=100;
	Saver30(){
		plan=499; //plan amt
		data= 45;  // data
		voice=300;  // voice 
		vrate= 0.75; // voice rate
		sms= 0.2; // sms rate
		
		netflix= false;
		prime= false;
		hotstar= true;
		spotify= false;
	}
	public  double Usage(int min, int sms, int data) {
		int mins= min-voice;
		double amt=mins*vrate; // voice amt
		double samt= sms*this.sms -smsfree; // sms amount
		double d= (data - this.data)*70.0; // data amt
		if(amt<0) {
			amt=0;
		}
		if(samt<0) {
			samt=0;
		}
		if(d<0) {
			d=0;
		}
	
	    total= amt+samt+ d; //total
		return  total + plan ;
	}
	public  void PlanQuote() {
		System.out.println("============Recommended plan=========== \nSaver30");

		System.out.println("Data: 1.5 GB/day (overage: ₹0.70/10 MB)");
		System.out.println("Voice: 300 mins included, then ₹0.75/min");
		System.out.println("SMS: 100 free, then ₹0.2/SMS");
		System.out.println("OTT: Hotstar");
        System.out.println("---------------------------------------------------");

		
	}  
	public  boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
		if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify) {
		   return true;
		}
		return false;
	}
	
	public String plan () {
		return "Saver30 is the best plan 499! with data :"+ data + " Voice : " + voice + 
				" mins included, + sms rs.0.2/sms" +
	            "For additional data/ voice/ sms you may have to pay+" + total +"... " ;
	}
	public String toString() {
        return "This is the best plan"+ planname + "!";
    }
	
	
}


class UnlimitedTalk30 extends Plan {
    String planname="UnlimitedTalk30";
    double total=0;
    UnlimitedTalk30() {
        plan=650;
        data=5; // total GB
        voice=Integer.MAX_VALUE; // unlimited
        vrate=0.0;
        sms=0.0; // unlimited
        netflix=false; prime=false; hotstar=false; spotify=true;
    }
    public double Usage(int min, int sms, int data) {
        double amt=0; // unlimited voice
        double samt=0; // unlimited sms
        double d=(data - this.data)*70.0; // over data after 5GB
        if(d<0) d=0;
        total=amt+samt+d;
        return total+plan;
    }
    public void PlanQuote() {
		System.out.println("============Recommended plan=========== \n UnlimitedTalk30");

        System.out.println("Data: 5 GB total (overage: ₹0.70/10 MB)");
        System.out.println("Voice: Unlimited");
        System.out.println("SMS: Unlimited");
        System.out.println("OTT: Spotify");
        System.out.println("---------------------------------------------------");

    }
    public boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
        if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify)
            return true;
        return false;
    }
    public String plan () {
        return planname+" rs"+plan+" with Unlimited Voice/SMS, 5GB data + extra charges "+total;
    }
    public String toString() {
        return "This is the best plan "+ planname + "!";
    }
}

class DataMax20 extends Plan {
    String planname="DataMax20";
    double total=0;
    DataMax20() {
        plan=749;
        data=Integer.MAX_VALUE; // unlimited data
        voice=100;
        vrate=0.75;
        sms=0.0; // unlimited
        netflix=false; prime=false; hotstar=true; spotify=false;
    }
    public double Usage(int min, int sms, int data) {
        int mins=min-voice;
        double amt=mins*vrate;
        if(amt<0) amt=0;
        double samt=0; // unlimited SMS
        double d=0; // unlimited Data
        total=amt+samt+d;
        return total+plan;
    }
    public void PlanQuote() {
		System.out.println("============Recommended plan=========== \n DataMax20");

        System.out.println("Data: Unlimited");
        System.out.println("Voice: 100 mins included, then ₹0.75/min");
        System.out.println("SMS: Unlimited");
        System.out.println("OTT: Hotstar");
        System.out.println("---------------------------------------------------");

    }
    public boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
        if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify)
            return true;
        return false;
    }
    public String plan () {
        return planname+" rs"+plan+" with Unlimited Data, 100 voice mins, OTT Hotstar";
    }
    public String toString() {
        return "This is the best plan "+ planname + "!";
    }
}

class StudentStream56 extends Plan {
    String planname="StudentStream56";
    double total=0;
    int smsfree=200;
    StudentStream56() {
        plan=435;
        data=60; // 2GB/day * 30 days
        voice=300;
        vrate=0.75;
        sms=0.2;
        netflix=false; prime=false; hotstar=false; spotify=true;
    }
    public double Usage(int min, int sms, int data) {
        int mins=min-voice;
        double amt=mins*vrate;
        if(amt<0) amt=0;
        double samt=(sms-smsfree)*this.sms;
        if(samt<0) samt=0;
        double d=(data-this.data)*70.0;
        if(d<0) d=0;
        total=amt+samt+d;
        return total+plan;
    }
    public void PlanQuote() {
		System.out.println("============Recommended plan=========== \n StudentStream56");

        System.out.println("Data: 2 GB/day (overage: ₹0.70/10 MB)");
        System.out.println("Voice: 300 mins included, then ₹0.75/min");
        System.out.println("SMS: 200 free, then ₹0.20/SMS");
        System.out.println("OTT: Spotify");
        System.out.println("---------------------------------------------------");

    }
    public boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
        if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify)
            return true;
        return false;
    }
    public String plan () {
        return planname+" rs"+plan+" with 2GB/day, 300 voice mins, 200 SMS free";
    }
    public String toString() {
        return "This is the best plan "+ planname + "!";
    }
}

class FamilyShare30 extends Plan {
    String planname="FamilyShare30";
    double total=0;
    int smsfree=500;
    FamilyShare30() {
        plan=500;
        data=50; // total GB
        voice=1000;
        vrate=0.6;
        sms=0.2;
        netflix=false; prime=true; hotstar=false; spotify=false;
    }
    public double Usage(int min, int sms, int data) {
        int mins=min-voice;
        double amt=mins*vrate;
        if(amt<0) amt=0;
        double samt=(sms-smsfree)*this.sms;
        if(samt<0) samt=0;
        double d=(data-this.data)*70.0;
        if(d<0) d=0;
        total=amt+samt+d;
        return total+plan;
    }
    public void PlanQuote() {
		System.out.println("============Recommended plan=========== \n FamilyShare30");

        System.out.println("Data: 50 GB total (overage: ₹0.70/10 MB)");
        System.out.println("Voice: 1000 mins included, then ₹0.60/min");
        System.out.println("SMS: 500 free, then ₹0.20/SMS");
        System.out.println("OTT: Amazon Prime");
        System.out.println("---------------------------------------------------");

    }
    public boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
        if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify)
            return true;
        return false;
    }
    public String plan () {
        return planname+" rs"+plan+" with 50GB, 1000 voice mins, 500 SMS free, OTT Prime";
    }
    public String toString() {
        return "This is the best plan "+ planname + "!";
    }
}

class DataMaxPlus30 extends Plan {
    String planname="DataMaxPlus30";
    double total=0;
    int smsfree=200;
    DataMaxPlus30() {
        plan=1499;
        data=Integer.MAX_VALUE; // unlimited data
        voice=300;
        vrate=0.75;
        sms=0.2;
        netflix=false; prime=true; hotstar=true; spotify=false;
    }
    public double Usage(int min, int sms, int data) {
        int mins=min-voice;
        double amt=mins*vrate;
        if(amt<0) amt=0;
        double samt=(sms-smsfree)*this.sms;
        if(samt<0) samt=0;
        double d=0; // unlimited data
        total=amt+samt+d;
        return total+plan;
    }
    public void PlanQuote() {
		System.out.println("============Recommended plan=========== \n DataMaxPlus30");

        System.out.println("Data: Unlimited");
        System.out.println("Voice: 300 mins included, then ₹0.75/min");
        System.out.println("SMS: 200 free, then ₹0.20/SMS");
        System.out.println("OTT: Amazon Prime + Hotstar");
        System.out.println("---------------------------------------------------");

    }
    public boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
        if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify)
            return true;
        return false;
    }
    public String plan () {
        return planname+" rs"+plan+" Unlimited Data, 300 mins, 200 SMS free, Prime+Hotstar";
    }
    public String toString() {
        return "This is the best plan "+ planname + "!";
    }
}

class PremiumUltra30 extends Plan {
    String planname="PremiumUltra30";
    double total=0;
    PremiumUltra30() {
        plan=2999;
        data=Integer.MAX_VALUE; // unlimited
        voice=Integer.MAX_VALUE;
        vrate=0.0;
        sms=0.0;
        netflix=true; prime=true; hotstar=true; spotify=true;
    }
    public double Usage(int min, int sms, int data) {
        return plan; // everything unlimited
    }
    public void PlanQuote() {
		System.out.println("============Recommended plan=========== \n PremiumUltra30");

        System.out.println("Data: Unlimited");
        System.out.println("Voice: Unlimited");
        System.out.println("SMS: Unlimited");
        System.out.println("OTT: Netflix + Prime + Hotstar + Spotify");
        System.out.println("---------------------------------------------------");
    }
    public boolean OTTRequirement(boolean netflix, boolean prime, boolean hotstar, boolean spotify) {
        if(this.netflix==netflix && this.prime==prime && this.hotstar==hotstar && this.spotify==spotify)
            return true;
        return false;
    }
    public String plan () {
        return planname+" rs"+plan+" with everything Unlimited + All OTTs";
    }
    public String toString() {
        return "This is the best plan "+ planname + "!";
    }
}



public class SmartPlan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		BasicLite basiclite= new BasicLite();
		Saver30 saver30= new Saver30();
		UnlimitedTalk30 unlimitedtalk30= new UnlimitedTalk30();
		DataMax20 datamax20= new DataMax20();
		StudentStream56 studentstream56 = new StudentStream56();
		FamilyShare30 familyshare30= new FamilyShare30();
		DataMaxPlus30 datamaxplus30= new DataMaxPlus30();
		PremiumUltra30 premiumultra30= new PremiumUltra30();
		
		System.out.println("Welcome to SmartPlan");
		int ch;
		Scanner sc= new Scanner(System.in);
		do {
			System.out.println("Enter your choice:");
			System.out.println("\t 1. Show plan details");
			System.out.println("\t 2. Give me the best plan(without considering ott)");
			System.out.println("\t 3. Give me the best plan(considering ott");
			System.out.println("\t 4. Exit");
			ch=sc.nextInt();
			if(ch==1) {
				basiclite.PlanQuote();
				saver30.PlanQuote();
				unlimitedtalk30.PlanQuote();
				datamax20.PlanQuote();
				studentstream56.PlanQuote();
				familyshare30.PlanQuote();
				datamaxplus30.PlanQuote();
				premiumultra30.PlanQuote();

				
				
			}
			else if(ch==2) {
				System.out.println("Enter you voice, sms and data requirements:");
				System.out.print("talktime in mins: ");
				int min= sc.nextInt();
				System.out.print("No of sms required: ");
				int sms= sc.nextInt();
				System.out.print("Data required (in GB): ");
				int data= sc.nextInt();
				Plan[] plans = {
		                new BasicLite(), new Saver30(), new UnlimitedTalk30(),
		                new DataMax20(), new StudentStream56(), new FamilyShare30(),
		                new DataMaxPlus30(), new PremiumUltra30()
		        };
		 
		        Plan bestPlan = null;
		        double bestCost = Double.MAX_VALUE;
		 
		        for (Plan p : plans) {
		            double cost = p.Usage(min, sms, data);
		        
		            if (cost < bestCost) {
		                bestCost = cost;
		                bestPlan = p;
		            }
		        }
		        bestPlan.PlanQuote();
			}
			else if(ch==3) {
				System.out.println("Give me your ott preferences");
				System.out.print("Netflix (Y/N):");
				String netflix= sc.next();
				System.out.print("Prime (Y/N):");
				String prime= sc.next();
				System.out.print("Hotstar (Y/N):");
				String hotstar= sc.next();
				System.out.print("Spotify (Y/N):");
				String spotify= sc.next();
				boolean n=true,p=true,h=true,sp=true;
				if(netflix.equals("n") || netflix.equals("N")) {
					n=false;
				}
				if(prime.equals("n") || prime.equals("N")) {
					p=false;
				}
				if(hotstar.equals("n") || hotstar.equals("N")) {
					h=false;
				}
				if(spotify.equals("n") || spotify.equals("N")) {
					sp=false;
				}
				ArrayList<Plan> arr= new ArrayList<Plan>();
			   if( basiclite.OTTRequirement(n, p, h, sp)) {
					arr.add(basiclite);
				}
				if( saver30.OTTRequirement(n, p, h, sp)) {
					arr.add(saver30);
				}
				if( unlimitedtalk30.OTTRequirement(n, p, h, sp)) {
					arr.add(unlimitedtalk30);
				}
				if( datamax20.OTTRequirement(n, p, h, sp)) {
					arr.add(datamax20);
				}
				if( studentstream56.OTTRequirement(n, p, h, sp)) {
					arr.add(studentstream56);
				}
				if( familyshare30.OTTRequirement(n, p, h, sp)) {
					arr.add(familyshare30);
				}
				if( datamaxplus30.OTTRequirement(n, p, h, sp)) {
					arr.add(datamaxplus30);
				}
				if( premiumultra30.OTTRequirement(n, p, h, sp)) {
					arr.add(premiumultra30);
				}
				


		        Collections.sort(arr, new Comparator<>() {
		            public int compare(Plan p1, Plan p2) {
		                return p1.plan - p2.plan;
		            }
		        });
		        System.out.println(arr.get(0));
//			   for(Plan p : arr) {
//            			 System.out.println("Plan " + p.planname + " matches OTT and costs" + cost);
//			}

			}
		}while(ch!=4);
		sc.close();

	}

}

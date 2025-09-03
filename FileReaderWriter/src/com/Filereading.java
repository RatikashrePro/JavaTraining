package com;

import java.io.*;
import java.util.*;
import java.io.FileReader;

public class Filereading {

	public static void main(String[] args) {
		
		try {
        File inputFile = new File("employees.txt");
        File tempFile = new File("employees_temp.txt");
        
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("Tech")) {
                continue; // Skip this line -> deletes Bob
            }
            bw.write(line);
            bw.newLine();
        }

        br.close();
        bw.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        System.out.println("Record deleted successfully!");
        
		}
		catch(Exception e) {
			
		}

		
		
		
		
		
		// TODO Auto-generated method stub
		
		
		
//		try {
//		FileReader fr= new FileReader("input.txt");
//        for (int ch = fr.read(); ch != -1; ch = fr.read()) {
//            System.out.print((char) ch);
//        }
//		fr.close();
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		
//		
//		
//		try {
//			System.out.println("Enter file name to read");
//			Scanner sc= new Scanner(System.in);
//			String filename=sc.next();
//			File file= new File(filename);
//			if(!file.exists()) {
//				System.out.println("File does not exist");
//				return;
//			}
//			
//			FileReader fr= new FileReader(filename);
//            for (int ch = fr.read(); ch != -1; ch = fr.read()) {
//                System.out.print((char) ch);
//            }
//
//			sc.close();
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}

	}

}

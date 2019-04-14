
package object_oriented_programming_demo_2;

import java.util.Scanner;


public class Terminal {

public static void main(String[] args) {
      
MakePerson avatar = new MakePerson("Bob","Caucasian","Male",5,7,14,120) {}; 
  
     Scanner scan = new Scanner(System.in);
     String initialize = scan.nextLine();

     A:        
 if (initialize.equals("init")){
   
     System.out.println();
     System.out.println("Terminal initialised\nIf you need help please type in HELP");
     
     
 }else{
     System.out.println("error 404 user must initialize to use terminal \n");
     
 }
    String hello = scan.nextLine();
 if (hello.equals("sayHello")){
     
    avatar.sayHello();
 }     
         String die = scan.nextLine();
 if (die.equals("Die")){
    avatar.die();
  
 }  
      
}      
}
    


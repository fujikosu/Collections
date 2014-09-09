import java.util.*;

public class CollectionsDemo {

   public static void main(String[] args) {
      List<String> a1 = new ArrayList();
      a1.add("Zara");
      a1.add("Mahnaz");
      a1.add("Ayan");
      System.out.println(" ArrayList Elements");
      System.out.print("\t" + a1);
      int k = 0;
      for(String f : a1 ){
    	  if(f == "Mahnaz"){
    		  a1.set(k, "Mohsen");
    	  }
    	  ++k;
      }
      Iterator<String> i = a1.iterator();
      while(i.hasNext()){
    	  String f = (String)i.next();
    	  System.out.println(f);
      }
      
      List<String> l1 = new LinkedList();
      l1.add("Zara");
      l1.add("Mahnaz");
      l1.add("Ayan");
      l1.remove("Zara");
      System.out.println(l1.contains("Ayan"));
      System.out.println(l1.size());
      System.out.println(" LinkedList Elements");
      System.out.print("\t" + l1);
   }
}
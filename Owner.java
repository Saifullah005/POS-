 public class Owner extends User{

    int tprofit=0,tsale=0;
    
    Owner(){
     super();
     
    }

    Order2 o1= new Order2();
    
     void dailySales(){
        System.out.println("DAILY RECORD IS:");
        System.out.println("Total number of orders are: "+o1.record.size());
        for(int i =0;i<o1.record.size();i++){
            tprofit+=o1.record.get(i).t_order_profit;
            tsale+=o1.record.get(i).t_order_price;
        }
        System.out.println("Total profit is:"+tprofit);
        System.out.println("Total sale is:"+tsale);
    
    
    }
    
    
    void weeklysales(){
    
    
    }
    
    void monthlysales(){
    
    
    
    }
    
    
    void totalprofit(){
          System.out.println("Total profit is:"+tprofit);
          System.out.println("Total sale is:"+tsale);

    }
    
    
    void totalorders(){
         System.out.println("Total number of orders are: "+o1.record.size());
    
    }
    
    
    void bestSellingproduct(){
    
    
    
    }
    
    
    void leastsellingproduct(){
    
    
    
    }
    
    
    void checkStock(){
    
    
    
    }
    
    void viewEmployeeSalaries(){
    
    
    
    }
    
    
    
}
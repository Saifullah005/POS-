    import java.time.LocalDate;
    public class Product {
        //Threshold for low stock:
        private static final int THRESHOLD = 5;
        
        private int id , quantity ;
        private double price;
        private String name;    
        private LocalDate expiryDate;

        public Product(int id , int quantity , double price , String name , LocalDate expDate){
            this.id = id;
            this.quantity = quantity;
            this.price = price;
            this.name = name;
            this.expiryDate = expDate;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public LocalDate getExpiryDate() {
            return expiryDate;
        }

        public boolean soldFromStock(int q){

            if (q <= 0) {
                return false;
            }

            if(this. quantity >= q){
                this.quantity -= q; 
                return true;
            }
            return false;
        }

        public boolean reStock(int q){
            if(q <= 0){
                return false;
            }
            quantity += q;
            return true;
        }

        public boolean lowStock(){
            if(quantity <= THRESHOLD){
                return true;
            }
            return false;
        }

        public boolean checkExpiry(){
            LocalDate current = LocalDate.now();
            if(current.isAfter(expiryDate)){
                return true;
            }
            return false;
        }

        @Override 
        public String toString(){
            return "Product ID: " + id 
            +"\nProduct Name: " + name + 
            "\nQuantity: " + quantity +
            "\nPrice: " + price + 
            "\nDate: " + expiryDate; 
        }
    }

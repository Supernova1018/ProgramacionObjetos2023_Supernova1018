public class BankAccount {
    private String nameClient;
    private double totalAmount;
    
    public BankAccount(String nameClient, double totalAmount) {
        this.nameClient = nameClient;
        this.totalAmount = totalAmount;
    }
    
    public String getNameClient() {
        return nameClient;
    }
    
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
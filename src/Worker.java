public class Worker extends User {
    protected String rank;
    protected double discount;

    //worker const
    public Worker(String userName, String firstName, String lastName, String password, String rank){
        super(userName, firstName, lastName, password);
        this.rank = rank;
        if (this.rank.equals("regular")){
            discount = 0.1;
        }else{
            if (this.rank.equals("manager")){
                discount = 0.2;
            }
            else{
                discount = 0.3;
            }
        }
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "hello " +firstName+" "+lastName+" "+rank+" !";
    }
}

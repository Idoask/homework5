public class Costumer extends User{
    protected boolean isMember;

    // Costumer const
    public Costumer(String userName, String firstName, String lastName, String password, boolean isMember){
        super(userName, firstName, lastName, password);
        this.isMember = isMember;
    }

    @Override
    public String toString() {
        if (isMember)
            return  firstName  +" "+ lastName+" !";
        else{
            return  firstName  +" "+ lastName+" VIP!";
        }
    }
}

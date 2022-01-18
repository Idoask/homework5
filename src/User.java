public class User {
    protected String userName;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected int numberOfPurchases;
    protected int sumOfPurchases;

    //User const
    public User(String userName, String firstName, String lastName, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.numberOfPurchases = 0;
        this.sumOfPurchases = 0;
    }

    public User(){}

    // setter
    public void setNumberOfPurchases(int numberOfPurchases) {
        this.numberOfPurchases = numberOfPurchases;
    }

    //getter
    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    // getter
    public int getSumOfPurchases() {
        return sumOfPurchases;
    }

    public void setSumOfPurchases(int sumOfPurchases) {
        this.sumOfPurchases = sumOfPurchases;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}


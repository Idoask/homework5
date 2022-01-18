import java.util.Scanner;

public class Store {
    private User[] allUsers;
    private Product[] allProducts;
    private Scanner sc;
    private Cart cart;

    //store constractor
    public Store() {
        this.allUsers = new User[0];
        this.allProducts = new Product[0];
        this.cart = new Cart();
        sc = new Scanner(System.in);
    }

    // the function runs the hall main menu
    public void main(){
        int choose = -1;
        while (choose!=3) {
            System.out.println("for creating new account press 1");
            System.out.println("for connecting to exiting account press 2");
            System.out.println("for exit press 3");
            choose = sc.nextInt();
            while (!isValidChoose(choose)) {
                choose = sc.nextInt();
                System.out.println("please choose number from above list");
            }
            if (choose==1){
                createUser();
            }else{
                if (choose==2){
                    connect();
                }
            }
        }
    }

    // the function checks rather the user choose is valid
    public boolean isValidChoose(int c){
        if (c != 1 && c!=2 && c!=3)
            return false;
        return true;
    }

    // the function connect the user to the account, if user not exist it show a messeage
    public void connect(){
        int isCostumer = costORWorker();
        String userName1 = getInput("please enter your user name");
        String password = getInput("please enter your password");
        User x = (isUserExist(userName1,password,isCostumer));
        if (x == null) {
            System.out.println("user not exist");
            return;
        }
        System.out.println("Hello " +x);
        if (x instanceof Costumer){
            userBuying(x);
        }
        else{
            printOptionsWorker(x);
        }
    }


    // the function shows the secondary menu and direct the user choose to the corelated function
    public void printOptionsWorker(User x){
        int userChoose = -1;
        while (userChoose != 8) {
            System.out.println("choose what you would like to do");
            System.out.println("1 - for printing all costumers");
            System.out.println("2 - for printing all that are in the members club");
            System.out.println("3 - print the costumers that have done at least one purchases");
            System.out.println("4 - print the costumer with maximum amount of buying");
            System.out.println("5 - add new product to store");
            System.out.println("6 - change status of inventory for product");
            System.out.println("7 - buy product");
            System.out.println("8 - disconnect");
            sc = new Scanner(System.in);
            userChoose = sc.nextInt();
            if (!isValidChooseInt(userChoose))
                continue;
            else{
                if (userChoose == 1)
                    printAllCostumers();
                if (userChoose == 2)
                    printMembersCostumers();
                if (userChoose == 3)
                    printMembersCostumersWithOnePurchase();
                if (userChoose == 4)
                    printCostumerWithMaxSum();
                if (userChoose == 5)
                    addProductToStore();
                if (userChoose == 6)
                    changeInventoryStatus();
                if (userChoose == 7)
                    printProductInInventory(x);
                if (userChoose == 8)
                    return;
            }
        }

    }
    // the function checks if the user choose is valid
    public boolean isValidChooseInt(int n){
        if (n!=1 && n!=2 && n!=3 && n!=4 && n!=5 && n!=6 && n!=7 && n!=8){
            return false;
        }
        return true;
    }

    // the function print all costumers
    public void printAllCostumers(){
        for (int i = 0; i < allUsers.length; i++) {
            if (allUsers[i] instanceof Costumer){
                System.out.println(allUsers[i]);
            }
        }
    }

    // the function print all members
    public void printMembersCostumers(){
        for (int i = 0; i < allUsers.length; i++) {
            if (allUsers[i] instanceof Costumer ){
                if (((Costumer) allUsers[i]).isMember)
                    System.out.println(allUsers[i]);
            }
        }
    }

    // the function print all costumers with at least one purchase
    public void printMembersCostumersWithOnePurchase(){
        for (int i = 0; i < allUsers.length; i++) {
            if (allUsers[i].getNumberOfPurchases()>0 ){
                System.out.println(allUsers[i]);
            }
        }
    }

    // the function print the costmer that spent the most money
    public void printCostumerWithMaxSum(){
        int maxValue = -1;
        int maxIndex = 0;
        for (int i = 0; i < allUsers.length; i++) {
            if (allUsers[i] instanceof Costumer )
                if (allUsers[i].getSumOfPurchases()> maxValue ){
                    maxIndex = i;
                }
        }
        System.out.println(allUsers[maxIndex]);
    }
    // the function add product to store
    public void addProductToStore(){
        System.out.println("insert product description");
        sc.nextLine();
        String desc = sc.nextLine();
        System.out.println("insert product price");
        int price = sc.nextInt();
        System.out.println("insert discount precentage");
        int dis = sc.nextInt();
        Product p = new Product(desc,price,dis);
        Product[] newArr = new Product[allProducts.length+1];
        for (int i = 0; i < allProducts.length; i++) {
            newArr[i] = allProducts[i];
        }
        newArr[newArr.length-1] = p;
        this.allProducts = newArr;
    }

    // the function change the status of product
    // if avilable to not avilable
    // if not available to available
    public void changeInventoryStatus(){
        for (int i = 0; i < allProducts.length; i++) {
            System.out.println(allProducts[i]+" product number : "+i);
        }
        int itemID = -2;
        while (itemID!=-1) {
            System.out.println("please choose product number from above list that you want to change it's status");
            itemID = sc.nextInt();
        }
        if (allProducts[itemID].isInInventory()) {
            allProducts[itemID].setInInventory(false);
        }
        else {
            allProducts[itemID].setInInventory(true);
        }
    }

    // the function directs to another function
    public void userBuying(User x){
        printProductInInventory(x);

    }

    // the function manage buying process
    // for all kind of costumers
    public void printProductInInventory(User x){
        boolean flag = false;
        int amount = -1;
        for (int i = 0; i < allProducts.length; i++) {
            if(allProducts[i].isInInventory())
                System.out.println(allProducts[i]+" product number : "+i);
        }
        int itemID = -2;
        while (itemID!=-1) {
            System.out.println("please choose product number or -1 to end");
            itemID = sc.nextInt();
            if (itemID == -1)
                break;
            while (!(isItemExist(itemID))){
                System.out.println("please choose exist product number or -1 to end");
                itemID = sc.nextInt();
            }
            if (itemID != -1) {
                if (!flag) {
                    x.setNumberOfPurchases(x.getSumOfPurchases() + 1);
                    flag = true;
                }
                System.out.println("please enter amount of the product");
                amount = sc.nextInt();
                while (!isAmountInInventory(amount)) {
                    System.out.println("wrong amount. enter positive number and number of product that are avilable in the inventory");
                    amount = sc.nextInt();
                }
                if (x instanceof Worker){
                    cart.addProduct(allProducts[itemID], (int) (amount * ((Worker) x).getDiscount()));
                }
                cart.addProduct(allProducts[itemID], amount);
                System.out.println("product added to cart");
                cart.printCart();
                x.setNumberOfPurchases(x.getSumOfPurchases()+cart.getCurrentPrice());
                System.out.println("current total price: " + cart.getCurrentPrice());


            }
            else{
                break;
            }
        }
        this.cart = new Cart();
    }


    //the function checks if anoubt op product is valid
    // valid = greater than 0
    public boolean isAmountInInventory(int amount){
        if (amount < 0)
            return false;
        return true;
    }

    // checks if item exist in list
    public boolean isItemExist(int id){
        if (id < allProducts.length && allProducts[id].isInInventory())
            return true;
        return false;
    }

    // the function return the User object while trying to connect after registration
    public User isUserExist(String userName1, String password, int type) {
        for (int i = 0; i < allUsers.length; i++) {
            if (type == 1) {
                if (allUsers[i].getUserName().equals(userName1) && allUsers[i].getPassword().equals(password) && allUsers[i] instanceof Costumer) {
                    return allUsers[i];
                }
            } else {
                if (allUsers[i].getUserName().equals(userName1) && allUsers[i].getPassword().equals(password) && allUsers[i] instanceof Worker) {
                    return allUsers[i];
                }
            }
        }
        return null;
    }

    // the function checks rather the user want to connect as worker or costumer
    public int costORWorker(){
        int i = -1;
        while (i != 0 && i !=1) {
            System.out.println("press 1 to connect as costumer and 0 to connect as worker");
            i = sc.nextInt();
        }
        return i;
    }

    // the function is generic and showing a message and getting input correleated to the message
    public String getInput(String msg){
        sc = new Scanner(System.in);
        System.out.println(msg);
        String ans = sc.nextLine();
        return ans;
    }

    // the function get the required data and create a user
    // the function put that user in the users array
    public void createUser(){
        sc = new Scanner(System.in);
        System.out.println("insert first name");
        String firstName;
        firstName = sc.nextLine();
        while (!isContainNumbers(firstName)){
            System.out.println("insert first name without numbers");
            firstName = sc.nextLine();
        }
        System.out.println("insert last name");
        String lastName = sc.nextLine();
        while (!isContainNumbers(lastName)){
            System.out.println("insert last Name without numbers");
            lastName = sc.nextLine();
        }
        System.out.println("insert userNAme name");
        String userNAme = sc.nextLine();
        while (isUserNameUsed(userNAme)){
            System.out.println("user name already exist, enter new one");
            userNAme = sc.nextLine();
        }
        System.out.println("insert password");
        String pass = sc.nextLine();
        while (!isPasswordLong(pass)){
            System.out.println("enter password longer than 5 characters");
            pass = sc.nextLine();
        }
        System.out.println("press 1 for worker and 2 for costumer");
        String ans = sc.nextLine();
        while (!isValidAns(ans)){
            System.out.println("enter valid answer, press 1 for worker and 2 for costumer");
            ans = sc.nextLine();
        }
        if (ans.equals("1")){
            System.out.println("please choose rank 1 - regular, 2 - manager, 3 management member");
            String type = sc.nextLine();
            while (!isValidrnk(type)){
                System.out.println("enter valid answer, press 1 for worker and 2 for costumer and 3 for managment member");
                type = sc.nextLine();
            }
            if (type.equals("1"))
                addUserToArray(new Worker(userNAme,firstName,lastName,pass,"regular"));
            if (type.equals("2"))
                addUserToArray(new Worker(userNAme,firstName,lastName,pass,"manager"));
            if (type.equals("3"))
                addUserToArray(new Worker(userNAme,firstName,lastName,pass,"management member"));
        }
        else{
            boolean isMember;
            System.out.println("are you member? press 1 if yes, press 2 if no");
            String isMem = sc.nextLine();
            while (!isValidAns(isMem)){
                System.out.println("are you member? press 1 if yes, press 2 if no");
                isMem = sc.nextLine();
            }
            if (isMem.equals("1")){
                isMember = true;
            }
            else{
                isMember = false;
            }
            addUserToArray(new Costumer(userNAme,firstName,lastName,pass,isMember));
        }

    }

    // the function checks if a given rank is valid
    public boolean isValidrnk (String s){
        if ((s.equals("1")) || (s.equals("2")) || (s.equals("3"))){
            return true;
        }
        return false;
    }

    // the function checks if given answer is valid
    public boolean isValidAns (String s){
        if (s.equals("1") || s.equals("2")){
            return true;
        }
        return false;
    }

    //the function check if given string contain numbers
    public boolean isContainNumbers(String str){
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    // checks if given user name already exist
    public boolean isUserNameUsed(String str){
        for (int i = 0; i < allUsers.length; i++) {
            if (str.equals(allUsers[i])) {
                return true;
            }
        }
        return false;
    }

    // checks if given str is shoerter than 6 chars
    public boolean isPasswordLong(String str){
        return str.length() >= 6;
    }
    // the function get user and add it to the users array
    public void addUserToArray(User newUser){
        User[] newArr = new User[allUsers.length+1];
        for (int i = 0; i < allUsers.length; i++) {
            newArr[i] = allUsers[i];
        }
        newArr[newArr.length-1] = newUser;
        this.allUsers = newArr;
    }
}

public class Train {
    private String name, product, origin, destination;
    private int weight, miles;

    public Train(String name, String product, String origin, 
                 String destination, int weight, int miles) {
        this.name = name;
        this.product = product;
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.miles = miles;
    } // constructor
    
    public String getName() {
        return name;
    } // getName
    
    public String getProduct() {
        return product;
    } // getProduct
    
    public String getOrigin() {
        return origin;
    } // getOrigin
    
    public String getDestination() {
        return destination;
    } // getDestination
    
    public int getWeight() {
        return weight;
    } // getWeight
    
    public int getMiles() {
        return miles;
    } // getMiles
    
    public void setName(String name) {
        this.name = name;
    } // setName
    
    public void setProduct(String product) {
        this.product = product;
    } // setProduct
    
    public void setOrigin(String origin) {
        this.origin = origin;
    } // setOrigin
    
    public void setDestination(String destination) {
        this.destination = destination;
    } // setDestination
    
    public void setWeight(int weight) {
        this.weight = weight;
    } // setWeight
    
    public void setMiles(int miles) {
        this.miles = miles;
    } // setMiles
    
    public void resetMiles() {
        this.miles = 100;
    } // resetMiles
    
    @Override
    public String toString() {
        return name + " containing " + product;
    } // toString
}

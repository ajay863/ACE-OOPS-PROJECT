import java.util.ArrayList;
import java.util.List;

// Product class to manage product information
class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: Rs." + price;
    }
}

// Customer class to manage customer information
class Customer {
    private String id;
    private String name;
    private ShoppingCart cart;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.cart = new ShoppingCart(this);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}

// ShoppingCart class to manage the shopping cart for a customer
class ShoppingCart {
    private Customer customer;
    private List<Product> products;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Added to cart: " + product.getName());
    }

    public void removeProduct(Product product) {
        if (products.remove(product)) {
            System.out.println("Removed from cart: " + product.getName());
        } else {
            System.out.println("Product not found in cart: " + product.getName());
        }
    }

    public void viewCart() {
        System.out.println("Shopping Cart for " + customer.getName() + ":");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public double checkout() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        products.clear();
        System.out.println("Checked out. Total amount: Rs." + total);
        return total;
    }
}

// Main class to demonstrate the functionality
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        // Create products
        Product product1 = new Product("P001", "Laptop", 75000);
        Product product2 = new Product("P002", "Smartphone", 30000);
        Product product3 = new Product("P003", "Headphones", 2000);

        // Create customer
        Customer customer = new Customer("C001", "Alice");

        // Customer adds products to cart
        customer.getCart().addProduct(product1);
        customer.getCart().addProduct(product2);
        customer.getCart().addProduct(product3);

        // View cart
        customer.getCart().viewCart();

        // Remove a product from cart
        customer.getCart().removeProduct(product2);

        // View cart again
        customer.getCart().viewCart();

        // Checkout
        customer.getCart().checkout();
    }
}

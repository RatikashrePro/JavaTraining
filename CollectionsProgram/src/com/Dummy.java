//package com;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Scanner;
// 
////first need create a base
//interface ICart {
//    void addProduct(Product product, int quantity);
//    void removeProduct(int productId);
//    void updateQuantity(int productId, int newQuantity);
//    BigDecimal getTotalPrice();
//}
// 
// 
////i need to create the product first
//class Product {
//    private int id;
//    private String name = "";
//    private BigDecimal price = null;
// 
//    // defining setters and fail-safes
//    public Product(int id, String name, BigDecimal price) {
//        if (id < 0) throw new IllegalArgumentException("ID Recquired");
//        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name Recquired");
//        if (price == null ) throw new IllegalArgumentException("Price Recquired");
// 
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
// 
//    // defining getters
//    public int getId() {
//        return id;
//    }
// 
//    public String getName() {
//        return name;
//    }
// 
//    public BigDecimal getPrice() {
//        return price;
//    }
//}
// 
//class CartItem {
//    private Product product;
//    private int quantity;
// 
//    public CartItem(Product product, int quantity) {
//        if (quantity < 0)
//            throw new IllegalArgumentException("Quantity cannot be ZERO");
//        this.product = product;
//        this.quantity = quantity;
//    }
// 
//    public Product getProduct() {
//        return product;
//    }
// 
//    public int getQuantity() {
//        return quantity;
//    }
// 
//    public void setQuantity(int newQuantity) {
//        if (newQuantity <= 0)
//            throw new IllegalArgumentException("Quantity cannot be ZERO");
//        quantity = newQuantity;
//    }
// 
//    public BigDecimal getSubtotal() {
//        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
//    }
//}
// 
//class ShoppingCart implements ICart {
//    private List<CartItem> items;
//    public ShoppingCart() {
//        this.items = new ArrayList<>();
//    }
// 
//    public void addProduct(Product product, int quantity) {
//        // Scanner sc = new Scanner(System.in);
//        for (CartItem i : items) {
//            if (i.getProduct().getId() == product.getId()) {
//                i.setQuantity(i.getQuantity() + quantity);
//                return;
//            }
//        }
//        items.add(new CartItem(product, quantity));
//    }
// 
//    public void removeProduct(int productId) {
//        Iterator<CartItem> iterator = items.iterator();
//        while (iterator.hasNext()) {
//            CartItem tmp = iterator.next();
//            if (tmp.getProduct().getId() == productId) {
//                iterator.remove();
//                return;
//            }
//        }
//    }
// 
//    public void updateQuantity(int productId, int newQuantity) {
//        Iterator<CartItem> iterator = items.iterator();
//        if (newQuantity <= 0) {
//            throw new IllegalArgumentException("Quantity must be greater than zero");
//        }
//        while (iterator.hasNext()) {
//            CartItem tmp = iterator.next();
//            if (tmp.getProduct().getId() == productId) {
//                tmp.setQuantity(newQuantity);
//                break;
//            }
//        }
//        throw new IllegalArgumentException("Product with ID " + productId + " not found in cart");
// 
//    }
// 
//    public BigDecimal getTotalPrice() {
//        BigDecimal total = BigDecimal.ZERO;
//        Iterator<CartItem> iterator = items.iterator();
//        while (iterator.hasNext()) {
//            CartItem tmp = iterator.next();
//            total = total.add(tmp.getSubtotal());
//        }
//        return total;
//    }
// 
//    public List<CartItem> getItems() {
//        return items;
//    }
//}
// 
//public class Dummy {
//    public static void menu() {
//        System.out.println("<---MENU--->");
//        System.out.println("|       1. add to cart              |");
//        System.out.println("|       2. remove from cart             |");
//        System.out.println("|       3. update cart              |");
//        System.out.println("|       4. total price of the cart              |");
// 
//    }
// 
//    public static void main(String[] args) {
//        ShoppingCart cart = new ShoppingCart();
//        Scanner sc = new Scanner(System.in);
//        int choice;
//        // ArrayList<ShoppingCart> cart = new ArrayList<>();
//        do {
//            menu();
//            choice = sc.nextInt();
//            if (choice == 1) {
//                System.out.println("Enter ID, Name and Quantity and its price");
//                int id = sc.nextInt();
//                String name = sc.next();
//                int quantity = sc.nextInt();
//                BigDecimal price = sc.nextBigDecimal();
// 
//                Product p = new Product(id, name, price);
// 
//                cart.addProduct(p, quantity);
//                continue;
//            }
//            if (choice == 2) {
//                System.out.println("Enter ID to be removed");
//                int id = sc.nextInt();
//                cart.removeProduct(id);
//                continue;
//            }
//            if (choice == 3) {
//                System.out.println("Enter ID to updated followed by its New Quantity");
//                int id = sc.nextInt();
//                int newQuantity = sc.nextInt();
//                cart.updateQuantity(id, newQuantity);
//            }
//            if (choice == 4) {
//                // System.out.println("Give me your ID: ");
//                // int id = sc.nextInt();
//                System.out.println("Here is your total and your items: ");
//                List<CartItem> temp = new ArrayList<>();
//                temp = cart.getItems();
//                for (int i = 0; i < temp.size(); i++) {
//                    System.out.println(temp.get(i).getProduct().getName() + " x " + temp.get(i).getQuantity());
//                }
//                System.out.println("Total: " + cart.getTotalPrice());
//            }
//        } while (choice != 5);
//        sc.close();
//    }
//}
// 
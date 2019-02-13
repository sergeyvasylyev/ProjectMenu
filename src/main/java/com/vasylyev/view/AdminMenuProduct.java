package com.vasylyev.view;

import com.vasylyev.services.ProductService;
import com.vasylyev.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import static com.vasylyev.view.CommonMethods.InputString;

public class AdminMenuProduct {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ProductService productService = new ProductServiceImpl();

    public void show() throws IOException {
        boolean isRunning = true;
        while(isRunning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createProduct();
                    break;
                case "2":
                    modifyProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    getProductsList();
                    break;
                case "9":
                    isRunning = false;
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    private void createProduct() throws IOException {
        String name = InputString("Input name: ");
        BigDecimal price = new BigDecimal(0);
        try{
            price = new BigDecimal(InputString("Input price: "));
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
            return;
        }
        productService.createProduct(name, price);
    }

    private void modifyProduct()  throws IOException {
        String name = InputString("Input name to find product: ");
        String newName = InputString("Input new name: ");
        productService.modifyProduct(name, newName);
    }

    private void getProductsList(){
        productService.getProductsList();
    }

    private void deleteProduct() throws IOException {
        String name = InputString("Input name to find product: ");
        productService.deleteProduct(name);
    }

    private void showMenu() {
        System.out.println("1. Add product");
        System.out.println("2. Modify product");
        System.out.println("3. Remove product");
        System.out.println("4. List all products");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import negocio.Order;

@ManagedBean(name = "order")
@SessionScoped
public class OrderBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static double totalPrice = 0.0;
    private static double totalProducts = 0.0;

    private static Map<Order, Integer> cartProducts = new HashMap<>();

    private static final ArrayList<Order> orderList
            = new ArrayList<>(Arrays.asList(
                    new Order("1", "Lápis",
                            2.0),
                    new Order("2", "Apontador",
                            5.0),
                    new Order("3", "Borracha",
                            3.0),
                    new Order("4", "Régua",
                            4.0)
            ));

    public ArrayList<Order> getOrderList() {

        return orderList;

    }

    public ArrayList<Order> getCartList() {
        return new ArrayList<>(cartProducts.keySet());
    }

    public String addAction(Order order) {

        for (Order key : cartProducts.keySet()) {
            if (key.equals(order)) {

                cartProducts.put(order, cartProducts.remove(key) + 1);
                totalPrice += order.getPrice();
                totalProducts++;
                return null;
            }
        }

        cartProducts.put(order, 1);

        totalProducts++;
        totalPrice += order.getPrice();
        return null;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalProducts() {
        return totalProducts;
    }

    public int getTotalEspecific(Order order) {

        return cartProducts.get(order);
    }

    public double getMedia() {
        if (totalProducts == 0.0) {
            return 0;
        }
        return totalPrice / totalProducts;
    }

    public String deleteAction(Order order) {

        for (Order key : cartProducts.keySet()) {
            if (key.equals(order)) {
                if (cartProducts.get(key) == 1) {
                    cartProducts.remove(key);
                    totalPrice -= order.getPrice();
                    totalProducts--;
                    return null;
                } else {
                    cartProducts.put(order, cartProducts.remove(order) - 1);
                    totalPrice -= order.getPrice();
                    totalProducts--;
                    return null;
                }
            }
        }
        return null;
    }

}

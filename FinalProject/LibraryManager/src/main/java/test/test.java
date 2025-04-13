/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import controller.AdminController;
import controller.LibrarianController;
import controller.LoginController;
import view.LoginForm;

/**
 *
 * @author adkm2
 */
public class test {
    public static void main(String[] args) {
        LoginController lg = new LoginController();
        lg.LoadLoginForm();
//        AdminController ad = new AdminController();
//        ad.loadAdminForm();
//          LibrarianController lib = new LibrarianController();
//          lib.loadLibrarianForm();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import model.User;


/**
 *
 * @author adkm2
 */
public class SignupForm extends javax.swing.JFrame {

    private LoginController loginController = new LoginController();

    /**
     * Creates new form SSignupForm
     */
    public SignupForm() {
        initComponents();
        this.setTitle("Library Manager");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.getHSBColor(0.2f, 0.05f, 0.95f));
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbSignUp = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        lbConfirm = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        confirmField = new javax.swing.JPasswordField();
        backLogin = new javax.swing.JLabel();
        signupBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbSignUp.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbSignUp.setText("Sign Up");

        lbUsername.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lbUsername.setText("Username");

        lbPassword.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lbPassword.setText("Password");

        lbConfirm.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lbConfirm.setText("Confirm Password");

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        usernameField.setToolTipText("");
        usernameField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameFieldKeyTyped(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });

        confirmField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        confirmField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        confirmField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmFieldKeyPressed(evt);
            }
        });

        backLogin.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        backLogin.setText("Back to Login");
        backLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backLoginMouseClicked(evt);
            }
        });

        signupBtn.setBackground(new java.awt.Color(102, 102, 102));
        signupBtn.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        signupBtn.setForeground(new java.awt.Color(255, 255, 255));
        signupBtn.setText("Sign Up");
        signupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupBtnActionPerformed(evt);
            }
        });
        signupBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                signupBtnKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbUsername)
                                            .addComponent(lbPassword))
                                        .addGap(97, 97, 97))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lbConfirm)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(passwordField)
                                    .addComponent(confirmField)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(backLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(78, 78, 78))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSignUp)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUsername)
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPassword)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(confirmField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbConfirm))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(signupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(backLogin)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void signupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupBtnActionPerformed
        // TODO add your handling code here:
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String rePassword = String.valueOf(confirmField.getPassword());

        if (isExistUser(username)) {
            JOptionPane.showMessageDialog(null, "Username already exist! Please try another username");
        } else if (!validateSignUp(username) || !validatePassword(password, rePassword)) {
            // JOptionPane.showMessageDialog(null, "Username already exist! Please try another username");
        } else if (loginController.register(username, password, false)) {
            JOptionPane.showMessageDialog(null, "SignUp successful!");
            new LoginForm().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "SignUp failed");
        }
    }//GEN-LAST:event_signupBtnActionPerformed

    private void backLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backLoginMouseClicked
        // TODO add your handling code here:
        LoginForm login = new LoginForm();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_backLoginMouseClicked

    private void signupBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_signupBtnKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_signupBtnKeyPressed

    private void usernameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signupBtn.doClick();
        }
    }//GEN-LAST:event_usernameFieldKeyPressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signupBtn.doClick();
        }
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void confirmFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signupBtn.doClick();
        }
    }//GEN-LAST:event_confirmFieldKeyPressed

    private void usernameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyTyped
        // TODO add your handling code here:
        if(passwordField.getText().length() >= 60){
            evt.consume();
        }
    }//GEN-LAST:event_usernameFieldKeyTyped

    public boolean validateSignUp(String username) {
        if (username == null || username.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Please enter your username!");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password, String passwordConfirm) {
        if (password == null || password.isBlank() || passwordConfirm == null || passwordConfirm.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Password must not be blank!!");
            return false;
        }
        if (!(passwordConfirm.equals(password))) {
            JOptionPane.showMessageDialog(rootPane, "Password do not match!!");
            return false;
        }
        return true;
    }

    //DOC DU LIEU TU TEXTFILD
    public User getDangKy() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String passwordConfirm = new String(confirmField.getPassword());

        if (!validateSignUp(username)) {
            return null;
        }
        if (!validatePassword(password, passwordConfirm)) {
            return null;
        }
        return new User(username, password, 0);
    }

    public void addDangKyListener(ActionListener listener) {
        signupBtn.addActionListener(listener);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backLogin;
    private javax.swing.JPasswordField confirmField;
    private javax.swing.JLabel lbConfirm;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbSignUp;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton signupBtn;
    public javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}

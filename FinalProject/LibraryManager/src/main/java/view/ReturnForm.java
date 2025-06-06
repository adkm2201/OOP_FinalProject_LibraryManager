package view;

import controller.BorrowController;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author adkm2
 */
public class ReturnForm extends javax.swing.JFrame {
    private BorrowController borrowController = new BorrowController();
    
    private int userID; // Biến để lưu userID

    public ReturnForm(int userID) {
        this.userID = userID; // Lưu userID
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Borrowed Books");
        borrowController.loadBorrowedBooksToTable(bookTable, userID); // Chỉ hiển thị sách của userID
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookList = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        returnBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        titleTF = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Borrow ID", "Username", "Title", "Borrow Date", "Return Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        bookList.setViewportView(bookTable);
        if (bookTable.getColumnModel().getColumnCount() > 0) {
            bookTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            bookTable.getColumnModel().getColumn(1).setPreferredWidth(40);
            bookTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        returnBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        returnBtn.setText("Return Book");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        titleLabel.setText("Title:");

        backBtn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookList, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(backBtn)
                        .addGap(136, 136, 136)
                        .addComponent(returnBtn))
                    .addComponent(titleLabel)
                    .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(returnBtn)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookList, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        // TODO add your handling code here:
        String title = titleTF.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a book title.");
            return;
        }

        try {
            // Lấy BookID từ tiêu đề sách
            int bookID = borrowController.getBookIDByTitle(title);
            if (bookID == -1) {
                JOptionPane.showMessageDialog(this, "Book not found.");
                return;
            }

            // Lấy ngày hiện tại và returnDate từ cơ sở dữ liệu
            LocalDate currentDate = LocalDate.now();
            java.sql.Date sqlReturnDate = borrowController.getReturnDate(userID, bookID);

            if (sqlReturnDate != null) {
                // Chuyển đổi returnDate từ java.sql.Date sang LocalDate
                LocalDate returnDate = sqlReturnDate.toLocalDate();
                int numBorrow = borrowController.CountReturnedBooks(userID, bookID);
                int numReturned = borrowController.countReturnedBooksByDate(userID, bookID);
                // So sánh ngày
                if (numBorrow == numReturned) {
                    JOptionPane.showMessageDialog(this, "This book has already been returned.");
                    return;
                }
            }

            // Cập nhật returnDate trong bảng BorrowedBooks
            if (borrowController.updateReturnDate(userID, bookID, java.sql.Date.valueOf(currentDate))) {
                // Cộng 1 vào cột Available trong bảng Books
                borrowController.incrementBookAvailability(bookID);
                JOptionPane.showMessageDialog(this, "Book returned successfully!");
                borrowController.loadBorrowedBooksToTable(bookTable, userID); // Tải lại bảng
            } else {
                JOptionPane.showMessageDialog(this, "Failed to return the book.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_returnBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JScrollPane bookList;
    private javax.swing.JTable bookTable;
    private javax.swing.JButton returnBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTF;
    // End of variables declaration//GEN-END:variables
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankGUI extends JFrame implements ActionListener {

    Bank bank = new Bank();

    JTextField accNoField, nameField, amountField;
    JTextArea outputArea;

    JButton createBtn, depositBtn, withdrawBtn, balanceBtn;

    public BankGUI() {

        setTitle("Bank Management System");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // -------- INPUT PANEL --------
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Account Details"));

        inputPanel.add(new JLabel("Account Number:"));
        accNoField = new JTextField();
        inputPanel.add(accNoField);

        inputPanel.add(new JLabel("Account Holder Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Amount :"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        createBtn = new JButton("Create Account");
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        balanceBtn = new JButton("Check Balance");

        inputPanel.add(createBtn);
        inputPanel.add(depositBtn);
        inputPanel.add(withdrawBtn);
        inputPanel.add(balanceBtn);

        add(inputPanel, BorderLayout.NORTH);

        // -------- OUTPUT PANEL --------
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));

        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // -------- BUTTON ACTIONS --------
        createBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceBtn.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int accNo;
        double amount;

        try {
            accNo = Integer.parseInt(accNoField.getText());
        } catch (Exception ex) {
            outputArea.setText("Please enter a valid Account Number.");
            return;
        }

        if (e.getSource() == createBtn) {
            String name = nameField.getText();

            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (Exception ex) {
                outputArea.setText("Please enter a valid amount.");
                return;
            }

            bank.createAccount(accNo, name, amount);
            outputArea.setText("Account created successfully.");

        } else if (e.getSource() == depositBtn) {

            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (Exception ex) {
                outputArea.setText("Please enter a valid amount.");
                return;
            }

            bank.deposit(accNo, amount);
            outputArea.setText("Amount deposited successfully.");

        } else if (e.getSource() == withdrawBtn) {

            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (Exception ex) {
                outputArea.setText("Please enter a valid amount.");
                return;
            }

            bank.withdraw(accNo, amount);
            outputArea.setText("Withdrawal processed.");

        } else if (e.getSource() == balanceBtn) {

            Account acc = bank.getAccount(accNo);
            if (acc != null) {
                outputArea.setText(
                        "Account Holder: " + acc.getAccountHolderName() +
                        "\nBalance: " + acc.getBalance()
                );
            } else {
                outputArea.setText("Account not found.");
            }
        }
    }

    public static void main(String[] args) {
        new BankGUI();
    }
}

//package app.ui;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
//
//// Removed database-related imports as requested
//// import app.db.UserDAO;
//// import app.model.User;
//
//public class LoginPage extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//    private JPanel contentPane;
//    private CardLayout cardLayout;
//    private JPanel cardPanel; // Panel that holds login and signup forms
//
//    private JTextField loginEmailField;
//    private JPasswordField loginPasswordField;
//    private JTextField signupNameField;
//    private JTextField signupEmailField;
//    private JPasswordField signupPasswordField;
//    private JPasswordField signupConfirmPasswordField;
//
//    // Removed UserDAO instance as we are not using database for now
//    // private UserDAO userDAO;
//
//    /**
//     * Launch the application. (For standalone testing of LoginPage)
//     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    LoginPage frame = new LoginPage();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    /**
//     * Create the frame.
//     */
//    public LoginPage() {
//        setTitle("Welcome - Login / Sign Up");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to avoid exiting main app
//        setSize(450, 600); // Increased size for better layout
//        setMinimumSize(new Dimension(400, 550)); // Added minimum size for better responsiveness
//        setLocationRelativeTo(null); // Center on screen
//
//        // Removed UserDAO initialization as we are not using database for now
//        // userDAO = new UserDAO();
//
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//        contentPane.setLayout(new BorderLayout(0, 0));
//        setContentPane(contentPane);
//
//        // Header Panel
//        JPanel headerPanel = new JPanel();
//        headerPanel.setBackground(new Color(234, 88, 12)); // Orange background
//        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
//        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center header text
//        contentPane.add(headerPanel, BorderLayout.NORTH);
//
//        JLabel headerLabel = new JLabel("RecipeManager Account");
//        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
//        headerLabel.setForeground(Color.WHITE);
//        headerPanel.add(headerLabel);
//
//        // Card Panel to switch between Login and Sign Up forms
//        cardLayout = new CardLayout();
//        cardPanel = new JPanel(cardLayout);
//        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding inside card panel
//        cardPanel.setBackground(new Color(248, 250, 252)); // Light gray background
//        contentPane.add(cardPanel, BorderLayout.CENTER);
//
//        // --- Login Form Panel ---
//        JPanel loginFormPanel = new JPanel();
//        loginFormPanel.setLayout(new BoxLayout(loginFormPanel, BoxLayout.Y_AXIS));
//        loginFormPanel.setBackground(new Color(255, 255, 255)); // White background for the form
//        loginFormPanel.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//            new EmptyBorder(20, 20, 20, 20)
//        ));
//        loginFormPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel loginTitle = new JLabel("Login to Your Account", SwingConstants.CENTER);
//        loginTitle.setFont(new Font("Arial", Font.BOLD, 22));
//        loginTitle.setForeground(new Color(51, 51, 51));
//        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loginFormPanel.add(loginTitle);
//        loginFormPanel.add(Box.createVerticalStrut(20));
//
//        loginEmailField = createStyledTextField("Email Address");
//        loginFormPanel.add(loginEmailField);
//        loginFormPanel.add(Box.createVerticalStrut(15));
//
//        loginPasswordField = createStyledPasswordField("Password");
//        loginFormPanel.add(loginPasswordField);
//        loginFormPanel.add(Box.createVerticalStrut(25));
//
//        JButton loginButton = new JButton("Login");
//        styleButton(loginButton, new Color(234, 88, 12), Color.BLACK);
//        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
//        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loginFormPanel.add(loginButton);
//        loginFormPanel.add(Box.createVerticalStrut(15));
//
//        JLabel noAccountLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
//        noAccountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
//        noAccountLabel.setForeground(new Color(102, 102, 102));
//        noAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loginFormPanel.add(noAccountLabel);
//
//        JButton switchToSignupButton = new JButton("Sign Up Here");
//        styleLinkButton(switchToSignupButton, new Color(234, 88, 12));
//        switchToSignupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loginFormPanel.add(switchToSignupButton);
//
//        cardPanel.add(loginFormPanel, "Login"); // Add login panel to card layout
//
//        // --- Sign Up Form Panel ---
//        JPanel signupFormPanel = new JPanel();
//        signupFormPanel.setLayout(new BoxLayout(signupFormPanel, BoxLayout.Y_AXIS));
//        signupFormPanel.setBackground(new Color(255, 255, 255));
//        signupFormPanel.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//            new EmptyBorder(20, 20, 20, 20)
//        ));
//        signupFormPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        JLabel signupTitle = new JLabel("Create Your Account", SwingConstants.CENTER);
//        signupTitle.setFont(new Font("Arial", Font.BOLD, 22));
//        signupTitle.setForeground(new Color(51, 51, 51));
//        signupTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        signupFormPanel.add(signupTitle);
//        signupFormPanel.add(Box.createVerticalStrut(20));
//
//        signupNameField = createStyledTextField("Full Name");
//        signupFormPanel.add(signupNameField);
//        signupFormPanel.add(Box.createVerticalStrut(15));
//
//        signupEmailField = createStyledTextField("Email Address");
//        signupFormPanel.add(signupEmailField);
//        signupFormPanel.add(Box.createVerticalStrut(15));
//
//        signupPasswordField = createStyledPasswordField("Password");
//        signupFormPanel.add(signupPasswordField);
//        signupFormPanel.add(Box.createVerticalStrut(15));
//
//        signupConfirmPasswordField = createStyledPasswordField("Confirm Password");
//        signupFormPanel.add(signupConfirmPasswordField);
//        signupFormPanel.add(Box.createVerticalStrut(25));
//
//        JButton signupButton = new JButton("Sign Up");
//        styleButton(signupButton, new Color(234, 88, 12), Color.BLACK);
//        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
//        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        signupFormPanel.add(signupButton);
//        signupFormPanel.add(Box.createVerticalStrut(15));
//
//        JLabel alreadyAccountLabel = new JLabel("Already have an account?", SwingConstants.CENTER);
//        alreadyAccountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
//        alreadyAccountLabel.setForeground(new Color(102, 102, 102));
//        alreadyAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        signupFormPanel.add(alreadyAccountLabel);
//
//        JButton switchToLoginButton = new JButton("Login Here");
//        styleLinkButton(switchToLoginButton, new Color(234, 88, 12));
//        switchToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        signupFormPanel.add(switchToLoginButton);
//
//        cardPanel.add(signupFormPanel, "SignUp"); // Add signup panel to card layout
//
//        // Action Listeners for switching panels
//        switchToSignupButton.addActionListener(e -> cardLayout.show(cardPanel, "SignUp"));
//        switchToLoginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
//
//        // Action listeners for form submission
//        loginButton.addActionListener(e -> performLogin());
//        signupButton.addActionListener(e -> performSignup());
//
//        // Initially show the login form
//        cardLayout.show(cardPanel, "Login");
//    }
//
//    /**
//     * Public method to switch the displayed panel to the Sign Up form.
//     */
//    public void showSignUpPanel() {
//        cardLayout.show(cardPanel, "SignUp");
//    }
//
//
//    // --- Helper Methods for Styling and Components ---
//
//    private JTextField createStyledTextField(String placeholder) {
//        JTextField field = new JTextField(20); // Preferred column size hint
//        // Ensure horizontal stretch within BoxLayout
//        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
//        field.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//            new EmptyBorder(8, 10, 8, 10) // Internal padding
//        ));
//        field.setFont(new Font("Arial", Font.PLAIN, 14));
//        field.setForeground(Color.GRAY);
//        field.setText(placeholder);
//
//        field.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (field.getText().equals(placeholder)) {
//                    field.setText("");
//                    field.setForeground(Color.BLACK);
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (field.getText().isEmpty()) {
//                    field.setText(placeholder);
//                    field.setForeground(Color.GRAY);
//                }
//            }
//        });
//        return field;
//    }
//
//    private JPasswordField createStyledPasswordField(String placeholder) {
//        JPasswordField field = new JPasswordField(20);
//        // Ensure horizontal stretch within BoxLayout
//        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
//        field.setBorder(BorderFactory.createCompoundBorder(
//            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//            new EmptyBorder(8, 10, 8, 10)
//        ));
//        field.setFont(new Font("Arial", Font.PLAIN, 14));
//        field.setForeground(Color.GRAY);
//        field.setEchoChar((char) 0); // Show placeholder text initially
//        field.setText(placeholder);
//
//        field.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (new String(field.getPassword()).equals(placeholder)) {
//                    field.setText("");
//                    field.setEchoChar('•'); // Mask characters
//                    field.setForeground(Color.BLACK);
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (new String(field.getPassword()).isEmpty()) {
//                    field.setEchoChar((char) 0); // Show placeholder again
//                    field.setText(placeholder);
//                    field.setForeground(Color.GRAY);
//                }
//            }
//        });
//        return field;
//    }
//
//    private void styleButton(JButton button, Color bgColor, Color fgColor) {
//        button.setBackground(bgColor);
//        button.setForeground(fgColor);
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
//        button.setFont(new Font("Arial", Font.BOLD, 14));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align for buttons
//    }
//
//    private void styleLinkButton(JButton button, Color fgColor) {
//        button.setBackground(null);
//        button.setForeground(fgColor);
//        button.setFocusPainted(false);
//        button.setBorderPainted(false);
//        button.setContentAreaFilled(false);
//        button.setFont(new Font("Arial", Font.BOLD, 12));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button.setForeground(fgColor.brighter()); // Lighter color on hover
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button.setForeground(fgColor);
//            }
//        });
//    }
//
//    // --- Form Submission Logic (Placeholder - Frontend Only) ---
//
//    private void performLogin() {
//        String email = loginEmailField.getText();
//        String password = new String(loginPasswordField.getPassword());
//
//        if (email.isEmpty() || email.equals("Email Address") || password.isEmpty() || password.equals("Password")) {
//            JOptionPane.showMessageDialog(this, "Please enter both email and password.", "Login Error", JOptionPane.WARNING_MESSAGE);
//        } else if (email.equals("test@example.com") && password.equals("password")) { // Simple hardcoded check
//            JOptionPane.showMessageDialog(this, "Login successful for " + email + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
//            this.dispose(); // Close login page
//            // In a real app, you might pass login status back to RecipeLandingPage
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid email or password. (Hint: test@example.com / password)", "Login Failed", JOptionPane.WARNING_MESSAGE);
//        }
//    }
//
//    private void performSignup() {
//        String name = signupNameField.getText();
//        String email = signupEmailField.getText();
//        String password = new String(signupPasswordField.getPassword());
//        String confirmPassword = new String(signupConfirmPasswordField.getPassword());
//
//        if (name.isEmpty() || name.equals("Full Name") || email.isEmpty() || email.equals("Email Address") ||
//            password.isEmpty() || password.equals("Password") || confirmPassword.isEmpty() || confirmPassword.equals("Confirm Password")) {
//            JOptionPane.showMessageDialog(this, "All fields are required.", "Sign Up Error", JOptionPane.WARNING_MESSAGE);
//        } else if (!password.equals(confirmPassword)) {
//            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Sign Up Error", JOptionPane.WARNING_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, "Account creation simulated for: " + email + ". Please login.", "Sign Up Info", JOptionPane.INFORMATION_MESSAGE);
//            // After successful simulated signup, switch to login form
//            cardLayout.show(cardPanel, "Login");
//            loginEmailField.setText(email); // Pre-fill email for login
//            loginPasswordField.setText(""); // Clear password field
//            loginPasswordField.setEchoChar((char) 0); // Show placeholder initially
//            loginPasswordField.setForeground(Color.GRAY);
//        }
//    }
//}

// src/app/ui/LoginPage.java
package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField loginEmailField, signupNameField, signupEmailField;
    private JPasswordField loginPasswordField, signupPasswordField, signupConfirmPasswordField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }

    public LoginPage() {
        setTitle("Welcome - Login / Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 600);
        setMinimumSize(new Dimension(400, 550));
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(contentPane);

        // Header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setBackground(new Color(234,88,12));
        header.setBorder(new EmptyBorder(20,0,20,0));
        JLabel title = new JLabel("RecipeManager Account");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        header.add(title);
        contentPane.add(header, BorderLayout.NORTH);

        // Card
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(new EmptyBorder(20,20,20,20));
        cardPanel.setBackground(new Color(248,250,252));
        contentPane.add(cardPanel, BorderLayout.CENTER);

        cardPanel.add(buildLoginPanel(), "Login");
        cardPanel.add(buildSignupPanel(), "SignUp");
        cardLayout.show(cardPanel, "Login");
    }

    private JPanel buildLoginPanel() {
        JPanel p = styledFormPanel();
        JLabel lbl = new JLabel("Login to Your Account", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setForeground(new Color(51,51,51));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(lbl); p.add(Box.createVerticalStrut(20));

        loginEmailField   = createStyledTextField("Email Address");
        loginPasswordField= createStyledPasswordField("Password");
        p.add(loginEmailField); p.add(Box.createVerticalStrut(15));
        p.add(loginPasswordField); p.add(Box.createVerticalStrut(25));

        JButton loginBtn = new JButton("Login");
        styleButton(loginBtn);
        loginBtn.addActionListener(e -> performLogin());
        p.add(loginBtn); p.add(Box.createVerticalStrut(15));

        p.add(new JLabel("Don't have an account?", SwingConstants.CENTER));
        JButton toSignup = new JButton("Sign Up Here");
        styleLinkButton(toSignup);
        toSignup.addActionListener(e -> cardLayout.show(cardPanel, "SignUp"));
        p.add(toSignup);

        return p;
    }

    private JPanel buildSignupPanel() {
        JPanel p = styledFormPanel();
        JLabel lbl = new JLabel("Create Your Account", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 22));
        lbl.setForeground(new Color(51,51,51));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(lbl); p.add(Box.createVerticalStrut(20));

        signupNameField     = createStyledTextField("Full Name");
        signupEmailField    = createStyledTextField("Email Address");
        signupPasswordField = createStyledPasswordField("Password");
        signupConfirmPasswordField = createStyledPasswordField("Confirm Password");

        p.add(signupNameField); p.add(Box.createVerticalStrut(15));
        p.add(signupEmailField); p.add(Box.createVerticalStrut(15));
        p.add(signupPasswordField); p.add(Box.createVerticalStrut(15));
        p.add(signupConfirmPasswordField); p.add(Box.createVerticalStrut(25));

        JButton signupBtn = new JButton("Sign Up");
        styleButton(signupBtn);
        signupBtn.addActionListener(e -> performSignup());
        p.add(signupBtn); p.add(Box.createVerticalStrut(15));

        p.add(new JLabel("Already have an account?", SwingConstants.CENTER));
        JButton toLogin = new JButton("Login Here");
        styleLinkButton(toLogin);
        toLogin.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
        p.add(toLogin);

        return p;
    }

    private JPanel styledFormPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
            new EmptyBorder(20,20,20,20)
        ));
        return p;
    }

    private void performLogin() {
        String email = loginEmailField.getText().trim();
        String pass  = new String(loginPasswordField.getPassword());
        if(email.isEmpty()||pass.isEmpty()||email.equals("Email Address")||pass.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.","Login Error",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (Connection c = DBConnector.getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                "SELECT full_name,password_hash FROM users WHERE email=?"
            );
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next() && rs.getString("password_hash").equals(PasswordUtil.hashPassword(pass))) {
                JOptionPane.showMessageDialog(this, "Welcome, "+rs.getString("full_name")+"!","Login Success",JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.","Login Failed",JOptionPane.WARNING_MESSAGE);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"DB error: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performSignup() {
        String name  = signupNameField.getText().trim();
        String email = signupEmailField.getText().trim();
        String p1    = new String(signupPasswordField.getPassword());
        String p2    = new String(signupConfirmPasswordField.getPassword());
        if(name.isEmpty()||email.isEmpty()||p1.isEmpty()||p2.isEmpty()||
           name.equals("Full Name")||email.equals("Email Address")||
           p1.equals("Password")||p2.equals("Confirm Password")) {
            JOptionPane.showMessageDialog(this,"All fields are required.","Sign Up Error",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(!p1.equals(p2)) {
            JOptionPane.showMessageDialog(this,"Passwords do not match.","Sign Up Error",JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (Connection c = DBConnector.getConnection()) {
            PreparedStatement chk = c.prepareStatement("SELECT 1 FROM users WHERE email=?");
            chk.setString(1,email);
            if(chk.executeQuery().next()) {
                JOptionPane.showMessageDialog(this,"Email already in use.","Sign Up Error",JOptionPane.WARNING_MESSAGE);
                return;
            }
            PreparedStatement ins = c.prepareStatement(
                "INSERT INTO users(full_name,email,password_hash,created_at) VALUES(?,?,?,NOW())"
            );
            ins.setString(1,name);
            ins.setString(2,email);
            ins.setString(3,PasswordUtil.hashPassword(p1));
            ins.executeUpdate();

            JOptionPane.showMessageDialog(this,"Account created! Please log in.","Success",JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(cardPanel,"Login");
            loginEmailField.setText(email);
            loginPasswordField.setText("");
            loginPasswordField.setEchoChar((char)0);
            loginPasswordField.setForeground(Color.GRAY);

        } catch(SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Sign up error: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField f = new JTextField(20);
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE,f.getPreferredSize().height));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
            new EmptyBorder(8,10,8,10)
        ));
        f.setFont(new Font("Arial",Font.PLAIN,14));
        f.setForeground(Color.GRAY);
        f.setText(placeholder);
        f.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(f.getText().equals(placeholder)) { f.setText(""); f.setForeground(Color.BLACK); }
            }
            public void focusLost(FocusEvent e) {
                if(f.getText().isEmpty()) { f.setText(placeholder); f.setForeground(Color.GRAY); }
            }
        });
        return f;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField f = new JPasswordField(20);
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE,f.getPreferredSize().height));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
            new EmptyBorder(8,10,8,10)
        ));
        f.setFont(new Font("Arial",Font.PLAIN,14));
        f.setForeground(Color.GRAY);
        f.setEchoChar((char)0);
        f.setText(placeholder);
        f.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if(new String(f.getPassword()).equals(placeholder)) {
                    f.setText(""); f.setEchoChar('•'); f.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if(f.getPassword().length==0) {
                    f.setEchoChar((char)0); f.setText(placeholder); f.setForeground(Color.GRAY);
                }
            }
        });
        return f;
    }

    private void styleButton(JButton b) {
        b.setBackground(new Color(234,88,12));
        b.setForeground(Color.BLACK);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void styleLinkButton(JButton b) {
        b.setBackground(null);
        b.setForeground(new Color(234,88,12));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFont(new Font("Arial",Font.BOLD,12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { b.setForeground(b.getForeground().brighter()); }
            public void mouseExited(java.awt.event.MouseEvent e) { b.setForeground(new Color(234,88,12)); }
        });
    }
}

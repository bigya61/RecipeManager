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
//        setLocationRelativeTo(null); // Center on screen
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
//    // --- Helper Methods for Styling and Components ---
//
//    private JTextField createStyledTextField(String placeholder) {
//        JTextField field = new JTextField(20); // Preferred column size hint
//        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height)); // Allow horizontal stretch
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
//    // --- Form Submission Logic (Placeholder) ---
//
//    private void performLogin() {
//        String email = loginEmailField.getText();
//        String password = new String(loginPasswordField.getPassword());
//
//        if (email.isEmpty() || email.equals("Email Address") || password.isEmpty() || password.equals("Password")) {
//            JOptionPane.showMessageDialog(this, "Please enter both email and password.", "Login Error", JOptionPane.WARNING_MESSAGE);
//        } else {
//            // In a real application, you would validate credentials against a database
//            JOptionPane.showMessageDialog(this, "Attempting to log in with: " + email, "Login Info", JOptionPane.INFORMATION_MESSAGE);
//            // If login successful:
//            // this.dispose(); // Close login page
//            // new MainApplicationFrame().setVisible(true); // Open main app frame
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
//            // In a real application, you would create a new user in your database
//            JOptionPane.showMessageDialog(this, "Attempting to sign up new user: " + name + " with email: " + email, "Sign Up Info", JOptionPane.INFORMATION_MESSAGE);
//            // If signup successful:
//            // this.dispose(); // Close signup page
//            // new MainApplicationFrame().setVisible(true); // Open main app frame
//        }
//    }
//}
package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// Removed database-related imports as requested
// import app.db.UserDAO;
// import app.model.User;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JPanel cardPanel; // Panel that holds login and signup forms

    private JTextField loginEmailField;
    private JPasswordField loginPasswordField;
    private JTextField signupNameField;
    private JTextField signupEmailField;
    private JPasswordField signupPasswordField;
    private JPasswordField signupConfirmPasswordField;

    // Removed UserDAO instance as we are not using database for now
    // private UserDAO userDAO;

    /**
     * Launch the application. (For standalone testing of LoginPage)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginPage() {
        setTitle("Welcome - Login / Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to avoid exiting main app
        setSize(450, 600); // Increased size for better layout
        setMinimumSize(new Dimension(400, 550)); // Added minimum size for better responsiveness
        setLocationRelativeTo(null); // Center on screen

        // Removed UserDAO initialization as we are not using database for now
        // userDAO = new UserDAO();

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(234, 88, 12)); // Orange background
        headerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center header text
        contentPane.add(headerPanel, BorderLayout.NORTH);

        JLabel headerLabel = new JLabel("RecipeHub Account");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Card Panel to switch between Login and Sign Up forms
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding inside card panel
        cardPanel.setBackground(new Color(248, 250, 252)); // Light gray background
        contentPane.add(cardPanel, BorderLayout.CENTER);

        // --- Login Form Panel ---
        JPanel loginFormPanel = new JPanel();
        loginFormPanel.setLayout(new BoxLayout(loginFormPanel, BoxLayout.Y_AXIS));
        loginFormPanel.setBackground(new Color(255, 255, 255)); // White background for the form
        loginFormPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        loginFormPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginTitle = new JLabel("Login to Your Account", SwingConstants.CENTER);
        loginTitle.setFont(new Font("Arial", Font.BOLD, 22));
        loginTitle.setForeground(new Color(51, 51, 51));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginFormPanel.add(loginTitle);
        loginFormPanel.add(Box.createVerticalStrut(20));

        loginEmailField = createStyledTextField("Email Address");
        loginFormPanel.add(loginEmailField);
        loginFormPanel.add(Box.createVerticalStrut(15));

        loginPasswordField = createStyledPasswordField("Password");
        loginFormPanel.add(loginPasswordField);
        loginFormPanel.add(Box.createVerticalStrut(25));

        JButton loginButton = new JButton("Login");
        styleButton(loginButton, new Color(234, 88, 12), Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginFormPanel.add(loginButton);
        loginFormPanel.add(Box.createVerticalStrut(15));

        JLabel noAccountLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
        noAccountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        noAccountLabel.setForeground(new Color(102, 102, 102));
        noAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginFormPanel.add(noAccountLabel);

        JButton switchToSignupButton = new JButton("Sign Up Here");
        styleLinkButton(switchToSignupButton, new Color(234, 88, 12));
        switchToSignupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginFormPanel.add(switchToSignupButton);

        cardPanel.add(loginFormPanel, "Login"); // Add login panel to card layout

        // --- Sign Up Form Panel ---
        JPanel signupFormPanel = new JPanel();
        signupFormPanel.setLayout(new BoxLayout(signupFormPanel, BoxLayout.Y_AXIS));
        signupFormPanel.setBackground(new Color(255, 255, 255));
        signupFormPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        signupFormPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel signupTitle = new JLabel("Create Your Account", SwingConstants.CENTER);
        signupTitle.setFont(new Font("Arial", Font.BOLD, 22));
        signupTitle.setForeground(new Color(51, 51, 51));
        signupTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupFormPanel.add(signupTitle);
        signupFormPanel.add(Box.createVerticalStrut(20));

        signupNameField = createStyledTextField("Full Name");
        signupFormPanel.add(signupNameField);
        signupFormPanel.add(Box.createVerticalStrut(15));

        signupEmailField = createStyledTextField("Email Address");
        signupFormPanel.add(signupEmailField);
        signupFormPanel.add(Box.createVerticalStrut(15));

        signupPasswordField = createStyledPasswordField("Password");
        signupFormPanel.add(signupPasswordField);
        signupFormPanel.add(Box.createVerticalStrut(15));

        signupConfirmPasswordField = createStyledPasswordField("Confirm Password");
        signupFormPanel.add(signupConfirmPasswordField);
        signupFormPanel.add(Box.createVerticalStrut(25));

        JButton signupButton = new JButton("Sign Up");
        styleButton(signupButton, new Color(234, 88, 12), Color.BLACK);
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupFormPanel.add(signupButton);
        signupFormPanel.add(Box.createVerticalStrut(15));

        JLabel alreadyAccountLabel = new JLabel("Already have an account?", SwingConstants.CENTER);
        alreadyAccountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        alreadyAccountLabel.setForeground(new Color(102, 102, 102));
        alreadyAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupFormPanel.add(alreadyAccountLabel);

        JButton switchToLoginButton = new JButton("Login Here");
        styleLinkButton(switchToLoginButton, new Color(234, 88, 12));
        switchToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupFormPanel.add(switchToLoginButton);

        cardPanel.add(signupFormPanel, "SignUp"); // Add signup panel to card layout

        // Action Listeners for switching panels
        switchToSignupButton.addActionListener(e -> cardLayout.show(cardPanel, "SignUp"));
        switchToLoginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        // Action listeners for form submission
        loginButton.addActionListener(e -> performLogin());
        signupButton.addActionListener(e -> performSignup());

        // Initially show the login form
        cardLayout.show(cardPanel, "Login");
    }

    /**
     * Public method to switch the displayed panel to the Sign Up form.
     */
    public void showSignUpPanel() {
        cardLayout.show(cardPanel, "SignUp");
    }


    // --- Helper Methods for Styling and Components ---

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(20); // Preferred column size hint
        // Ensure horizontal stretch within BoxLayout
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(8, 10, 8, 10) // Internal padding
        ));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField field = new JPasswordField(20);
        // Ensure horizontal stretch within BoxLayout
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, field.getPreferredSize().height));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0); // Show placeholder text initially
        field.setText(placeholder);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('•'); // Mask characters
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(field.getPassword()).isEmpty()) {
                    field.setEchoChar((char) 0); // Show placeholder again
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
        return field;
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align for buttons
    }

    private void styleLinkButton(JButton button, Color fgColor) {
        button.setBackground(null);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(fgColor.brighter()); // Lighter color on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(fgColor);
            }
        });
    }

    // --- Form Submission Logic (Placeholder - Frontend Only) ---

    private void performLogin() {
        String email = loginEmailField.getText();
        String password = new String(loginPasswordField.getPassword());

        if (email.isEmpty() || email.equals("Email Address") || password.isEmpty() || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.", "Login Error", JOptionPane.WARNING_MESSAGE);
        } else if (email.equals("test@example.com") && password.equals("password")) { // Simple hardcoded check
            JOptionPane.showMessageDialog(this, "Login successful for " + email + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Close login page
            // In a real app, you might pass login status back to RecipeLandingPage
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password. (Hint: test@example.com / password)", "Login Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void performSignup() {
        String name = signupNameField.getText();
        String email = signupEmailField.getText();
        String password = new String(signupPasswordField.getPassword());
        String confirmPassword = new String(signupConfirmPasswordField.getPassword());

        if (name.isEmpty() || name.equals("Full Name") || email.isEmpty() || email.equals("Email Address") ||
            password.isEmpty() || password.equals("Password") || confirmPassword.isEmpty() || confirmPassword.equals("Confirm Password")) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Sign Up Error", JOptionPane.WARNING_MESSAGE);
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Sign Up Error", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Account creation simulated for: " + email + ". Please login.", "Sign Up Info", JOptionPane.INFORMATION_MESSAGE);
            // After successful simulated signup, switch to login form
            cardLayout.show(cardPanel, "Login");
            loginEmailField.setText(email); // Pre-fill email for login
            loginPasswordField.setText(""); // Clear password field
            loginPasswordField.setEchoChar((char) 0); // Show placeholder initially
            loginPasswordField.setForeground(Color.GRAY);
        }
    }
}

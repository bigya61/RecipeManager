
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

/**
 * LoginPage shows Login / Sign Up forms.
 * If created with an AuthListener, it will invoke onLoginSuccess(fullName)
 * immediately when a login succeeds.
 */
public class LoginPage extends JFrame {
    private static final long serialVersionUID = 1L;

    // --- FOR AUTH CALLBACK ---
    private final AuthListener authListener;

    // --- UI PANELS & LAYOUT ---
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // --- LOGIN FIELDS ---
    private JTextField loginEmailField;
    private JPasswordField loginPasswordField;

    // --- SIGNUP FIELDS ---
    private JTextField signupNameField;
    private JTextField signupEmailField;
    private JPasswordField signupPasswordField;
    private JPasswordField signupConfirmPasswordField;

    /** Standalone constructor (no callback) */
    public LoginPage() {
        this(null);
    }

    /**
     * @param authListener if non-null, called with fullName on successful login.
     */
    public LoginPage(AuthListener authListener) {
        this.authListener = authListener;
        initUI();
    }

    private void initUI() {
        setTitle("Welcome - Login / Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 600);
        setMinimumSize(new Dimension(400, 550));
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        setContentPane(contentPane);

        // --- HEADER ---
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        header.setBackground(new Color(234,88,12));
        header.setBorder(new EmptyBorder(20,0,20,0));
        JLabel headerLabel = new JLabel("RecipeManager Account");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(Color.WHITE);
        header.add(headerLabel);
        contentPane.add(header, BorderLayout.NORTH);

        // --- CARD PANEL ---
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(new EmptyBorder(20,20,20,20));
        cardPanel.setBackground(new Color(248,250,252));
        contentPane.add(cardPanel, BorderLayout.CENTER);

        // Add login & signup cards
        cardPanel.add(buildLoginForm(),  "Login");
        cardPanel.add(buildSignupForm(), "SignUp");

        // Show login by default
        cardLayout.show(cardPanel, "Login");
    }

    private JPanel buildLoginForm() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
            new EmptyBorder(20,20,20,20)
        ));

        JLabel title = new JLabel("Login to Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(51,51,51));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(Box.createVerticalStrut(20));

        loginEmailField    = createStyledTextField("Email Address");
        loginPasswordField = createStyledPasswordField("Password");
        p.add(loginEmailField);
        p.add(Box.createVerticalStrut(15));
        p.add(loginPasswordField);
        p.add(Box.createVerticalStrut(25));

        JButton loginBtn = new JButton("Login");
        styleButton(loginBtn, new Color(234,88,12), Color.BLACK);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.addActionListener(e -> performLogin());
        p.add(loginBtn);
        p.add(Box.createVerticalStrut(15));

        JLabel noAcc = new JLabel("Don't have an account?", SwingConstants.CENTER);
        noAcc.setFont(new Font("Arial", Font.PLAIN, 12));
        noAcc.setForeground(new Color(102,102,102));
        noAcc.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(noAcc);

        JButton toSignup = new JButton("Sign Up Here");
        styleLinkButton(toSignup, new Color(234,88,12));
        toSignup.setAlignmentX(Component.CENTER_ALIGNMENT);
        toSignup.addActionListener(e -> showSignUpPanel());
        p.add(toSignup);

        return p;
    }

    private JPanel buildSignupForm() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
            new EmptyBorder(20,20,20,20)
        ));

        JLabel title = new JLabel("Create Your Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(51,51,51));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(title);
        p.add(Box.createVerticalStrut(20));

        signupNameField            = createStyledTextField("Full Name");
        signupEmailField           = createStyledTextField("Email Address");
        signupPasswordField        = createStyledPasswordField("Password");
        signupConfirmPasswordField = createStyledPasswordField("Confirm Password");

        p.add(signupNameField); p.add(Box.createVerticalStrut(15));
        p.add(signupEmailField); p.add(Box.createVerticalStrut(15));
        p.add(signupPasswordField); p.add(Box.createVerticalStrut(15));
        p.add(signupConfirmPasswordField); p.add(Box.createVerticalStrut(25));

        JButton signupBtn = new JButton("Sign Up");
        styleButton(signupBtn, new Color(234,88,12), Color.BLACK);
        signupBtn.setFont(new Font("Arial", Font.BOLD, 16));
        signupBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupBtn.addActionListener(e -> performSignup());
        p.add(signupBtn);
        p.add(Box.createVerticalStrut(15));

        JLabel haveAcc = new JLabel("Already have an account?", SwingConstants.CENTER);
        haveAcc.setFont(new Font("Arial", Font.PLAIN, 12));
        haveAcc.setForeground(new Color(102,102,102));
        haveAcc.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(haveAcc);

        JButton toLogin = new JButton("Login Here");
        styleLinkButton(toLogin, new Color(234,88,12));
        toLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        toLogin.addActionListener(e -> showLoginPanel());
        p.add(toLogin);

        return p;
    }

    /** Switches card to SignUp **/
    public void showSignUpPanel() {
        cardLayout.show(cardPanel, "SignUp");
    }

    /** Switches card to Login **/
    public void showLoginPanel() {
        cardLayout.show(cardPanel, "Login");
    }

    /** Attempts login against MySQL users table **/
    private void performLogin() {
        String email = loginEmailField.getText().trim();
        String pwd   = new String(loginPasswordField.getPassword());
        if (email.isEmpty() || pwd.isEmpty()
         || "Email Address".equals(email) || "Password".equals(pwd)) {
            JOptionPane.showMessageDialog(this,
                "Please enter both email and password.",
                "Login Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT full_name,password_hash FROM users WHERE email = ?"
            );
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()
             && rs.getString("password_hash")
                      .equals(PasswordUtil.hashPassword(pwd))) {

                String fullName = rs.getString("full_name");
                // Notify listener if exists
                if (authListener != null) {
                    authListener.onLoginSuccess(fullName);
                }
                // Close this window
                dispose();

            } else {
                JOptionPane.showMessageDialog(this,
                    "Invalid email or password.",
                    "Login Failed", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Database error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Attempts signup inserting into MySQL users table **/
    private void performSignup() {
        String name  = signupNameField.getText().trim();
        String email = signupEmailField.getText().trim();
        String p1    = new String(signupPasswordField.getPassword());
        String p2    = new String(signupConfirmPasswordField.getPassword());

        if (name.isEmpty()  || email.isEmpty()
         || p1.isEmpty()    || p2.isEmpty()
         || "Full Name".equals(name)
         || "Email Address".equals(email)
         || "Password".equals(p1)
         || "Confirm Password".equals(p2)) {
            JOptionPane.showMessageDialog(this,
                "All fields are required.",
                "Sign Up Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!p1.equals(p2)) {
            JOptionPane.showMessageDialog(this,
                "Passwords do not match.",
                "Sign Up Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnector.getConnection()) {
            // Check duplicate
            PreparedStatement chk = conn.prepareStatement(
                "SELECT 1 FROM users WHERE email = ?"
            );
            chk.setString(1, email);
            if (chk.executeQuery().next()) {
                JOptionPane.showMessageDialog(this,
                    "Email already in use.",
                    "Sign Up Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insert
            PreparedStatement ins = conn.prepareStatement(
                "INSERT INTO users(full_name,email,password_hash,created_at) " +
                "VALUES(?,?,?,NOW())"
            );
            ins.setString(1, name);
            ins.setString(2, email);
            ins.setString(3, PasswordUtil.hashPassword(p1));
            ins.executeUpdate();

            JOptionPane.showMessageDialog(this,
                "Account created! Please log in.",
                "Success", JOptionPane.INFORMATION_MESSAGE);

            showLoginPanel();
            loginEmailField.setText(email);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Sign up error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---- UI HELPER METHODS (unchanged) ----

    private JTextField createStyledTextField(String placeholder) {
        JTextField f = new JTextField(20);
        f.setMaximumSize(new Dimension(
            Integer.MAX_VALUE, f.getPreferredSize().height));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY,1),
            new EmptyBorder(8,10,8,10)
        ));
        f.setFont(new Font("Arial",Font.PLAIN,14));
        f.setForeground(Color.GRAY);
        f.setText(placeholder);
        f.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (f.getText().equals(placeholder)) {
                    f.setText(""); f.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (f.getText().isEmpty()) {
                    f.setText(placeholder); f.setForeground(Color.GRAY);
                }
            }
        });
        return f;
    }

    private JPasswordField createStyledPasswordField(String placeholder) {
        JPasswordField f = new JPasswordField(20);
        f.setMaximumSize(new Dimension(
            Integer.MAX_VALUE, f.getPreferredSize().height));
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
                if (new String(f.getPassword()).equals(placeholder)) {
                    f.setText(""); f.setEchoChar('•'); f.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (f.getPassword().length == 0) {
                    f.setEchoChar((char)0);
                    f.setText(placeholder);
                    f.setForeground(Color.GRAY);
                }
            }
        });
        return f;
    }

    private void styleButton(JButton b, Color bg, Color fg) {
        b.setBackground(bg); b.setForeground(fg);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        b.setFont(new Font("Arial",Font.BOLD,14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void styleLinkButton(JButton b, Color fg) {
        b.setBackground(null); b.setForeground(fg);
        b.setFocusPainted(false); b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFont(new Font("Arial",Font.BOLD,12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                b.setForeground(fg.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                b.setForeground(fg);
            }
        });
    }
}















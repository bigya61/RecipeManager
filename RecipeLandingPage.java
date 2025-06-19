//package app.ui; // Added the package declaration based on your feedback
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException; // Needed for potential image loading errors
//import java.net.URL; // For loading images from URLs
//import java.net.URI; // Added for URL creation
//import java.net.URISyntaxException; // Added for URI exception handling
//
//public class RecipeLandingPage extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//    private JPanel contentPane; // Main panel for the JFrame
//
//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    RecipeLandingPage frame = new RecipeLandingPage();
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
//    public RecipeLandingPage() {
//        // Set the title of the window
//        setTitle("Recipe Manager");
//        // Set default close operation
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        // Set initial size of the window
//        setSize(1000, 750); // Increased initial size for better visibility of cards
//        // Center the window on the screen
//        setLocationRelativeTo(null);
//
//        // Create the main content pane with BorderLayout
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPane.setLayout(new BorderLayout(0, 0)); // Use BorderLayout for top, center, bottom sections
//        setContentPane(contentPane);
//
//        // --- 1. Navigation Bar Panel ---
//        JPanel navBarPanel = new JPanel();
//        navBarPanel.setBackground(new Color(255, 255, 255)); // White background
//        navBarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Bottom border for separation
//        navBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // FlowLayout for components
//        contentPane.add(navBarPanel, BorderLayout.NORTH);
//
//        // Logo/Site Title
//        JLabel logoLabel = new JLabel("Recipe Management System");
//        logoLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Bold, larger font
//        logoLabel.setForeground(new Color(234, 88, 12)); // Orange color
//        navBarPanel.add(logoLabel);
//
//        // Search Bar
//        JTextField searchField = new JTextField(30); // Width of the search field
//        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
//        // Add a placeholder effect
//        searchField.setText("Search for recipes...");
//        searchField.setForeground(Color.GRAY);
//        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                if (searchField.getText().equals("Search for recipes...")) {
//                    searchField.setText("");
//                    searchField.setForeground(Color.BLACK);
//                }
//            }
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                if (searchField.getText().isEmpty()) {
//                    searchField.setText("Search for recipes...");
//                    searchField.setForeground(Color.GRAY);
//                }
//            }
//        });
//        navBarPanel.add(searchField);
//
//        // Login/Signup Buttons
//        JButton loginButton = new JButton("Login");
//        styleButton(loginButton, new Color(255, 255, 255), new Color(234, 88, 12)); // White with orange text
//        navBarPanel.add(loginButton);
//        // Action listener for Login button
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(RecipeLandingPage.this, "Login functionality coming soon!", "Login", JOptionPane.INFORMATION_MESSAGE);
//                // In a real app, you'd open a Login dialog/frame here
//            }
//        });
//
//        JButton signUpButton = new JButton("Sign Up");
//        styleButton(signUpButton, new Color(234, 88, 12), Color.WHITE); // Orange with white text
//        navBarPanel.add(signUpButton);
//        // Action listener for Sign Up button
//        signUpButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(RecipeLandingPage.this, "Sign Up process starting!", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
//                // In a real app, you'd open a Sign Up dialog/frame here
//            }
//        });
//
//        // --- 2. Main Content Scroll Pane ---
//        // Use a JScrollPane for the main content to allow scrolling if content exceeds window size
//        JScrollPane scrollPane = new JScrollPane();
//        contentPane.add(scrollPane, BorderLayout.CENTER);
//
//        JPanel mainContentPanel = new JPanel();
//        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS)); // Stack sections vertically
//        mainContentPanel.setBackground(new Color(248, 250, 252)); // Light gray background
//        scrollPane.setViewportView(mainContentPanel); // Set this panel as the scroll pane's view
//
//        // --- Hero Section ---
//        JPanel heroPanel = new JPanel();
//        heroPanel.setBackground(new Color(255, 237, 213)); // Light orange
//        heroPanel.setBorder(new EmptyBorder(30, 20, 30, 20)); // Padding
//        heroPanel.setLayout(new BorderLayout(0, 15)); // Vertical spacing
//        heroPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align within BoxLayout
//        mainContentPanel.add(heroPanel);
//        mainContentPanel.add(Box.createVerticalStrut(20)); // Spacer
//
//        JLabel heroTitle = new JLabel("Discover Delicious Recipes", SwingConstants.CENTER);
//        heroTitle.setFont(new Font("Arial", Font.BOLD, 36));
//        heroTitle.setForeground(new Color(153, 27, 27)); // Darker orange/red
//        heroPanel.add(heroTitle, BorderLayout.NORTH);
//
//        JTextArea heroDescription = new JTextArea("Explore a world of culinary delights, from quick meals to gourmet dishes. Find your next favorite recipe today!");
//        heroDescription.setFont(new Font("Arial", Font.PLAIN, 16));
//        heroDescription.setForeground(new Color(51, 51, 51));
//        heroDescription.setWrapStyleWord(true);
//        heroDescription.setLineWrap(true);
//        heroDescription.setEditable(false);
//        heroDescription.setBackground(null); // Make background transparent
//        heroDescription.setBorder(null); // Remove border
//        heroPanel.add(heroDescription, BorderLayout.CENTER);
//
//        JButton browseButton = new JButton("Browse Recipes");
//        styleButton(browseButton, new Color(234, 88, 12), Color.WHITE);
//        browseButton.setFont(new Font("Arial", Font.BOLD, 18));
//        heroPanel.add(browseButton, BorderLayout.SOUTH);
//
//
//        // --- Recipe Cards Section ---
//        JLabel popularRecipesTitle = new JLabel("Popular Recipes", SwingConstants.CENTER);
//        popularRecipesTitle.setFont(new Font("Arial", Font.BOLD, 28));
//        popularRecipesTitle.setForeground(new Color(51, 51, 51));
//        popularRecipesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        mainContentPanel.add(popularRecipesTitle);
//        mainContentPanel.add(Box.createVerticalStrut(20)); // Spacer
//
//        JPanel cardsPanel = new JPanel();
//        // Use GridLayout to arrange cards in a grid (e.g., 2 rows, 4 columns, or adjust as needed)
//        cardsPanel.setLayout(new GridLayout(0, 4, 15, 15)); // 0 rows (dynamic), 4 columns, 15px gap
//        cardsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        cardsPanel.setBackground(new Color(248, 250, 252));
//        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        mainContentPanel.add(cardsPanel);
//        mainContentPanel.add(Box.createVerticalStrut(30)); // Spacer
//
//        // Add example recipe cards
//        addRecipeCard(cardsPanel, "Delicious Pasta", "A rich and savory pasta dish with a creamy garlic sauce...", "https://placehold.co/400x250/FDBA74/FFFFFF?text=Delicious+Pasta");
//        addRecipeCard(cardsPanel, "Spicy Indian Chicken Curry", "An aromatic and flavorful chicken curry, slow-cooked with traditional Indian spices...", "https://placehold.co/400x250/A78BFA/FFFFFF?text=Chicken+Curry");
//        addRecipeCard(cardsPanel, "Fresh Quinoa Salad", "A light and healthy vegan quinoa salad packed with fresh vegetables...", "https://placehold.co/400x250/6EE7B7/FFFFFF?text=Vegan+Salad");
//        addRecipeCard(cardsPanel, "Decadent Chocolate Cake", "An intensely rich and moist chocolate cake with a velvety smooth chocolate ganache...", "https://placehold.co/400x250/FBBF24/FFFFFF?text=Chocolate+Cake");
//        addRecipeCard(cardsPanel, "Classic Margherita Pizza", "A timeless Italian classic with fresh mozzarella, basil, and a simple tomato sauce...", "https://placehold.co/400x250/C4B5FD/FFFFFF?text=Pizza");
//        addRecipeCard(cardsPanel, "Homemade Sushi Rolls", "Learn to make your own fresh and delicious sushi rolls at home...", "https://placehold.co/400x250/FECACA/FFFFFF?text=Sushi");
//        addRecipeCard(cardsPanel, "Comforting Tomato Soup", "A warm and hearty tomato soup recipe, perfect for a cozy evening...", "https://placehold.co/400x250/BFDBFE/FFFFFF?text=Soup");
//        addRecipeCard(cardsPanel, "Berry Blast Smoothie", "A refreshing and nutritious smoothie packed with mixed berries, yogurt...", "https://placehold.co/400x250/CFFAFE/FFFFFF?text=Smoothie");
//
//
//        // --- Add New Recipe Section ---
//        JPanel addRecipePanel = new JPanel();
//        addRecipePanel.setBackground(new Color(255, 247, 237)); // Lighter orange
//        addRecipePanel.setBorder(new EmptyBorder(30, 20, 30, 20));
//        addRecipePanel.setLayout(new BoxLayout(addRecipePanel, BoxLayout.Y_AXIS)); // Stack vertically
//        addRecipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        mainContentPanel.add(addRecipePanel);
//        mainContentPanel.add(Box.createVerticalStrut(20)); // Spacer
//
//        JLabel addRecipeTitle = new JLabel("Didn't find what you were looking for?", SwingConstants.CENTER);
//        addRecipeTitle.setFont(new Font("Arial", Font.BOLD, 28));
//        addRecipeTitle.setForeground(new Color(51, 51, 51));
//        addRecipeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addRecipePanel.add(addRecipeTitle);
//        addRecipePanel.add(Box.createVerticalStrut(10));
//
//        JTextArea addRecipeDescription = new JTextArea("Help us grow our collection by adding your own unique recipes!");
//        addRecipeDescription.setFont(new Font("Arial", Font.PLAIN, 16));
//        addRecipeDescription.setForeground(new Color(51, 51, 51));
//        addRecipeDescription.setWrapStyleWord(true);
//        addRecipeDescription.setLineWrap(true);
//        addRecipeDescription.setEditable(false);
//        addRecipeDescription.setBackground(null);
//        addRecipeDescription.setBorder(null);
//        addRecipeDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addRecipePanel.add(addRecipeDescription);
//        addRecipePanel.add(Box.createVerticalStrut(20));
//
//        JButton addNewRecipeButton = new JButton("Add New Recipe");
//        styleButton(addNewRecipeButton, new Color(234, 88, 12), Color.WHITE);
//        addNewRecipeButton.setFont(new Font("Arial", Font.BOLD, 20));
//        addNewRecipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addRecipePanel.add(addNewRecipeButton);
//
//        // Add action listener to the "Add New Recipe" button
//        addNewRecipeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(RecipeLandingPage.this, "Opening new recipe submission form!", "Add Recipe", JOptionPane.INFORMATION_MESSAGE);
//                // In a real application, you would open a new JFrame or dialog here
//            }
//        });
//
//
//        // --- Footer Panel ---
//        JPanel footerPanel = new JPanel();
//        footerPanel.setBackground(new Color(51, 51, 51)); // Dark gray background
//        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centered flow layout
//        contentPane.add(footerPanel, BorderLayout.SOUTH);
//
//        JLabel copyrightLabel = new JLabel("© 2024 RecipeHub. All rights reserved.");
//        copyrightLabel.setForeground(Color.WHITE);
//        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
//        footerPanel.add(copyrightLabel);
//
//        // Add dummy links (these would typically open web pages)
//        JButton privacyPolicy = new JButton("Privacy Policy");
//        styleLinkButton(privacyPolicy);
//        footerPanel.add(privacyPolicy);
//
//        JButton termsOfService = new JButton("Terms of Service");
//        styleLinkButton(termsOfService);
//        footerPanel.add(termsOfService);
//
//        JButton contactUs = new JButton("Contact Us");
//        styleLinkButton(contactUs);
//        footerPanel.add(contactUs);
//    }
//
//    /**
//     * Helper method to style buttons for a modern look.
//     */
//    private void styleButton(JButton button, Color bgColor, Color fgColor) {
//        button.setBackground(bgColor);
//        button.setForeground(fgColor);
//        button.setFocusPainted(false); // Remove focus border
//        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
//        button.setFont(new Font("Arial", Font.BOLD, 14));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
//        // Add a subtle border for white buttons for better visibility
//        if (bgColor.equals(Color.WHITE)) {
//            button.setBorder(BorderFactory.createLineBorder(new Color(234, 88, 12), 1)); // Orange border
//        }
//    }
//
//    /**
//     * Helper method to style buttons that act as links.
//     */
//    private void styleLinkButton(JButton button) {
//        button.setBackground(null); // Transparent background
//        button.setForeground(Color.WHITE);
//        button.setFocusPainted(false);
//        button.setBorderPainted(false); // No border
//        button.setContentAreaFilled(false); // Transparent content area
//        button.setFont(new Font("Arial", Font.PLAIN, 12));
//        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseEntered(java.awt.event.MouseEvent evt) {
//                button.setForeground(new Color(251, 191, 36)); // Lighter orange on hover
//            }
//            public void mouseExited(java.awt.event.MouseEvent evt) {
//                button.setForeground(Color.WHITE);
//            }
//        });
//    }
//
//    /**
//     * Helper method to create and add a recipe card to a panel.
//     */
//    private void addRecipeCard(JPanel parentPanel, String title, String description, String imageUrl) {
//        JPanel cardPanel = new JPanel();
//        cardPanel.setLayout(new BorderLayout(0, 5)); // Vertical spacing for card elements
//        cardPanel.setBackground(Color.WHITE);
//        cardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Subtle border
//        cardPanel.setPreferredSize(new Dimension(220, 300)); // Fixed size for cards
//        cardPanel.setMaximumSize(new Dimension(220, 300)); // Ensure it doesn't grow too much
//        cardPanel.setMinimumSize(new Dimension(200, 280)); // Minimum size
//        cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
//
//        // Image label
//        JLabel imageLabel = new JLabel();
//        imageLabel.setPreferredSize(new Dimension(200, 150)); // Fixed size for image area
//        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
//
//        // Load image from URL in a separate thread to avoid freezing GUI
//        new Thread(() -> {
//            try {
//                // Use URI to construct URL to avoid deprecated constructor
//                URL url = new URI(imageUrl).toURL();
//                ImageIcon imageIcon = new ImageIcon(url);
//                Image image = imageIcon.getImage(); // transform it
//                Image newimg = image.getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly
//                imageIcon = new ImageIcon(newimg);  // transform it back
//                imageLabel.setIcon(imageIcon);
//            } catch (IOException e) {
//                System.err.println("Error loading image (IO): " + imageUrl + " - " + e.getMessage());
//                // Fallback to a placeholder text or icon if image fails to load
//                imageLabel.setText("No Image");
//                imageLabel.setForeground(Color.GRAY);
//                imageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
//            } catch (URISyntaxException e) {
//                System.err.println("Error loading image (URI Syntax): " + imageUrl + " - " + e.getMessage());
//                imageLabel.setText("Invalid Image URL");
//                imageLabel.setForeground(Color.RED);
//                imageLabel.setFont(new Font("Arial", Font.ITALIC, 10));
//            }
//        }).start();
//
//        cardPanel.add(imageLabel, BorderLayout.NORTH);
//
//        // Text content panel for title and description
//        JPanel textContentPanel = new JPanel();
//        textContentPanel.setLayout(new BoxLayout(textContentPanel, BoxLayout.Y_AXIS)); // Stack title and description
//        textContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding inside text content
//        textContentPanel.setBackground(Color.WHITE);
//        cardPanel.add(textContentPanel, BorderLayout.CENTER);
//
//        JLabel titleLabel = new JLabel(title);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        titleLabel.setForeground(new Color(51, 51, 51));
//        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align title to left
//        textContentPanel.add(titleLabel);
//        textContentPanel.add(Box.createVerticalStrut(5)); // Spacer
//
//        JTextArea descriptionArea = new JTextArea(description);
//        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 12));
//        descriptionArea.setForeground(new Color(102, 102, 102));
//        descriptionArea.setWrapStyleWord(true);
//        descriptionArea.setLineWrap(true);
//        descriptionArea.setEditable(false);
//        descriptionArea.setBackground(null);
//        descriptionArea.setBorder(null);
//        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT); // Align description to left
//        // Limit description to a few lines
//        descriptionArea.setPreferredSize(new Dimension(180, 50)); // Fixed height to simulate line-clamp
//        descriptionArea.setMinimumSize(new Dimension(180, 50));
//        descriptionArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
//        textContentPanel.add(descriptionArea);
//        textContentPanel.add(Box.createVerticalStrut(5)); // Spacer
//
//        JButton readMoreButton = new JButton("Read More ->");
//        readMoreButton.setFont(new Font("Arial", Font.PLAIN, 12));
//        readMoreButton.setForeground(new Color(234, 88, 12)); // Orange color for link
//        readMoreButton.setBackground(null);
//        readMoreButton.setBorderPainted(false);
//        readMoreButton.setContentAreaFilled(false);
//        readMoreButton.setFocusPainted(false);
//        readMoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        readMoreButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
//        textContentPanel.add(readMoreButton);
//
//        // Action listener for "Read More" button on recipe cards
//        readMoreButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(RecipeLandingPage.this, "Viewing details for: " + title, "Recipe Details", JOptionPane.INFORMATION_MESSAGE);
//                // In a real app, you'd open a new dialog/frame with full recipe details
//            }
//        });
//
//        parentPanel.add(cardPanel); // Add the complete card to the parent panel
//    }
//}
package app.ui; // Added the package declaration

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter; // For component resizing
import java.awt.event.ComponentEvent;   // For component resizing
import java.io.IOException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

// Import the LoginPage class
// import app.ui.LoginPage; // This import is implicit if both classes are in the same package 'app.ui'

public class RecipeLandingPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane; // Main panel for the JFrame
    private JPanel cardsPanel; // Reference to the panel holding recipe cards for dynamic adjustments

    // Constants for responsive card layout calculation
    private static final int CARD_MIN_WIDTH = 220; // Ideal minimum width for a card
    private static final int CARD_HORIZONTAL_GAP = 15; // Horizontal gap between cards
    private static final int CARDS_PANEL_HORIZONTAL_PADDING = 20; // Estimated padding around cardsPanel

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RecipeLandingPage frame = new RecipeLandingPage();
                    frame.setVisible(true);
                    // After frame is visible, ensure initial layout adjustment is performed
                    // This is important because component sizes are not finalized until then
                    SwingUtilities.invokeLater(() -> frame.adjustCardLayout());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public RecipeLandingPage() {
        // Set the title of the window
        setTitle("Recipe Manager");
        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set initial size of the window
        setSize(1000, 750); // Increased initial size for better visibility of cards
        // Center the window on the screen
        setLocationRelativeTo(null);

        // Create the main content pane with BorderLayout
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0)); // Use BorderLayout for top, center, bottom sections
        setContentPane(contentPane);

        // --- 1. Navigation Bar Panel ---
        JPanel navBarPanel = new JPanel();
        navBarPanel.setBackground(new Color(255, 255, 255)); // White background
        navBarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Bottom border for separation
        navBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); // FlowLayout for components
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        // Logo/Site Title
        JLabel logoLabel = new JLabel("Recipe Management System");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Bold, larger font
        logoLabel.setForeground(new Color(234, 88, 12)); // Orange color
        navBarPanel.add(logoLabel);

        // Search Bar
        JTextField searchField = new JTextField(30); // Width of the search field
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        // Add a placeholder effect
        searchField.setText("Search for recipes...");
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search for recipes...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for recipes...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        navBarPanel.add(searchField);

        // Login/Signup Buttons
        JButton loginButton = new JButton("Login");
        styleButton(loginButton, new Color(255, 255, 255), new Color(234, 88, 12)); // White with orange text
        navBarPanel.add(loginButton);
        // Action listener for Login button - MODIFIED
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage(); // Create an instance of the LoginPage
                loginPage.setVisible(true); // Make the LoginPage visible
                RecipeLandingPage.this.setVisible(false); // Hide the current RecipeLandingPage

                // Optional: Add a WindowListener to show RecipeLandingPage again when LoginPage is closed
                loginPage.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        // This will make the landing page visible again if LoginPage is closed.
                        // Consider if you want this behavior, or if login/signup should lead to a different main app state.
                        RecipeLandingPage.this.setVisible(true);
                    }
                });
            }
        });

        JButton signUpButton = new JButton("Sign Up");
        styleButton(signUpButton, new Color(234, 88, 12), Color.WHITE); // Orange with white text
        navBarPanel.add(signUpButton);
        // Action listener for Sign Up button - MODIFIED
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage(); // Create an instance of the LoginPage
                loginPage.setVisible(true); // Make the LoginPage visible
                loginPage.showSignUpPanel(); // Immediately switch to the Sign Up form in LoginPage
                RecipeLandingPage.this.setVisible(false); // Hide the current RecipeLandingPage

                // Optional: Add a WindowListener to show RecipeLandingPage again when LoginPage is closed
                loginPage.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        // This will make the landing page visible again if LoginPage is closed.
                        RecipeLandingPage.this.setVisible(true);
                    }
                });
            }
        });

        // --- 2. Main Content Scroll Pane ---
        // Use a JScrollPane for the main content to allow scrolling if content exceeds window size
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS)); // Stack sections vertically
        mainContentPanel.setBackground(new Color(248, 250, 252)); // Light gray background
        scrollPane.setViewportView(mainContentPanel); // Set this panel as the scroll pane's view

        // --- Hero Section ---
        JPanel heroPanel = new JPanel();
        heroPanel.setBackground(new Color(255, 237, 213)); // Light orange
        heroPanel.setBorder(new EmptyBorder(30, 20, 30, 20)); // Padding
        heroPanel.setLayout(new BorderLayout(0, 15)); // Vertical spacing
        heroPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align within BoxLayout
        mainContentPanel.add(heroPanel);
        mainContentPanel.add(Box.createVerticalStrut(20)); // Spacer

        JLabel heroTitle = new JLabel("Discover Delicious Recipes", SwingConstants.CENTER);
        heroTitle.setFont(new Font("Arial", Font.BOLD, 36));
        heroTitle.setForeground(new Color(153, 27, 27)); // Darker orange/red
        heroPanel.add(heroTitle, BorderLayout.NORTH);

        JTextArea heroDescription = new JTextArea("Explore a world of culinary delights, from quick meals to gourmet dishes. Find your next favorite recipe today!");
        heroDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        heroDescription.setForeground(new Color(51, 51, 51));
        heroDescription.setWrapStyleWord(true);
        heroDescription.setLineWrap(true);
        heroDescription.setEditable(false);
        heroDescription.setBackground(null); // Make background transparent
        heroDescription.setBorder(null); // Remove border
        heroPanel.add(heroDescription, BorderLayout.CENTER);

        JButton browseButton = new JButton("Browse Recipes");
        styleButton(browseButton, new Color(234, 88, 12), Color.WHITE);
        browseButton.setFont(new Font("Arial", Font.BOLD, 18));
        heroPanel.add(browseButton, BorderLayout.SOUTH);


        // --- Recipe Cards Section ---
        JLabel popularRecipesTitle = new JLabel("Popular Recipes", SwingConstants.CENTER);
        popularRecipesTitle.setFont(new Font("Arial", Font.BOLD, 28));
        popularRecipesTitle.setForeground(new Color(51, 51, 51));
        popularRecipesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(popularRecipesTitle);
        mainContentPanel.add(Box.createVerticalStrut(20)); // Spacer

        cardsPanel = new JPanel(); // Initialize cardsPanel
        // Use GridLayout to arrange cards in a grid (e.g., 2 rows, 4 columns, or adjust as needed)
        cardsPanel.setLayout(new GridLayout(0, 1, CARD_HORIZONTAL_GAP, 15)); // 0 rows (dynamic), 1 column initially, with gaps
        cardsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        cardsPanel.setBackground(new Color(248, 250, 252));
        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(cardsPanel);
        mainContentPanel.add(Box.createVerticalStrut(30)); // Spacer

        // Add ComponentListener to mainContentPanel to adjust cards layout on resize
        mainContentPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustCardLayout();
            }
        });


        // Add example recipe cards
        addRecipeCard(cardsPanel, "Delicious Pasta", "A rich and savory pasta dish with a creamy garlic sauce...", "https://placehold.co/400x250/FDBA74/FFFFFF?text=Delicious+Pasta");
        addRecipeCard(cardsPanel, "Spicy Indian Chicken Curry", "An aromatic and flavorful chicken curry, slow-cooked with traditional Indian spices...", "https://placehold.co/400x250/A78BFA/FFFFFF?text=Chicken+Curry");
        addRecipeCard(cardsPanel, "Fresh Quinoa Salad", "A light and healthy vegan quinoa salad packed with fresh vegetables...", "https://placehold.co/400x250/6EE7B7/FFFFFF?text=Vegan+Salad");
        addRecipeCard(cardsPanel, "Decadent Chocolate Cake", "An intensely rich and moist chocolate cake with a velvety smooth chocolate ganache...", "https://placehold.co/400x250/FBBF24/FFFFFF?text=Chocolate+Cake");
        addRecipeCard(cardsPanel, "Classic Margherita Pizza", "A timeless Italian classic with fresh mozzarella, basil, and a simple tomato sauce...", "https://placehold.co/400x250/C4B5FD/FFFFFF?text=Pizza");
        addRecipeCard(cardsPanel, "Homemade Sushi Rolls", "Learn to make your own fresh and delicious sushi rolls at home...", "https://placehold.co/400x250/FECACA/FFFFFF?text=Sushi");
        addRecipeCard(cardsPanel, "Comforting Tomato Soup", "A warm and hearty tomato soup recipe, perfect for a cozy evening...", "https://placehold.co/400x250/BFDBFE/FFFFFF?text=Soup");
        addRecipeCard(cardsPanel, "Berry Blast Smoothie", "A refreshing and nutritious smoothie packed with mixed berries, yogurt...", "https://placehold.co/400x250/CFFAFE/FFFFFF?text=Smoothie");


        // --- Add New Recipe Section ---
        JPanel addRecipePanel = new JPanel();
        addRecipePanel.setBackground(new Color(255, 247, 237)); // Lighter orange
        addRecipePanel.setBorder(new EmptyBorder(30, 20, 30, 20));
        addRecipePanel.setLayout(new BoxLayout(addRecipePanel, BoxLayout.Y_AXIS)); // Stack vertically
        addRecipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(addRecipePanel);
        mainContentPanel.add(Box.createVerticalStrut(20)); // Spacer

        JLabel addRecipeTitle = new JLabel("Didn't find what you were looking for?", SwingConstants.CENTER);
        addRecipeTitle.setFont(new Font("Arial", Font.BOLD, 28));
        addRecipeTitle.setForeground(new Color(51, 51, 51));
        addRecipeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRecipePanel.add(addRecipeTitle);
        addRecipePanel.add(Box.createVerticalStrut(10));

        JTextArea addRecipeDescription = new JTextArea("Help us grow our collection by adding your own unique recipes!");
        addRecipeDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        addRecipeDescription.setForeground(new Color(51, 51, 51));
        addRecipeDescription.setWrapStyleWord(true);
        addRecipeDescription.setLineWrap(true);
        addRecipeDescription.setEditable(false);
        addRecipeDescription.setBackground(null);
        addRecipeDescription.setBorder(null);
        addRecipeDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRecipePanel.add(addRecipeDescription);
        addRecipePanel.add(Box.createVerticalStrut(20));

        JButton addNewRecipeButton = new JButton("Add New Recipe");
        styleButton(addNewRecipeButton, new Color(234, 88, 12), Color.WHITE);
        addNewRecipeButton.setFont(new Font("Arial", Font.BOLD, 20));
        addNewRecipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRecipePanel.add(addNewRecipeButton);

        // Add action listener to the "Add New Recipe" button
        addNewRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RecipeLandingPage.this, "Opening new recipe submission form!", "Add Recipe", JOptionPane.INFORMATION_MESSAGE);
                // In a real application, you would open a new JFrame or dialog here
            }
        });


        // --- Footer Panel ---
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(51, 51, 51)); // Dark gray background
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Centered flow layout
        contentPane.add(footerPanel, BorderLayout.SOUTH);

        JLabel copyrightLabel = new JLabel("© 2024 RecipeHub. All rights reserved.");
        copyrightLabel.setForeground(Color.WHITE);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerPanel.add(copyrightLabel);

        // Add dummy links (these would typically open web pages)
        JButton privacyPolicy = new JButton("Privacy Policy");
        styleLinkButton(privacyPolicy);
        footerPanel.add(privacyPolicy);

        JButton termsOfService = new JButton("Terms of Service");
        styleLinkButton(termsOfService);
        footerPanel.add(termsOfService);

        JButton contactUs = new JButton("Contact Us");
        styleLinkButton(contactUs);
        footerPanel.add(contactUs);
    }

    /**
     * Helper method to adjust the number of columns in the recipe cards grid
     * based on the available panel width.
     */
    private void adjustCardLayout() {
        if (cardsPanel == null) {
            return; // Ensure cardsPanel is initialized
        }
        
        int availableWidth = cardsPanel.getWidth();
        if (availableWidth <= 0) { // Avoid division by zero or negative width
            return;
        }

        // Calculate how many cards can fit horizontally
        // Account for card min width + horizontal gap
        int idealColumns = availableWidth / (CARD_MIN_WIDTH + CARD_HORIZONTAL_GAP);
        int newColumns = Math.max(1, idealColumns); // Ensure at least one column

        GridLayout layout = (GridLayout) cardsPanel.getLayout();
        if (layout.getColumns() != newColumns) {
            layout.setColumns(newColumns);
            cardsPanel.revalidate(); // Re-layout the components
            cardsPanel.repaint(); // Repaint to reflect changes
        }
    }

    /**
     * Helper method to style buttons for a modern look.
     */
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        // Add a subtle border for white buttons for better visibility
        if (bgColor.equals(Color.WHITE)) {
            button.setBorder(BorderFactory.createLineBorder(new Color(234, 88, 12), 1)); // Orange border
        }
    }

    /**
     * Helper method to style buttons that act as links.
     */
    private void styleLinkButton(JButton button) {
        button.setBackground(null); // Transparent background
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false); // No border
        button.setContentAreaFilled(false); // Transparent content area
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(251, 191, 36)); // Lighter orange on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.WHITE);
            }
        });
    }

    /**
     * Helper method to create and add a recipe card to a panel.
     */
    private void addRecipeCard(JPanel parentPanel, String title, String description, String imageUrl) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout(0, 5)); // Vertical spacing for card elements
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Subtle border
        cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover

        // Image label
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 150)); // Fixed size for image area
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Load image from URL in a separate thread to avoid freezing GUI
        new Thread(() -> {
            try {
                // Use URI to construct URL to avoid deprecated constructor
                URL url = new URI(imageUrl).toURL();
                ImageIcon imageIcon = new ImageIcon(url);
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH); // scale it smoothly
                imageIcon = new ImageIcon(newimg);  // transform it back
                imageLabel.setIcon(imageIcon);
            } catch (IOException e) {
                System.err.println("Error loading image (IO): " + imageUrl + " - " + e.getMessage());
                // Fallback to a placeholder text or icon if image fails to load
                imageLabel.setText("No Image");
                imageLabel.setForeground(Color.GRAY);
                imageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            } catch (URISyntaxException e) {
                System.err.println("Error loading image (URI Syntax): " + imageUrl + " - " + e.getMessage());
                imageLabel.setText("Invalid Image URL");
                imageLabel.setForeground(Color.RED);
                imageLabel.setFont(new Font("Arial", Font.ITALIC, 10));
            }
        }).start();

        cardPanel.add(imageLabel, BorderLayout.NORTH);

        // Text content panel for title and description
        JPanel textContentPanel = new JPanel();
        textContentPanel.setLayout(new BoxLayout(textContentPanel, BoxLayout.Y_AXIS)); // Stack title and description
        textContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding inside text content
        textContentPanel.setBackground(Color.WHITE);
        cardPanel.add(textContentPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align title to left
        textContentPanel.add(titleLabel);
        textContentPanel.add(Box.createVerticalStrut(5)); // Spacer

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionArea.setForeground(new Color(102, 102, 102));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(null);
        descriptionArea.setBorder(null);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT); // Align description to left
        // Limit description to a few lines
        descriptionArea.setPreferredSize(new Dimension(180, 50)); // Fixed height to simulate line-clamp
        descriptionArea.setMinimumSize(new Dimension(180, 50));
        descriptionArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        textContentPanel.add(descriptionArea);
        textContentPanel.add(Box.createVerticalStrut(5)); // Spacer

        JButton readMoreButton = new JButton("Read More ->");
        readMoreButton.setFont(new Font("Arial", Font.PLAIN, 12));
        readMoreButton.setForeground(new Color(234, 88, 12)); // Orange color for link
        readMoreButton.setBackground(null);
        readMoreButton.setBorderPainted(false);
        readMoreButton.setContentAreaFilled(false);
        readMoreButton.setFocusPainted(false);
        readMoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        readMoreButton.setAlignmentX(Component.LEFT_ALIGNMENT); // Align to left
        textContentPanel.add(readMoreButton);

        // Action listener for "Read More" button on recipe cards
        readMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RecipeLandingPage.this, "Viewing details for: " + title, "Recipe Details", JOptionPane.INFORMATION_MESSAGE);
                // In a real app, you'd open a new dialog/frame with full recipe details
            }
        });

        parentPanel.add(cardPanel); // Add the complete card to the parent panel
    }
}

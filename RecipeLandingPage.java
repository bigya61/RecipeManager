
package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RecipeLandingPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel cardsPanel;

    private static final int CARD_MIN_WIDTH = 220;
    private static final int CARD_HORIZONTAL_GAP = 15;

    // --- Inner class to simulate Recipe data ---
    private static class RecipeData {
        String title;
        String description;
        String imageUrl;
        List<String> ingredients;
        String cookTime;

        public RecipeData(String title, String description, String imageUrl, List<String> ingredients, String cookTime) {
            this.title = title;
            this.description = description;
            this.imageUrl = imageUrl;
            this.ingredients = ingredients;
            this.cookTime = cookTime;
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RecipeLandingPage frame = new RecipeLandingPage();
                    frame.setVisible(true);
                    SwingUtilities.invokeLater(() -> {
                        frame.adjustCardLayout();
                        frame.loadAndDisplayRecipes();
                    });
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
        setTitle("Recipe Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        //setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // --- 1. Navigation Bar Panel ---
        JPanel navBarPanel = new JPanel();
        navBarPanel.setBackground(new Color(255, 255, 255));
        navBarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        navBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        contentPane.add(navBarPanel, BorderLayout.NORTH);

        JLabel logoLabel = new JLabel("Recipe Manager");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(new Color(234, 88, 12));
        navBarPanel.add(logoLabel);

        JTextField searchField = new JTextField(30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
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

        JButton loginButton = new JButton("Login");
        styleButton(loginButton, new Color(255, 255, 255), new Color(234, 88, 12));
        navBarPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                RecipeLandingPage.this.setVisible(false);

                loginPage.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        RecipeLandingPage.this.setVisible(true);
                    }
                });
            }
        });

        JButton signUpButton = new JButton("Sign Up");
        styleButton(signUpButton, new Color(234, 88, 12), Color.WHITE);
        navBarPanel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                loginPage.showSignUpPanel();
                RecipeLandingPage.this.setVisible(false);

                loginPage.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        RecipeLandingPage.this.setVisible(true);
                    }
                });
            }
        });

        // --- 2. Main Content Scroll Pane ---
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(new Color(248, 250, 252));
        scrollPane.setViewportView(mainContentPanel);

        // --- Hero Section ---
        JPanel heroPanel = new JPanel();
        heroPanel.setBackground(new Color(255, 237, 213));
        heroPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
        heroPanel.setLayout(new BorderLayout(0, 15));
        heroPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(heroPanel);
        mainContentPanel.add(Box.createVerticalStrut(20));

        JLabel heroTitle = new JLabel("Discover Delicious Recipes", SwingConstants.CENTER);
        heroTitle.setFont(new Font("Arial", Font.BOLD, 36));
        heroTitle.setForeground(new Color(153, 27, 27));
        heroPanel.add(heroTitle, BorderLayout.NORTH);

        JTextArea heroDescription = new JTextArea("Discover, manage, and preserve authentic Nepali recipes!");
        heroDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        heroDescription.setForeground(new Color(51, 51, 51));
        heroDescription.setWrapStyleWord(true);
        heroDescription.setLineWrap(true);
        heroDescription.setEditable(false);
        heroDescription.setBackground(null);
        heroDescription.setBorder(null);
        heroPanel.add(heroDescription, BorderLayout.CENTER);

        JButton browseButton = new JButton("Browse Recipes");
        styleButton(browseButton, new Color(234, 88, 12), Color.WHITE);
        browseButton.setFont(new Font("Arial", Font.BOLD, 18));
        heroPanel.add(browseButton, BorderLayout.SOUTH);

        // --- Recipe Cards Section ---
        JLabel popularRecipesTitle = new JLabel("Traditional Nepalese Recipes", SwingConstants.CENTER);
        popularRecipesTitle.setFont(new Font("Arial", Font.BOLD, 28));
        popularRecipesTitle.setForeground(new Color(51, 51, 51));
        popularRecipesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(popularRecipesTitle);
        mainContentPanel.add(Box.createVerticalStrut(20));

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(0, 1, CARD_HORIZONTAL_GAP, 15));
        cardsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        cardsPanel.setBackground(new Color(248, 250, 252));
        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(cardsPanel);
        mainContentPanel.add(Box.createVerticalStrut(30));

        mainContentPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustCardLayout();
            }
        });

        // --- Add New Recipe Section ---
        JPanel addRecipePanel = new JPanel();
        addRecipePanel.setBackground(new Color(255, 247, 237));
        addRecipePanel.setBorder(new EmptyBorder(30, 20, 30, 20));
        addRecipePanel.setLayout(new BoxLayout(addRecipePanel, BoxLayout.Y_AXIS));
        addRecipePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(addRecipePanel);
        mainContentPanel.add(Box.createVerticalStrut(20));

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
        styleButton(addNewRecipeButton, new Color(234, 88, 12), Color.BLACK);
        addNewRecipeButton.setFont(new Font("Arial", Font.BOLD, 20));
        addNewRecipeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRecipePanel.add(addNewRecipeButton);

        addNewRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RecipeLandingPage.this, "Opening new recipe submission form!", "Add Recipe", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // --- Footer Panel ---
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(51, 51, 51));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        contentPane.add(footerPanel, BorderLayout.SOUTH);

        JLabel copyrightLabel = new JLabel("© 2024 RecipeHub. All rights reserved.");
        copyrightLabel.setForeground(Color.WHITE);
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerPanel.add(copyrightLabel);

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
     * Helper method to simulate fetching recipe data.
     * For now, this replaces database fetching with hardcoded data.
     */
    private List<RecipeData> fetchRecipes() {
        List<RecipeData> recipes = new ArrayList<>();
        // Simulate a short delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Hardcoded Traditional Nepalese Recipes
        recipes.add(new RecipeData("Dal, Bhat, Tarkari", "A classic dish that represents the soul-warming essence of Nepali comfort food. It's a staple meal consisting of lentil soup (dal), steamed rice (bhat), and vegetable curry (tarkari), often served with pickles (achar) and sometimes meat.", "https://placehold.co/400x250/FDBA74/FFFFFF?text=Dal+Bhat+Tarkari",
            Arrays.asList("1 cup lentils", "2 cups rice", "Mixed vegetables (potatoes, cauliflower, beans)", "Spices (turmeric, cumin, coriander)", "Ghee", "Onion", "Garlic", "Ginger"), "45 minutes"));
        recipes.add(new RecipeData("Chukauni", "It is made from boiled potatoes, yogurt, onion, coriander and spices. A popular side dish from Palpa, it's creamy, tangy, and a delightful accompaniment to main meals or as a snack.", "https://placehold.co/400x250/A78BFA/FFFFFF?text=Chukauni",
            Arrays.asList("4-5 boiled potatoes", "1 cup plain yogurt", "1 onion (finely chopped)", "1 green chili (finely chopped)", "Coriander leaves", "Mustard oil", "Fenugreek seeds", "Turmeric powder", "Salt"), "20 minutes"));
        recipes.add(new RecipeData("Sel Roti", "Made from a batter of rice flour, water, sugar, ghee, and spices which is then deep-fried in cooking oil to form a beautiful ring-shaped bread. It's a traditional homemade, ring-shaped rice bread, often prepared during festivals.", "https://placehold.co/400x250/6EE7B7/FFFFFF?text=Sel+Roti",
            Arrays.asList("2 cups rice flour", "1/2 cup sugar", "1/4 cup ghee (clarified butter)", "1/2 tsp cardamom powder", "Water (as needed)", "Cooking oil for frying"), "30 minutes"));
        recipes.add(new RecipeData("Jaggery Khajuri", "A traditional sweet that has been in the market for over a century. It is generally made of flour and sugar and fried in ghee, resulting in a crispy and sweet pastry, perfect with tea.", "https://placehold.co/400x250/FBBF24/FFFFFF?text=Jaggery+Khajuri",
            Arrays.asList("2 cups all-purpose flour", "1/2 cup jaggery (gud)", "1/4 cup ghee", "Water", "Fennel seeds", "Cooking oil for frying"), "40 minutes"));
        recipes.add(new RecipeData("Gundruk, Bhatmas Sadeko", "Gundruk is a traditional Nepali dish made out of fermented leafy vegetables, often served as a side dish. Bhatmas Sadeko is a spicy soybean salad. When combined, they offer a unique tangy and savory flavor.", "https://placehold.co/400x250/C4B5FD/FFFFFF?text=Gundruk+Bhatmas",
            Arrays.asList("1 cup dried gundruk", "1/2 cup roasted soybeans (bhatmas)", "Onion", "Garlic", "Ginger", "Green chilies", "Tomatoes", "Mustard oil", "Coriander leaves", "Salt", "Spices"), "35 minutes"));
        recipes.add(new RecipeData("Rildok Soup (Nepali Soup)", "A Sherpa dish made from potatoes mashed to a pulp by beating or pounding it. This hearty soup is often consumed during cold weather, providing warmth and energy.", "https://placehold.co/400x250/FECACA/FFFFFF?text=Rildok+Soup",
            Arrays.asList("4-5 large potatoes", "2-3 garlic cloves", "1 inch ginger", "Green chilies", "Coriander leaves", "Butter/Ghee", "Salt to taste"), "30 minutes"));
        recipes.add(new RecipeData("Chatamari (Rice Pancake)", "A savory rice pancake, often topped with meat, eggs, or vegetables. Sometimes referred to as 'Nepali Pizza,' it's a traditional dish of the Newar community.", "https://placehold.co/400x250/BFDBFE/FFFFFF?text=Chatamari",
            Arrays.asList("1 cup rice flour", "Water", "Salt", "Minced meat (optional)", "Egg (optional)", "Onion", "Coriander", "Spices"), "25 minutes"));
        recipes.add(new RecipeData("Yomari (Steamed Dumpling)", "A steamed dumpling made from rice flour and flour and filled with a sweet filling, typically \"chhaku\" (a sweet paste made from jaggery) or a savory lentil paste. It's a special delicacy of the Newar community, especially during the Yomari Punhi festival.", "https://placehold.co/400x250/CFFAFE/FFFFFF?text=Yomari",
            Arrays.asList("1 cup rice flour", "Jaggery (gud)", "Sesame seeds", "Coconut (grated)", "Ghee", "Water"), "40 minutes"));

        return recipes;
    }

    /**
     * Loads recipes asynchronously and displays them.
     */
    private void loadAndDisplayRecipes() {
        cardsPanel.removeAll();
        cardsPanel.revalidate();
        cardsPanel.repaint();

        JLabel loadingLabel = new JLabel("Loading recipes...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        loadingLabel.setForeground(Color.GRAY);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardsPanel.add(loadingLabel);

        SwingWorker<List<RecipeData>, Void> worker = new SwingWorker<List<RecipeData>, Void>() {
            @Override
            protected List<RecipeData> doInBackground() throws Exception {
                return fetchRecipes();
            }

            @Override
            protected void done() {
                try {
                    List<RecipeData> recipes = get();
                    cardsPanel.removeAll();
                    for (RecipeData recipe : recipes) {
                        addRecipeCard(cardsPanel, recipe);
                    }
                } catch (Exception e) {
                    System.err.println("Error displaying recipes: " + e.getMessage());
                    e.printStackTrace();
                    cardsPanel.removeAll();
                    JLabel errorLabel = new JLabel("Failed to load recipes.", SwingConstants.CENTER);
                    errorLabel.setForeground(Color.RED);
                    cardsPanel.add(errorLabel);
                } finally {
                    adjustCardLayout();
                }
            }
        };
        worker.execute();
    }

    /**
     * Helper method to adjust the number of columns in the recipe cards grid
     * based on the available panel width.
     */
    private void adjustCardLayout() {
        if (cardsPanel == null) {
            return;
        }
        
        int availableWidth = cardsPanel.getWidth();
        if (availableWidth <= 0) {
            return;
        }

        int idealColumns = availableWidth / (CARD_MIN_WIDTH + CARD_HORIZONTAL_GAP);
        int newColumns = Math.max(1, idealColumns);

        GridLayout layout = (GridLayout) cardsPanel.getLayout();
        if (layout.getColumns() != newColumns) {
            layout.setColumns(newColumns);
            cardsPanel.revalidate();
            cardsPanel.repaint();
        }
    }

    /**
     * Helper method to style buttons for a modern look.
     */
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (bgColor.equals(Color.WHITE)) {
            button.setBorder(BorderFactory.createLineBorder(new Color(234, 88, 12), 1));
        }
    }

    /**
     * Helper method to style buttons that act as links.
     */
    private void styleLinkButton(JButton button) {
        button.setBackground(null);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(251, 191, 36));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.WHITE);
            }
        });
    }

    /**
     * Helper method to create and add a recipe card to a panel.
     * Now accepts a RecipeData object.
     */
    private void addRecipeCard(JPanel parentPanel, RecipeData recipe) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout(0, 5));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        cardPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Image label
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 150));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Load image from URL in a separate thread to avoid freezing GUI
        new Thread(() -> {
            try {
                URL url = new URI(recipe.imageUrl).toURL();
                ImageIcon imageIcon = new ImageIcon(url);
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(200, 150,  java.awt.Image.SCALE_SMOOTH);
//                imageIcon = new ImageIcon(newimg);
//                SwingUtilities.invokeLater(() -> imageLabel.setIcon(imageIcon));
            } catch (IOException e) {
                System.err.println("Error loading image (IO): " + recipe.imageUrl + " - " + e.getMessage());
                SwingUtilities.invokeLater(() -> {
                    imageLabel.setText("No Image");
                    imageLabel.setForeground(Color.GRAY);
                    imageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                });
            } catch (URISyntaxException e) {
                System.err.println("Error loading image (URI Syntax): " + recipe.imageUrl + " - " + e.getMessage());
                SwingUtilities.invokeLater(() -> {
                    imageLabel.setText("Invalid Image URL");
                    imageLabel.setForeground(Color.RED);
                    imageLabel.setFont(new Font("Arial", Font.ITALIC, 10));
                });
            }
        }).start();

        cardPanel.add(imageLabel, BorderLayout.NORTH);

        // Text content panel for title and description
        JPanel textContentPanel = new JPanel();
        textContentPanel.setLayout(new BoxLayout(textContentPanel, BoxLayout.Y_AXIS));
        textContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        textContentPanel.setBackground(Color.WHITE);
        cardPanel.add(textContentPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(recipe.title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textContentPanel.add(titleLabel);
        textContentPanel.add(Box.createVerticalStrut(5));

        JTextArea descriptionArea = new JTextArea(recipe.description);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionArea.setForeground(new Color(102, 102, 102));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(null);
        descriptionArea.setBorder(null);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionArea.setPreferredSize(new Dimension(180, 50));
        descriptionArea.setMinimumSize(new Dimension(180, 50));
        descriptionArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        textContentPanel.add(descriptionArea);
        textContentPanel.add(Box.createVerticalStrut(5));

        JButton readMoreButton = new JButton("Read More →");
        readMoreButton.setFont(new Font("Arial", Font.PLAIN, 12));
        readMoreButton.setForeground(new Color(234, 88, 12));
        readMoreButton.setBackground(null);
        readMoreButton.setBorderPainted(false);
        readMoreButton.setContentAreaFilled(false);
        readMoreButton.setFocusPainted(false);
        readMoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        readMoreButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        textContentPanel.add(readMoreButton);

        readMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the RecipeDescriptionPage with this recipe's data
                RecipeDescriptionPage descriptionPage = new RecipeDescriptionPage(
                    recipe.title,
                    recipe.description,
                    recipe.imageUrl,
                    recipe.ingredients,
                    recipe.cookTime
                );
                descriptionPage.setVisible(true);
            }
        });

        parentPanel.add(cardPanel);
    }
}
// Updated RecipeLandingPage.java with steps support and cleaner loading



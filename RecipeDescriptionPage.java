package app.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter; // Added for image resizing
import java.awt.event.ComponentEvent;   // Added for image resizing
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays; // For simulating ingredient list
import java.util.List;    // For simulating ingredient list

/**
 * A dedicated page to display detailed information about a single recipe.
 * It features the recipe title, description, image, ingredients, and cook time.
 */
public class RecipeDescriptionPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Constructor for RecipeDescriptionPage.
     *
     * @param recipeTitle The title of the recipe.
     * @param recipeDescription A detailed description of the recipe.
     * @param imageUrl The URL of the recipe's image.
     * @param ingredients A list of ingredients for the recipe.
     * @param cookTime The estimated cook time for the recipe.
     */
    public RecipeDescriptionPage(String recipeTitle, String recipeDescription, String imageUrl, List<String> ingredients, String cookTime) {
        setTitle("Recipe Details: " + recipeTitle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes only this window, not the entire application
        setSize(800, 700); // Increased size for detailed content
        setMinimumSize(new Dimension(600, 600)); // Ensure minimum size for responsiveness
        setLocationRelativeTo(null); // Center on screen

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10)); // Added gaps between regions
        contentPane.setBackground(new Color(248, 250, 252)); // Light gray background
        setContentPane(contentPane);

        // --- Header Panel ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(234, 88, 12)); // Orange header
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        headerPanel.setLayout(new BorderLayout());
        contentPane.add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel(recipeTitle, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // --- Main Content Area (Scrollable) ---
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS)); // Stack elements vertically
        mainContentPanel.setBackground(new Color(255, 255, 255)); // White background for content
        mainContentPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding inside the main content
        scrollPane.setViewportView(mainContentPanel);

        // Recipe Image
        JLabel recipeImageLabel = new JLabel();
        recipeImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recipeImageLabel.setPreferredSize(new Dimension(600, 350)); // Larger image area
        recipeImageLabel.setMinimumSize(new Dimension(400, 250));
        recipeImageLabel.setMaximumSize(new Dimension(700, 400));
        recipeImageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Subtle border
        recipeImageLabel.setOpaque(true);
        recipeImageLabel.setBackground(Color.LIGHT_GRAY.brighter());
        recipeImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recipeImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        mainContentPanel.add(recipeImageLabel);
        mainContentPanel.add(Box.createVerticalStrut(20)); // Spacer

        // Load image in background
        new Thread(() -> {
            try {
                URL url = new URI(imageUrl).toURL();
                ImageIcon imageIcon = new ImageIcon(url);
                Image image = imageIcon.getImage();
                // Scale the image to fit the label, maintaining aspect ratio
                // Use getWidth() and getHeight() from recipeImageLabel itself for dynamic sizing
                Image scaledImage = image.getScaledInstance(
                    recipeImageLabel.getWidth() > 0 ? recipeImageLabel.getWidth() : 600, // Fallback if 0
                    recipeImageLabel.getHeight() > 0 ? recipeImageLabel.getHeight() : 350, // Fallback if 0
                    Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                SwingUtilities.invokeLater(() -> recipeImageLabel.setIcon(scaledImageIcon));
            } catch (IOException | URISyntaxException e) {
                System.err.println("Error loading image for description page: " + imageUrl + " - " + e.getMessage());
                SwingUtilities.invokeLater(() -> {
                    recipeImageLabel.setText("Image Failed to Load");
                    recipeImageLabel.setForeground(Color.RED);
                    recipeImageLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                });
            }
        }).start();


        // Recipe Description
        JLabel descriptionHeader = new JLabel("Description", SwingConstants.LEFT);
        descriptionHeader.setFont(new Font("Arial", Font.BOLD, 20));
        descriptionHeader.setForeground(new Color(51, 51, 51));
        descriptionHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainContentPanel.add(descriptionHeader);
        mainContentPanel.add(Box.createVerticalStrut(5));

        JTextArea descriptionArea = new JTextArea(recipeDescription);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setForeground(new Color(68, 68, 68));
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(null);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Padding
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainContentPanel.add(descriptionArea);
        mainContentPanel.add(Box.createVerticalStrut(20));

        // Ingredients Section
        JLabel ingredientsHeader = new JLabel("Ingredients", SwingConstants.LEFT);
        ingredientsHeader.setFont(new Font("Arial", Font.BOLD, 20));
        ingredientsHeader.setForeground(new Color(51, 51, 51));
        ingredientsHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainContentPanel.add(ingredientsHeader);
        mainContentPanel.add(Box.createVerticalStrut(5));

        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.setBackground(Color.WHITE);
        ingredientsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainContentPanel.add(ingredientsPanel);

        if (ingredients != null && !ingredients.isEmpty()) {
            for (String ingredient : ingredients) {
                JLabel ingredientLabel = new JLabel("• " + ingredient);
                ingredientLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                ingredientLabel.setForeground(new Color(68, 68, 68));
                ingredientLabel.setBorder(new EmptyBorder(2, 0, 2, 0)); // Small vertical padding
                ingredientLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                ingredientsPanel.add(ingredientLabel);
            }
        } else {
            JLabel noIngredientsLabel = new JLabel("No ingredients listed.");
            noIngredientsLabel.setFont(new Font("Arial", Font.ITALIC, 14));
            noIngredientsLabel.setForeground(Color.GRAY);
            noIngredientsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            ingredientsPanel.add(noIngredientsLabel);
        }
        mainContentPanel.add(Box.createVerticalStrut(20));

        // Cook Time Section
        JLabel cookTimeHeader = new JLabel("Cook Time", SwingConstants.LEFT);
        cookTimeHeader.setFont(new Font("Arial", Font.BOLD, 20));
        cookTimeHeader.setForeground(new Color(51, 51, 51));
        cookTimeHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainContentPanel.add(cookTimeHeader);
        mainContentPanel.add(Box.createVerticalStrut(5));

        JLabel cookTimeLabel = new JLabel(cookTime);
        cookTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cookTimeLabel.setForeground(new Color(68, 68, 68));
        cookTimeLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
        cookTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainContentPanel.add(cookTimeLabel);
        mainContentPanel.add(Box.createVerticalStrut(20));

        // Adding a back button for navigation
        JButton backButton = new JButton("← Back to Recipes");
        styleButton(backButton, new Color(234, 88, 12), Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(backButton);
        backButton.addActionListener(e -> dispose()); // Close this window

        // Adjust image size when the panel is resized (more dynamic image scaling)
        recipeImageLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Only try to re-scale if there's an actual image icon
                if (recipeImageLabel.getIcon() instanceof ImageIcon) {
                    ImageIcon currentIcon = (ImageIcon) recipeImageLabel.getIcon();
                    Image originalImage = currentIcon.getImage();

                    int labelWidth = recipeImageLabel.getWidth();
                    int labelHeight = recipeImageLabel.getHeight();

                    if (labelWidth > 0 && labelHeight > 0) { // Avoid division by zero
                        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                        SwingUtilities.invokeLater(() -> recipeImageLabel.setIcon(new ImageIcon(scaledImage)));
                    }
                }
            }
        });
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
        // Add a subtle border for white buttons for better visibility
        if (bgColor.equals(Color.WHITE)) {
            button.setBorder(BorderFactory.createLineBorder(new Color(234, 88, 12), 1));
        }
    }

    // You can add a main method here for standalone testing of this page if needed
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Dummy data for testing
                List<String> ingredients = Arrays.asList(
                    "2 cups all-purpose flour",
                    "1 tbsp baking powder",
                    "1/2 tsp salt",
                    "1/4 cup granulated sugar",
                    "1 large egg",
                    "1 cup milk",
                    "2 tbsp melted butter"
                );
                RecipeDescriptionPage frame = new RecipeDescriptionPage(
                    "Fluffy Pancakes",
                    "These pancakes are incredibly light and fluffy, perfect for a weekend breakfast or brunch. Serve with your favorite syrup and fresh berries.",
                    "https://placehold.co/600x350/FFD700/000000?text=Fluffy+Pancakes",
                    ingredients,
                    "25 minutes"
                );
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    */
}

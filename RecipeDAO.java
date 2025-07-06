
package app.ui;

import app.ui.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import app.ui.RecipeData;

/**
 * Handles all database operations for Recipes, specifically for a MySQL database.
 * It uses transactions to ensure data consistency across multiple table insertions.
 */
public class RecipeDAO {

    public void addRecipe(String title, String description, int cookTimeMins, String instructions,
                          List<String> ingredientsList, String tagsList, String imageUrl, int userId) throws SQLException {

        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            conn.setAutoCommit(false);

            long recipeId = insertRecipe(conn, title, description, cookTimeMins, instructions, userId);
            processIngredients(conn, recipeId, ingredientsList);
            processTags(conn, recipeId, tagsList);

            if (imageUrl != null && !imageUrl.trim().isEmpty()) {
                insertRecipeImage(conn, recipeId, imageUrl);
            }

            conn.commit();
            System.out.println("Transaction committed. Successfully added recipe with ID: " + recipeId);

        } catch (SQLException e) {
            System.err.println("Transaction is being rolled back. Error: " + e.getMessage());
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    private long insertRecipe(Connection conn, String title, String description, int cookTimeMins, String instructions, int userId) throws SQLException {
        String sql = "INSERT INTO recipes (title, description, cook_time_mins, instructions, user_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, NOW(), NOW())";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setInt(3, cookTimeMins);
            pstmt.setString(4, instructions);
            pstmt.setInt(5, userId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Creating recipe failed, no rows affected.");

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getLong(1);
                else throw new SQLException("Creating recipe failed, no ID obtained.");
            }
        }
    }

    private void processIngredients(Connection conn, long recipeId, List<String> ingredientsList) throws SQLException {
        String sql = "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String ingredientLine : ingredientsList) {
                if (ingredientLine == null || ingredientLine.trim().isEmpty()) continue;

                String[] parts = ingredientLine.trim().split("\\s+", 3);
                Double quantity = null;
                String unit = null;
                String name;

                if (parts.length >= 2) {
                    try {
                        quantity = Double.parseDouble(parts[0]);
                        unit = parts[1];
                        name = (parts.length == 3) ? parts[2] : "";
                    } catch (NumberFormatException e) {
                        name = ingredientLine;
                    }
                } else {
                    name = ingredientLine;
                }

                long ingredientId = getOrCreateId(conn, "ingredients", "ingredient_id", "name", name);
                pstmt.setLong(1, recipeId);
                pstmt.setLong(2, ingredientId);
                if (quantity != null) pstmt.setDouble(3, quantity);
                else pstmt.setNull(3, Types.FLOAT);
                pstmt.setString(4, unit);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    private void processTags(Connection conn, long recipeId, String tagsList) throws SQLException {
        if (tagsList == null || tagsList.trim().isEmpty()) return;

        List<String> tags = Arrays.stream(tagsList.split(","))
                                  .map(String::trim)
                                  .filter(tag -> !tag.isEmpty())
                                  .collect(Collectors.toList());

        String sql = "INSERT INTO recipe_tags (recipe_id, tag_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String tagName : tags) {
                long tagId = getOrCreateId(conn, "tags", "tag_id", "name", tagName);
                pstmt.setLong(1, recipeId);
                pstmt.setLong(2, tagId);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    private long getOrCreateId(Connection conn, String tableName, String idColumnName, String nameColumnName, String nameValue) throws SQLException {
        if (nameValue == null || nameValue.trim().isEmpty()) throw new SQLException(nameColumnName + " cannot be empty.");

        String selectSql = "SELECT " + idColumnName + " FROM " + tableName + " WHERE " + nameColumnName + " = ?";
        try (PreparedStatement selectPstmt = conn.prepareStatement(selectSql)) {
            selectPstmt.setString(1, nameValue);
            ResultSet rs = selectPstmt.executeQuery();
            if (rs.next()) return rs.getLong(idColumnName);
        }

        String insertSql = ("ingredients".equals(tableName))
            ? "INSERT INTO " + tableName + " (" + nameColumnName + ", created_at) VALUES (?, NOW())"
            : "INSERT INTO " + tableName + " (" + nameColumnName + ") VALUES (?)";

        try (PreparedStatement insertPstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            insertPstmt.setString(1, nameValue);
            insertPstmt.executeUpdate();
            ResultSet rs = insertPstmt.getGeneratedKeys();
            if (rs.next()) return rs.getLong(1);
            else throw new SQLException("Failed to insert new record into " + tableName + " for value " + nameValue);
        }
    }

    private void insertRecipeImage(Connection conn, long recipeId, String imageUrl) throws SQLException {
        String sql = "INSERT INTO recipe_images (recipe_id, image_url, uploaded_at) VALUES (?, ?, NOW())";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, recipeId);
            pstmt.setString(2, imageUrl);
            pstmt.executeUpdate();
        }
    }

//    public static List<RecipeLandingPage.RecipeData> getTopRecipes(int limit) throws SQLException {

    public static List<RecipeData> getTopRecipes(int limit) throws SQLException {
        List<RecipeData> recipes = new ArrayList<>();
        Connection conn = null;

        try {
            conn = DBConnector.getConnection();
            String sql = "SELECT r.title, r.description, ri.image_url, r.cook_time_mins " +
                         "FROM recipes r " +
                         "LEFT JOIN recipe_images ri ON r.recipe_id = ri.recipe_id " +
                         "ORDER BY r.created_at DESC LIMIT ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, limit);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String imageUrl = rs.getString("image_url");
                    String cookTime = rs.getString("cook_time_mins");
                    recipes.add(new RecipeData(title, description, imageUrl, null, cookTime));
                }
            }
        } finally {
            if (conn != null) conn.close();
        }

        return recipes;
    }
    
    public static List<RecipeData> searchRecipes(String keyword) throws SQLException {
        List<RecipeData> list = new ArrayList<>();
        String sql = "SELECT r.recipe_id, r.title, r.description, ri.image_url, r.cook_time_mins " +
                     "FROM recipes r " +
                     "LEFT JOIN recipe_images ri ON r.recipe_id = ri.recipe_id " +
                     "WHERE r.title LIKE ? OR r.description LIKE ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int recipeId = rs.getInt("recipe_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String imageUrl = rs.getString("image_url");
                String cookTime = rs.getString("cook_time_mins");

                List<String> ingredients = fetchIngredients(recipeId);

                RecipeData r = new RecipeData(title, description, imageUrl, ingredients, cookTime);
                list.add(r);
            }
        }
        return list;
    }

 // Search Recipes by keyword (title or description)
    public static List<String> fetchIngredients(int recipeId) throws SQLException {
        List<String> ingredients = new ArrayList<>();

        String sql = "SELECT i.name, ri.quantity, ri.unit " +
                     "FROM recipe_ingredients ri " +
                     "JOIN ingredients i ON ri.ingredient_id = i.ingredient_id " +
                     "WHERE ri.recipe_id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                double quantity = rs.getDouble("quantity");
                String unit = rs.getString("unit");

                String formatted = "";
                if (quantity > 0) formatted += quantity + " ";
                if (unit != null && !unit.isEmpty()) formatted += unit + " ";
                formatted += name;

                ingredients.add(formatted.trim());
            }
        }

        return ingredients;
    }



    
    
}

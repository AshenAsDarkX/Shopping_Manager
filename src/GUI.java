import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

class GUI extends JFrame {
//    Initializing varibles
    private final JTable table;
    private final JPanel detailsPanel;
    private final Stock stock;
    private final ShoppingCart shoppingCart;


    public GUI(Stock stock) {
//        cloning the stock from main class
        this.stock = stock;
        shoppingCart = new ShoppingCart();

        setTitle("Westminster Shopping Centre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create dropdown menu
        String[] categories = {"All", "Electronics", "Clothing"};
        JComboBox<String> categoryList = new JComboBox<>(categories);
        categoryList.addActionListener(e -> filterTableByCategory((String) categoryList.getSelectedItem()));



        // Creating a panel for the dropdown menu and center it
        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dropdownPanel.add(categoryList);
        add(dropdownPanel, BorderLayout.NORTH);

        // Creating shopping cart button
        JButton viewCartButton = new JButton("Shopping Cart");
        viewCartButton.addActionListener(e -> viewShoppingCart()); // Add an action listener to handle the button click
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(categoryList);
        topPanel.add(viewCartButton);
        add(topPanel, BorderLayout.NORTH);

        // Creating table
        String[] columnNames = {"Product ID", "Name", "quantity", "Price(£)", "Type"};
        table = new JTable(new DefaultTableModel(columnNames, 0));
        populateTableFromStock((DefaultTableModel) table.getModel(), stock); // Populate the table from the Stock

        // Add a ListSelectionListener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1 && detailsPanel != null) {
                        updateDetailsPanel(selectedRow, stock);
                    }
                }
            }
        });

        // Create a custom renderer for the quantity column
        DefaultTableCellRenderer quantityRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Check if the quantity column is being rendered
                if (column == 2) {
                    int quantity = Integer.parseInt(value.toString());
                    if (quantity < 3) {
                        // Change font color to red for quantities below 50
                        cellComponent.setForeground(Color.RED);
                    } else {
                        // Reset font color to default for other quantities
                        cellComponent.setForeground(table.getForeground());
                    }
                }

                return cellComponent;
            }
        };

        table.getColumnModel().getColumn(2).setCellRenderer(quantityRenderer);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Create product details panel with left alignment
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.add(new JLabel("Selected Product - Details"));
        detailsPanel.add(createDetailLabel("Product Id: -"));
        detailsPanel.add(createDetailLabel("Category: -"));
        detailsPanel.add(createDetailLabel("Name: -"));
        detailsPanel.add(createDetailLabel("Size: -"));
        detailsPanel.add(createDetailLabel("Colour: -"));
        detailsPanel.add(createDetailLabel("Items Available :-"));
        add(detailsPanel, BorderLayout.SOUTH);

        // Create a panel for the "Add to Shopping Cart" button and center it
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(e -> addToCartClicked());
        buttonPanel.add(addToCartButton);
        detailsPanel.add(buttonPanel);

        pack();
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setVisible(true);
    }


    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void populateTableFromStock(DefaultTableModel model, Stock stock) {
        List<String[]> productDetailsList = stock.getProductDetailsList();

        for (String[] rowData : productDetailsList) {
            model.addRow
                    (rowData);
        }
    }

    private void updateDetailsPanel(int selectedRow, Stock stock) {
        if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
            // Get data from the selected row
            String productId = (String) table.getValueAt(selectedRow, 0);
            String name = (String) table.getValueAt(selectedRow, 1);
            String quantity = (String) table.getValueAt(selectedRow, 2);
            String price = (String) table.getValueAt(selectedRow, 3);
            String category = (String) table.getValueAt(selectedRow, 4);
            Product selectedProduct = stock.getProObject(productId);

            // Update the product details panel
            detailsPanel.removeAll(); // Clear existing components
            detailsPanel.add(new JLabel("Selected Product - Details"));
            detailsPanel.add(createDetailLabel("Product Id: " + productId));
            detailsPanel.add(createDetailLabel("Category: " + category));
            detailsPanel.add(createDetailLabel("Name: " + name));
            detailsPanel.add(createDetailLabel("Quantity: " + quantity));
            detailsPanel.add(createDetailLabel("Price: " + price));

            // ... add other details as needed
            if (selectedProduct instanceof Electronics) {
                int warranty = ((Electronics) selectedProduct).getWarrPeriod();
                detailsPanel.add(createDetailLabel("Warranty: " + warranty));
            }
            if (selectedProduct instanceof Clothes) {
                String size = ((Clothes) selectedProduct).getSize();
                detailsPanel.add(createDetailLabel("Size: " + size));
            }


            detailsPanel.revalidate(); // Update the layout
            detailsPanel.repaint(); // Repaint the panel


            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton addToCartButton = new JButton("Add to Shopping Cart");
            addToCartButton.addActionListener(e -> addToCartClicked());
            buttonPanel.add(addToCartButton);
            detailsPanel.add(buttonPanel);
        }
    }

    private void addToCartClicked() { //Method awakens when add to cart button clicked
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String productId = (String) table.getValueAt(selectedRow, 0);
            Product selectedProduct = stock.getProObject(productId);

            if (selectedProduct != null) {
                shoppingCart.addProduct(productId, selectedProduct);
                JOptionPane.showMessageDialog(this, "Product added to the shopping cart!");

                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                tableModel.removeRow(selectedRow);

                // Set the madePurchase flag when a product is added to the cart
                if (!shoppingCart.hasMadePurchase()) {
                    shoppingCart.setMadePurchase(true);
                }
            }
        }
    }

    private void viewShoppingCart() {// Method handles the viewing cart
        JFrame cartFrame = new JFrame("Shopping Cart");
        cartFrame.setLayout(new BorderLayout());

        String[] cartColumns = {"Product ID", "Name", "Quantity", "Price"};
        JTable cartTable = new JTable(new DefaultTableModel(cartColumns, 0));

        DefaultTableModel cartTableModel = (DefaultTableModel) cartTable.getModel();
        List<String[]> cartDetailsList = shoppingCart.getProductDetailsList();
        for (String[] rowData : cartDetailsList) {
            cartTableModel.addRow(rowData);
        }

        cartFrame.add(new JScrollPane(cartTable), BorderLayout.CENTER);

        // Calculate total with discount
        double totalWithDiscount = calculateTotalWithDiscount();

        // Display first purchase discount, three purchases in the same category discount, and total
        JPanel discountPanel = new JPanel(new GridLayout(3, 2));
        discountPanel.add(new JLabel("First Purchase Discount: "));
        discountPanel.add(new JLabel(shoppingCart.hasMadePurchase() ? "10%" : "0%"));
        discountPanel.add(new JLabel("Three times in same category Discount: "));
        discountPanel.add(new JLabel(shoppingCart.hasMadeThreePurchasesInSameCategory("All") ? "5%" : "0%"));
        discountPanel.add(new JLabel("Total: "));
        discountPanel.add(new JLabel(String.valueOf(totalWithDiscount)));

        cartFrame.add(discountPanel, BorderLayout.NORTH);

        cartFrame.setSize(400, 300);
        cartFrame.setLocationRelativeTo(this);
        cartFrame.setVisible(true);
    }

    private double calculateTotalWithDiscount() { // Method for calculate total with discount
        double total = 0;

        for (Map.Entry<String, Product> entry : shoppingCart.getProList().entrySet()) {
            Product product = entry.getValue();
            double price = product.getProPrice();
            int quantity = product.getQuantity();
            total += price * quantity;
        }

        // Apply first purchase discount
        if (shoppingCart.hasMadePurchase()) {
            total *= 0.9; // 10% discount
        }

        // Apply three purchases in the same category discount
        if (shoppingCart.hasMadeThreePurchasesInSameCategory("All")) {
            total *= 0.95; // 5% discount
        }

        return total;
    }

    private void filterTableByCategory(String selectedCategory) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Clear the table

        if ("All".equals(selectedCategory)) {
            // Display all products
            populateTableFromStock(tableModel, stock);
        } else {
            // Display products based on the selected category
            for (Product product : stock.getProList().values()) {
                if (("Electronics".equals(selectedCategory) && product instanceof Electronics) ||
                        ("Clothing".equals(selectedCategory) && product instanceof Clothes)) {
                    Object[] rowData = {product.getProductId(), product.getProductName(), product.getQuantity(),
                            product.getProPrice(), product.getClass().getSimpleName()};
                    tableModel.addRow(rowData);
                }
            }
        }
    }




}

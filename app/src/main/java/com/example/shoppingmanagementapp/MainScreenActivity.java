package com.example.shoppingmanagementapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainScreenActivity extends AppCompatActivity {

    private static final int ADD_PRODUCT_REQUEST = 1;
    private final ArrayList<Product> productList = new ArrayList<>();
    private LinearLayout productContainer;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        TextView welcomeTextView = findViewById(R.id.welcome_text);
        welcomeTextView.setText("Hello! welcome to ShoppingCart, " + username + "!");

        productContainer = findViewById(R.id.product_container);
        Button addProductButton = findViewById(R.id.add_product_button);
        Button deleteProductButton = findViewById(R.id.delete_product_button);

        addProductButton.setOnClickListener(v -> {
            Intent addProductIntent = new Intent(MainScreenActivity.this, AddProductActivity.class);
            startActivityForResult(addProductIntent, ADD_PRODUCT_REQUEST);
        });

        deleteProductButton.setOnClickListener(v -> {
            ArrayList<Product> productsToRemove = new ArrayList<>();
            for (int i = 0; i < productContainer.getChildCount(); i++) {
                View productView = productContainer.getChildAt(i);
                CheckBox checkBox = productView.findViewById(R.id.product_checkbox);
                if (checkBox.isChecked()) {
                    productsToRemove.add(productList.get(i));
                }
            }

            productList.removeAll(productsToRemove);
            refreshProductList();
            Toast.makeText(this, "Selected products were removed from cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK) {
            String productName = data.getStringExtra("productName");
            int productQuantity = data.getIntExtra("productQuantity", 0);
            Product newProduct = new Product(productName, productQuantity);
            productList.add(newProduct);
            refreshProductList();
        }
    }

    private void refreshProductList() {
        productContainer.removeAllViews();
        for (Product product : productList) {
            @SuppressLint("InflateParams") View productView = getLayoutInflater().inflate(R.layout.product_item, null);
            TextView productNameTextView = productView.findViewById(R.id.product_name_text);
            TextView productQuantityTextView = productView.findViewById(R.id.product_quantity_text);
            CheckBox productCheckBox = productView.findViewById(R.id.product_checkbox);

            productNameTextView.setText(product.getName());
            productQuantityTextView.setText("Quantity: " + product.getQuantity());
            productCheckBox.setChecked(product.isSelected());

            productContainer.addView(productView);
        }
    }
}

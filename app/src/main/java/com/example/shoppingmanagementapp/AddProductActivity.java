package com.example.shoppingmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    private EditText productNameEditText, productQuantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productNameEditText = findViewById(R.id.product_name);
        productQuantityEditText = findViewById(R.id.product_quantity);
        Button addProductButton = findViewById(R.id.add_product_button);

        addProductButton.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString();
            String productQuantityString = productQuantityEditText.getText().toString();

            if (productName.isEmpty() || productQuantityString.isEmpty()) {
                Toast.makeText(AddProductActivity.this, "Enter product + quantity", Toast.LENGTH_SHORT).show();
                return;
            }

            int productQuantity = Integer.parseInt(productQuantityString);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("productName", productName);
            resultIntent.putExtra("productQuantity", productQuantity);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}

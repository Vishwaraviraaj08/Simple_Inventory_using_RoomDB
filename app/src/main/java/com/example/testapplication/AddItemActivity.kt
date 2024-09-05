package com.example.testapplication

import com.example.testapplication.Model.AdditionalInfo
import com.example.testapplication.Model.StockInfo
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.testapplication.Model.Item
import kotlinx.coroutines.launch

class AddItemActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item)

        itemViewModel = ItemViewModel(application)

        val spinnerAccountType: Spinner = findViewById(R.id.spinnerSalesAccount)
        val spinnerPurchaseAccount: Spinner = findViewById(R.id.spinnerPurchaseAccount)
        val checkBoxInventoryTrack: CheckBox = findViewById(R.id.checkBoxInventoryTrack)
        val linearLayoutInventory: LinearLayout = findViewById(R.id.trackInventorylayout)
        val checkBoxAdditionalInfo: CheckBox = findViewById(R.id.checkBoxAdditionalInfo)
        val linearLayoutAdditionalInfo: LinearLayout = findViewById(R.id.linearLayoutAdditionalInfo)

        checkBoxInventoryTrack.setOnCheckedChangeListener { _ , isChecked ->
            println()
            linearLayoutInventory.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        checkBoxAdditionalInfo.setOnCheckedChangeListener { _, isChecked ->
            linearLayoutAdditionalInfo.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        val accountTypeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.account_types,
            R.layout.spinner_item
        )
        spinnerAccountType.adapter = accountTypeAdapter

        val purchaseAccountAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.account_types,
            R.layout.spinner_item
        )
        spinnerPurchaseAccount.adapter = purchaseAccountAdapter

        val dimensionUnitAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.dimension_units,
            R.layout.spinner_item
        )
        findViewById<Spinner>(R.id.spinnerDimensionUnit).adapter = dimensionUnitAdapter

        val weightUnitAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.weight_units,
            R.layout.spinner_item
        )
        findViewById<Spinner>(R.id.spinnerWeightUnit).adapter = weightUnitAdapter

        val saveButton = findViewById<Button>(R.id.buttonSave)
        saveButton.setOnClickListener {
            gatherData()
        }

    }




    fun gatherData() {
        val itemName = findViewById<EditText>(R.id.editTextItemName).text.toString()
        val sku = findViewById<EditText>(R.id.editTextSKU).text.toString()
        val noOfUnits = findViewById<EditText>(R.id.editTextNoOfUnits).text.toString().toDoubleOrNull() ?: 0.0
        val sellingPrice = findViewById<EditText>(R.id.editTextSellingPrice).text.toString().toDoubleOrNull() ?: 0.0
        val itemAccount = findViewById<Spinner>(R.id.spinnerSalesAccount).selectedItem.toString()
        val costPrice = findViewById<EditText>(R.id.editTextCostPrice).text.toString().toDoubleOrNull() ?: 0.0
        val trackInventory = findViewById<CheckBox>(R.id.checkBoxInventoryTrack).isChecked

        val stockSummary = if (trackInventory) {
            val openingStock = findViewById<EditText>(R.id.editTextOpeningStock).text.toString().toDoubleOrNull() ?: 0.0
            val openingStockRate = findViewById<EditText>(R.id.editTextOpeningStockRate).text.toString().toDoubleOrNull() ?: 0.0
            val reorderLevel = findViewById<EditText>(R.id.editTextReorderLevel).text.toString().toDoubleOrNull() ?: 0.0


            StockInfo(
                physicalStockOnHand = openingStock,
                physicalCommittedStock = openingStockRate,
                physicalAvailableForSale = openingStock,
                accountingStockOnHand = openingStock,
                accountingCommittedStock = openingStockRate
            )

        } else {
            StockInfo(10, 0.0, 0.0, 0.0, 0.0, 0.0)
        }

        val additionalInfo = if (findViewById<CheckBox>(R.id.checkBoxAdditionalInfo).isChecked) {
            AdditionalInfo(
                length = findViewById<EditText>(R.id.editTextLength).text.toString().toDouble(),
                breadth = findViewById<EditText>(R.id.editTextBreadth).text.toString().toDouble(),
                height = findViewById<EditText>(R.id.editTextHeight).text.toString().toDouble(),
                dimensionUnit = findViewById<Spinner>(R.id.spinnerDimensionUnit).selectedItem.toString(),
                weight = findViewById<EditText>(R.id.editTextWeight).text.toString().toDouble(),
                weightUnit = findViewById<Spinner>(R.id.spinnerWeightUnit).selectedItem.toString(),
                mpn = findViewById<EditText>(R.id.editTextMPN).text.toString(),
                isbn = findViewById<EditText>(R.id.editTextISBN).text.toString(),
                upc = findViewById<EditText>(R.id.editTextUPC).text.toString()
            )
        } else null

        val item = Item(
            name = itemName,
            sellingPrice = sellingPrice,
            costPrice = costPrice,
            quantity = noOfUnits,
            stockSummary = stockSummary,
            itemAccount = itemAccount,
            openingStock = if (trackInventory) findViewById<EditText>(R.id.editTextOpeningStock).text.toString().toDoubleOrNull() ?: 0.0 else 0.0,
            reorderLevel = if (trackInventory) findViewById<EditText>(R.id.editTextReorderLevel).text.toString().toDoubleOrNull() ?: 0.0 else 0.0,
            additionalInfo = additionalInfo
        )


        lifecycleScope.launch {
            itemViewModel.insertItem(item)
        }





        Toast.makeText(this, "Item saved successfully", Toast.LENGTH_SHORT).show()

        finish()
    }
}

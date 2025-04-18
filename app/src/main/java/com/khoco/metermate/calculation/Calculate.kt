import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.khoco.metermate.model.PowerRates
import com.khoco.metermate.network.RetrofitInstance
import com.khoco.metermate.ui.theme.Background
import com.khoco.metermate.ui.theme.BlackTheme
import com.khoco.metermate.ui.theme.RedTheme
import com.khoco.metermate.ui.theme.YellowTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calculation() {
    // User inputs and result stored as mutable state
    var previousReading by remember { mutableStateOf("") }
    var currentReading by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("On-Grid") }
    var totalBill by remember { mutableStateOf<Double?>(null) }
    var powerRates by remember { mutableStateOf<PowerRates?>(null) }

    LaunchedEffect(true) {
        try {
            powerRates = RetrofitInstance.api.getPowerRates()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp) // vertical spacing between children
    ) {
        // Title
        Text("BOHECO I - Bill Calculator", style = MaterialTheme.typography.titleMedium)

        //  Current Date
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        val formattedDate = currentDate.format(formatter)
        Text("Date: $formattedDate", style = MaterialTheme.typography.bodyMedium)

        // Dropdown menu for connection type
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Connection Type:")
            DropdownMenuBox(selectedType) {
                selectedType = it
            }
        }

        // Previous Reading input
        OutlinedTextField(
            value = previousReading,
            onValueChange = { previousReading = it },
            label = { Text("Previous Reading (kWh)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Background,
                focusedBorderColor = BlackTheme,
                focusedLabelColor = BlackTheme
            )
        )

        // Current Reading input
        OutlinedTextField(
            value = currentReading,
            onValueChange = { currentReading = it },
            label = { Text("Current Reading (kWh)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Background,
                focusedBorderColor = BlackTheme,
                focusedLabelColor = BlackTheme
            )
        )

        // Button to calculate the bill
        Button(
            onClick = {
                // Convert readings to Double (safe fallback to 0.0 if invalid)
                val prev = previousReading.toDoubleOrNull() ?: 0.0
                val curr = currentReading.toDoubleOrNull() ?: 0.0
                val unitsUsed = (curr - prev).coerceAtLeast(0.0) // Avoid negative usage

                // Fixed charges and rates
                val fixedCharge = powerRates?.fixedCharge ?: 0.0
                val ratePerKwh = if (selectedType == "On-Grid") powerRates?.onGridRate ?: 0.0 else powerRates?.offGridRate ?: 0.0

                // Calculate total bill
                if(selectedType == "On-Grid"){
                    totalBill = (unitsUsed * ratePerKwh) + fixedCharge
                }else if (selectedType == "Off-Grid"){
                    totalBill = (unitsUsed * ratePerKwh)
                }

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = RedTheme,
                contentColor = YellowTheme
            )
        ) {
            Text("Calculate")
        }

        // Display result only if calculated
        totalBill?.let {
            Text("Total Bill: ₱%.2f".format(it), style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
fun DropdownMenuBox(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) } // Is the menu expanded?
    val options = listOf("On-Grid", "Off-Grid")

    Box {
        // Button that opens the dropdown
        OutlinedButton(onClick = { expanded = true }, colors = ButtonDefaults.outlinedButtonColors(contentColor = BlackTheme),) {
            Text(selectedOption)
        }

        // Dropdown with options
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false // close menu on selection
                    }
                )
            }
        }
    }
}

package app.features.categorycreation.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.base.ui.composables.MediumSpace
import app.features.categorycreation.R

/**
 * Fungible selection field
 *
 * @param isFungible
 * @param onSelectionChange
 * @receiver
 */
@Composable
fun FungibleSelectionField(
    isFungible: Boolean?,
    onSelectionChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MediumSpace()
        Text(
            text = stringResource(R.string.fungible),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isFungible == true,
                    onClick = { onSelectionChange(true) }
                )
                Text(
                    text = stringResource(R.string.si),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isFungible == false,
                    onClick = { onSelectionChange(false) }
                )
                Text(
                    text = stringResource(R.string.no),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
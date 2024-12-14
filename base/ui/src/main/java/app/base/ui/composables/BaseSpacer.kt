package app.base.ui.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.base.ui.Separations

@Composable
fun SmallSpace() = Spacer(modifier = Modifier.size(Separations.Small))

@Composable
fun MediumSpace() = Spacer(modifier = Modifier.size(Separations.Medium))

@Composable
fun HighSpace() = Spacer(modifier = Modifier.size(Separations.High))
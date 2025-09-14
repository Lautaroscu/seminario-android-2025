import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ar.edu.unicen.seminario2025.ui.features.games.enums.SortOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameFiltersAndSort(
    years: List<Int>,
    platforms: List<Pair<Int, String>>,
    onApplyFilters: (FiltersDTO) -> Unit,
    savedFilters: FiltersDTO? = null,
    modifier: Modifier = Modifier
) {
    var expandedYear by remember { mutableStateOf(false) }
    var selectedYear: String? by remember {
        mutableStateOf(savedFilters?.year)
    }

    var minRating by remember { mutableFloatStateOf(savedFilters?.minRating ?: 0f) }
    val selectedPlatforms = remember {
        mutableStateListOf<Int>().apply {
            savedFilters?.platforms?.let { addAll(it) }
        }
    }

    var selectedSort by remember {
        mutableStateOf(savedFilters?.order ?: SortOption.RATING_DESC)
    }
    val context = LocalContext.current
    var expandedSort by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ExposedDropdownMenuBox(
            expanded = expandedYear,
            onExpandedChange = { expandedYear = !expandedYear }
        ) {
            TextField(
                value = selectedYear?.toString() ?: "Todos",
                onValueChange = {},
                readOnly = true,
                label = { Text("Año de lanzamiento") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedYear) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expandedYear,
                onDismissRequest = { expandedYear = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Todos") },
                    onClick = {
                        selectedYear = null
                        expandedYear = false
                    }
                )
                years.forEach { year ->
                    DropdownMenuItem(
                        text = {Text(year.toString())
                        },
                        onClick = {
                            selectedYear = year.toString()
                            expandedYear = false
                        }
                    )
                }
            }
        }

        Column {
            Text("Rating mínimo: ${minRating.toInt()}")
            Slider(
                value = minRating,
                onValueChange = { minRating = it },
                valueRange = 0f..5f,
                steps = 4
            )
        }

        Column {
            Text("Plataformas")
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                platforms.forEach { platform ->
                    val selected = selectedPlatforms.contains(platform.first)
                    FilterChip(
                        selected = selected,
                        onClick = {
                            if (selected) selectedPlatforms.remove(platform.first)
                            else selectedPlatforms.add(platform.first)
                        },
                        label = { Text(platform.second) }
                    )
                }
            }


            ExposedDropdownMenuBox(
                expanded = expandedSort,
                onExpandedChange = { expandedSort = !expandedSort }
            ) {
                TextField(
                    value = context.getString(selectedSort.labelRes),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Ordenar por") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedSort) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedSort,
                    onDismissRequest = { expandedSort = false }
                ) {
                    SortOption.entries.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(context.getString(option.labelRes)) },
                            onClick = {
                                selectedSort = option
                                expandedSort = false
                            }
                        )
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp , bottom = 4.dp),
            ) {
                Button(
                    onClick = {
                        onApplyFilters(
                            FiltersDTO(
                                year = selectedYear,
                                minRating = minRating.let { if (it == 0f) null else it },
                                platforms = selectedPlatforms.let { if (it.isEmpty()) null else it },
                                order = selectedSort
                            )
                        )
                    }
                ) {
                    Text("Aplicar")
                }
                OutlinedButton(
                    onClick = {
                        onApplyFilters(
                            FiltersDTO(
                                year = null,
                                minRating = null,
                                platforms = null,
                                order = null
                            )
                        )
                        selectedYear = null
                        minRating = 0f
                        selectedPlatforms.clear()
                        selectedSort = SortOption.RATING_DESC
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)
                ) {
                    Text("Borrar filtros")
                }
            }


        }
    }
}

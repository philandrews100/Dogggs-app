package com.p.andrews.feature.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.p.andrews.style.AppTheme
import java.util.Locale

@Composable
fun MainBreedFilterPills(
    allMainBreeds: List<String>,
    selected: String?,
    onSelected: (String?) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        item {
            FilterChip(
                label = {
                    Text(
                        text = "All",
                        color = AppTheme.colors.textAndIcons.desc
                    )
                },
                selected = selected == null,
                onClick = { onSelected(null) }
            )
        }
        items(allMainBreeds) { main ->
            FilterChip(
                label = {
                    Text(
                        text = main.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        color = AppTheme.colors.textAndIcons.desc
                    )
                },
                selected = selected == main,
                onClick = { onSelected(main) }
            )
        }
    }
}
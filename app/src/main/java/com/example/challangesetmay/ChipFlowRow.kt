package com.example.challangesetmay

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import kotlin.collections.forEach

@Composable
fun ChipsFlowRow(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    horizontalSpacing: Int = 8,
    verticalSpacing: Int = 8,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { it.measure(constraints.copy(minWidth = 0)) }
        var currentRowWidth = 0
        var currentRowHeight = 0
        var totalHeight = 0
        val rows = mutableListOf<MutableList<Placeable>>()
        var currentRow = mutableListOf<Placeable>()

        placeables.forEachIndexed { index, placeable ->
            val widthWithSpacing =
                placeable.width + if (currentRow.isNotEmpty()) horizontalSpacing else 0

            if (currentRow.isEmpty() || currentRowWidth + widthWithSpacing <= constraints.maxWidth) {
                currentRow.add(placeable)
                currentRowWidth += widthWithSpacing
                currentRowHeight = maxOf(currentRowHeight, placeable.height)
            } else {
                rows.add(currentRow)
                totalHeight += currentRowHeight + if (rows.size > 1) verticalSpacing else 0
                currentRow = mutableListOf(placeable)
                currentRowWidth = placeable.width
                currentRowHeight = placeable.height
            }
        }

        if (currentRow.isNotEmpty()) {
            rows.add(currentRow)
            totalHeight += currentRowHeight + if (rows.size > 1) verticalSpacing else 0
        }

        val layoutWidth = when (horizontalAlignment) {
            Alignment.CenterHorizontally, Alignment.End -> constraints.maxWidth
            else -> constraints.maxWidth
        }

        layout(layoutWidth, totalHeight) {
            var currentY = 0
            rows.forEach { row ->
                val rowWidth = row.mapIndexed { index, placeable ->
                    placeable.width + if (index > 0) horizontalSpacing else 0
                }.sum()

                var currentX = when (horizontalAlignment) {
                    Alignment.CenterHorizontally -> (layoutWidth - rowWidth) / 2
                    Alignment.End -> layoutWidth - rowWidth
                    else -> 0
                }

                row.forEach { placeable ->
                    placeable.placeRelative(currentX, currentY)
                    currentX += placeable.width + horizontalSpacing
                }
                currentY += (row.maxOfOrNull { it.height } ?: 0) + verticalSpacing
            }
        }
    }
}
package com.example.concierge.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.concierge.ui.theme.PrimaryBlue
import com.example.concierge.ui.theme.SecondaryGreen

@Composable
fun BarChart(
    data: List<Float>,
    modifier: Modifier = Modifier,
    color: Color = PrimaryBlue
) {
    Canvas(modifier = modifier) {
        val spacing = 8.dp.toPx()
        val barWidth = (size.width - (data.size - 1) * spacing) / data.size
        val maxVal = data.maxOrNull() ?: 1f

        data.forEachIndexed { index, value ->
            val barHeight = (value / maxVal) * size.height
            drawRect(
                color = color,
                topLeft = Offset(index * (barWidth + spacing), size.height - barHeight),
                size = Size(barWidth, barHeight)
            )
        }
    }
}

@Composable
fun DonutChart(
    values: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val total = values.sum()
        var startAngle = -90f
        val strokeWidth = 40.dp.toPx()

        values.forEachIndexed { index, value ->
            val sweepAngle = (value / total) * 360f
            drawArc(
                color = colors[index % colors.size],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth),
                size = Size(size.width, size.height)
            )
            startAngle += sweepAngle
        }
    }
}

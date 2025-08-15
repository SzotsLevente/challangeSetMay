package com.example.challangesetmay

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challangesetmay.ui.theme.BackgroundPurplePillTextIconColor
import com.example.challangesetmay.ui.theme.CardBG
import com.example.challangesetmay.ui.theme.ChallangeSetMayTheme
import com.example.challangesetmay.ui.theme.GreenPillBG
import com.example.challangesetmay.ui.theme.GreenPillTextIconColor
import com.example.challangesetmay.ui.theme.PrimaryTextColor
import com.example.challangesetmay.ui.theme.PurplePillBG
import com.example.challangesetmay.ui.theme.SecondaryTextColor
import com.example.challangesetmay.ui.theme.Stroke
import com.example.challangesetmay.ui.theme.TeacherAvatarStroke
import com.example.challangesetmay.ui.theme.TeacherPillBG

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallangeSetMayTheme {
                LessonOverviewSheet()
            }
        }
    }
}

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

@Composable
fun LessonOverviewSheet(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPurplePillTextIconColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp) // Account for status bar
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(CardBG)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    // Title
                    Text(
                        text = "Physics Crash Course",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 40.sp,
                        color = PrimaryTextColor,
                        textAlign = if (isLandscape) TextAlign.Center else TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    // Description
                    Text(
                        text = "The Physics Crash Course offers a foundational overview of essential concepts, teaching learners to understand Newton's three laws of motion, explain the principle of energy conservation, distinguish between kinetic and potential energy with real-world examples, solve basic problems involving force and mass, and apply the concept of momentum in everyday situations.",
                        fontSize = 15.sp,
                        color = SecondaryTextColor,
                        lineHeight = 24.sp,
                        textAlign = if (isLandscape) TextAlign.Center else TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    ChipsFlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment =  if(isLandscape) Alignment.CenterHorizontally else Alignment.Start,
                        horizontalSpacing = 12,
                        verticalSpacing = 8
                    ) {
                        // Intermediate chip
                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    "Intermediate", color = BackgroundPurplePillTextIconColor,
                                    fontSize = 17.sp, lineHeight = 24.sp
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painterResource(R.drawable.icon__2_),
                                    contentDescription = null,
                                    tint = BackgroundPurplePillTextIconColor,
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = PurplePillBG
                            ), border = null,
                            modifier = Modifier.clip(RoundedCornerShape(24.dp))
                        )

                        // Science chip
                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    "Science",
                                    color = GreenPillTextIconColor,
                                    fontSize = 15.sp,
                                    lineHeight = 24.sp
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = GreenPillBG
                            ),
                            border = null,
                            modifier = Modifier.clip(RoundedCornerShape(24.dp))
                        )

                        // Physics chip
                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    "Physics",
                                    color = GreenPillTextIconColor,
                                    fontSize = 15.sp,
                                    lineHeight = 24.sp
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = GreenPillBG
                            ),
                            border = null,
                            modifier = Modifier.clip(RoundedCornerShape(24.dp))
                        )

                        // Duration chip
                        AssistChip(
                            onClick = { },
                            label = {
                                Icon(
                                    painterResource(R.drawable.icon__1_),
                                    contentDescription = "Duration",
                                    tint = SecondaryTextColor,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    "15 mins",
                                    color = SecondaryTextColor,
                                    fontSize = 15.sp,
                                    lineHeight = 24.sp
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Stroke
                            ),
                            border = null,
                            modifier = Modifier.clip(RoundedCornerShape(24.dp))
                        )
                    }
                }

                item {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Stroke
                    )
                }

                item {
                    // What you'll learn section
                    Text(
                        text = "What you'll learn:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp,
                        color = PrimaryTextColor,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Learning points
                val learningPoints = listOf(
                    "Understand Newton's three laws of motion",
                    "Explain the principle of energy conservation",
                    "Identify real-world examples of kinetic and potential energy",
                    "Solve simple physics problems involving force and mass",
                    "Apply concepts of momentum in everyday scenarios"
                )

                items(learningPoints.size) { index ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = SecondaryTextColor,
                            modifier = Modifier.padding(end = 12.dp)
                        )

                        Text(
                            text = learningPoints[index],
                            fontSize = 14.sp,
                            color = SecondaryTextColor,
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    // Footer with professor info
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = TeacherPillBG,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Profile image (wrapped in a Box for better positioning control)
                            Box(
                                modifier = Modifier.size(40.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.avatar),
                                    contentDescription = "Professor",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .border(1.dp, TeacherAvatarStroke, CircleShape)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = "Dr. Eleanor Maxwell",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = PrimaryTextColor
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp)) // Space for navigation bar
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChallangeSetMayTheme {
        LessonOverviewSheet()
    }
}
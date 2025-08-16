package com.example.challangesetmay

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challangesetmay.ui.theme.BackgroundPurplePillTextIconColor
import com.example.challangesetmay.ui.theme.CardBG
import com.example.challangesetmay.ui.theme.GreenPillBG
import com.example.challangesetmay.ui.theme.GreenPillTextIconColor
import com.example.challangesetmay.ui.theme.PrimaryTextColor
import com.example.challangesetmay.ui.theme.PurplePillBG
import com.example.challangesetmay.ui.theme.SecondaryTextColor
import com.example.challangesetmay.ui.theme.Stroke
import com.example.challangesetmay.ui.theme.TeacherAvatarStroke
import com.example.challangesetmay.ui.theme.TeacherPillBG

@Composable
fun LessonOverviewSheet(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val poltawskiNowy = GoogleFont("Poltawski Nowy")
    val montserrat = GoogleFont("Montserrat")

    val poltawskiNowyFontFamily = FontFamily(
        Font(googleFont = poltawskiNowy, fontProvider = provider, weight = FontWeight.W700)
    )

    val montserratFontFamily = FontFamily(
        Font(googleFont = montserrat, fontProvider = provider, weight = FontWeight.W400)
    )

    val montserratMediumFontFamily = FontFamily(
        Font(googleFont = montserrat, fontProvider = provider, weight = FontWeight.W500)
    )

    val montserratSemiBoldFontFamily = FontFamily(
        Font(googleFont = montserrat, fontProvider = provider, weight = FontWeight.W600)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPurplePillTextIconColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(CardBG)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .padding(bottom = 80.dp), // Add bottom padding for the fixed element
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    // Title
                    Text(
                        text = "Physics Crash Course",
                        fontSize = if(isLandscape) 40.sp else 36.sp,
                        fontFamily = poltawskiNowyFontFamily,
                        lineHeight = if(isLandscape) 44.sp else 40.sp,
                        color = PrimaryTextColor,
                        textAlign = if (isLandscape) TextAlign.Center else TextAlign.Start,
                        letterSpacing = 0.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    // Description
                    Text(
                        text = "The Physics Crash Course offers a foundational overview of essential concepts, teaching learners to understand Newton's three laws of motion, explain the principle of energy conservation, distinguish between kinetic and potential energy with real-world examples, solve basic problems involving force and mass, and apply the concept of momentum in everyday situations.",
                        fontSize = if(isLandscape) 17.sp else 15.sp,
                        color = SecondaryTextColor,
                        lineHeight = 24.sp,
                        textAlign = if (isLandscape) TextAlign.Center else TextAlign.Start,
                        fontFamily = montserratFontFamily,
                        letterSpacing = 0.sp,
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
                                    fontSize = 17.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = montserratMediumFontFamily,
                                    letterSpacing = 0.sp
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
                            ),
                            shape = RoundedCornerShape(50.dp),
                            border = null
                        )

                        // Science chip
                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    "Science",
                                    color = GreenPillTextIconColor,
                                    fontSize = 15.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = montserratMediumFontFamily,
                                    letterSpacing = 0.sp
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = GreenPillBG
                            ),
                            shape = RoundedCornerShape(50.dp),
                            border = null
                        )

                        // Physics chip
                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    "Physics",
                                    color = GreenPillTextIconColor,
                                    fontSize = 15.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = montserratMediumFontFamily,
                                    letterSpacing = 0.sp
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = GreenPillBG
                            ),
                            shape = RoundedCornerShape(50.dp),
                            border = null
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
                                    lineHeight = 24.sp,
                                    fontFamily = montserratMediumFontFamily,
                                    letterSpacing = 0.sp
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(50.dp),
                            border = BorderStroke(
                                width = 1.dp,
                                color = Stroke
                            )
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
                        fontSize = if(isLandscape) 20.sp else 18.sp,
                        fontFamily = montserratSemiBoldFontFamily,
                        lineHeight = 26.sp,
                        color = PrimaryTextColor,
                        textAlign = TextAlign.Start,
                        letterSpacing = 0.sp,
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
                            tint = BackgroundPurplePillTextIconColor,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .height(16.dp)
                        )

                        Text(
                            text = learningPoints[index],
                            fontSize = 14.sp,
                            color = PrimaryTextColor,
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Start,
                            fontFamily = montserratFontFamily,
                            letterSpacing = 0.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Fixed bottom element - Dr. Eleanor Maxwell
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(20.dp)
                    .background(
                        color = TeacherPillBG,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(2.dp, TeacherAvatarStroke, CircleShape)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.avatar),
                            contentDescription = "Professor",
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.TopEnd,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .padding(top = 8.dp)
                                .graphicsLayer(
                                    scaleX = 1.5f,
                                    scaleY = 1.5f
                                )
                        )
                    }

                    Text(
                        text = "Dr. Eleanor Maxwell",
                        fontSize = if (isLandscape) 20.sp else 18.sp,
                        lineHeight = if (isLandscape) 28.sp else 26.sp,
                        fontFamily = montserratMediumFontFamily,
                        color = PrimaryTextColor,
                        letterSpacing = 0.sp
                    )
                }
            }
        }
    }
}
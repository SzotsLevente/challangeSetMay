package com.example.challangesetmay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challangesetmay.ui.theme.StudyListBiologyTagBackground
import com.example.challangesetmay.ui.theme.StudyListBiologyTagText
import com.example.challangesetmay.ui.theme.StudyListDefaultTagBackground
import com.example.challangesetmay.ui.theme.StudyListDefaultTagText
import com.example.challangesetmay.ui.theme.StudyListEnvironmentalScienceTagBackground
import com.example.challangesetmay.ui.theme.StudyListEnvironmentalScienceTagText
import com.example.challangesetmay.ui.theme.StudyListFrenchTagBackground
import com.example.challangesetmay.ui.theme.StudyListFrenchTagText
import com.example.challangesetmay.ui.theme.StudyListGeographyTagBackground
import com.example.challangesetmay.ui.theme.StudyListGeographyTagText
import com.example.challangesetmay.ui.theme.StudyListHealthTagBackground
import com.example.challangesetmay.ui.theme.StudyListHealthTagText
import com.example.challangesetmay.ui.theme.StudyListHigherSurface
import com.example.challangesetmay.ui.theme.StudyListHistoryTagBackground
import com.example.challangesetmay.ui.theme.StudyListHistoryTagText
import com.example.challangesetmay.ui.theme.StudyListIconColor
import com.example.challangesetmay.ui.theme.StudyListLanguageArtsTagBackground
import com.example.challangesetmay.ui.theme.StudyListLanguageArtsTagText
import com.example.challangesetmay.ui.theme.StudyListLanguageTagBackground
import com.example.challangesetmay.ui.theme.StudyListLanguageTagText
import com.example.challangesetmay.ui.theme.StudyListLiteratureTagBackground
import com.example.challangesetmay.ui.theme.StudyListLiteratureTagText
import com.example.challangesetmay.ui.theme.StudyListMathTagBackground
import com.example.challangesetmay.ui.theme.StudyListMathTagText
import com.example.challangesetmay.ui.theme.StudyListPrimary
import com.example.challangesetmay.ui.theme.StudyListPrimaryText
import com.example.challangesetmay.ui.theme.StudyListSocialStudiesTagBackground
import com.example.challangesetmay.ui.theme.StudyListSocialStudiesTagText
import com.example.challangesetmay.ui.theme.StudyListStroke
import com.example.challangesetmay.ui.theme.StudyListSurface
import com.example.challangesetmay.ui.theme.StudyListTertiaryText
import kotlinx.coroutines.flow.debounce

@Composable
fun SearchableStudyList(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    var filteredTopics by remember { mutableStateOf(studyTopics) }

    LaunchedEffect(Unit) {
        snapshotFlow { searchText }
            .debounce(300)
            .collect { query ->
                filteredTopics = if (query.isEmpty()) {
                    studyTopics
                } else {
                    studyTopics.filter { topic ->
                        topic.title.contains(query, ignoreCase = true) ||
                        topic.subjects.any { subject ->
                            subject.contains(query, ignoreCase = true)
                        }
                    }
                }
            }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(StudyListHigherSurface)
    ) {
        // Header section with title and search
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(StudyListSurface)
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = "Study Topics",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = StudyListPrimaryText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = {
                    Text(
                        text = "Search by topic or subject",
                        color = StudyListTertiaryText
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = StudyListIconColor
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = StudyListStroke,
                    focusedBorderColor = StudyListPrimary,
                    focusedContainerColor = StudyListHigherSurface,
                    unfocusedContainerColor = StudyListHigherSurface
                )
            )
        }

        if (filteredTopics.isEmpty()) {
            Box(
                modifier = Modifier.padding(16.dp) ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No results found, try searching again!",
                    fontSize = 16.sp,
                    color = StudyListTertiaryText,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            // Study topics list
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(filteredTopics) { topic ->
                    StudyTopicCard(topic = topic)
                }
            }
        }
    }
}

@Composable
fun StudyTopicCard(topic: StudyTopic) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = StudyListSurface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Subject tags
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                topic.subjects.forEach { subject ->
                    SubjectTag(subject = subject)
                }
            }

            // Topic title
            Text(
                text = topic.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = StudyListPrimaryText
            )
        }
    }
}

@Composable
fun SubjectTag(subject: String) {
    val (backgroundColor, textColor) = when (subject) {
        "Biology" -> StudyListBiologyTagBackground to StudyListBiologyTagText
        "Environmental Science" -> StudyListEnvironmentalScienceTagBackground to StudyListEnvironmentalScienceTagText
        "History" -> StudyListHistoryTagBackground to StudyListHistoryTagText
        "Social Studies" -> StudyListSocialStudiesTagBackground to StudyListSocialStudiesTagText
        "Math" -> StudyListMathTagBackground to StudyListMathTagText
        "Literature" -> StudyListLiteratureTagBackground to StudyListLiteratureTagText
        "Language Arts" -> StudyListLanguageArtsTagBackground to StudyListLanguageArtsTagText
        "Geography" -> StudyListGeographyTagBackground to StudyListGeographyTagText
        "Language" -> StudyListLanguageTagBackground to StudyListLanguageTagText
        "French" -> StudyListFrenchTagBackground to StudyListFrenchTagText
        "Health" -> StudyListHealthTagBackground to StudyListHealthTagText
        else -> StudyListDefaultTagBackground to StudyListDefaultTagText
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = subject,
            fontSize = 12.sp,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}
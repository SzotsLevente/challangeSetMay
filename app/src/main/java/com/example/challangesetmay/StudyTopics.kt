package com.example.challangesetmay

data class StudyTopic(val title: String, val subjects: List<String>)

val studyTopics = listOf(
    StudyTopic("Photosynthesis", listOf("Biology", "Environmental Science")),
    StudyTopic("World War II Timeline", listOf("History", "Social Studies")),
    StudyTopic("Introduction to Fractions", listOf("Math")),
    StudyTopic("Elements of a Story", listOf("Literature", "Language Arts")),
    StudyTopic("The Water Cycle", listOf("Geography", "Environmental Science")),
    StudyTopic("Basic French Greetings", listOf("Language", "French")),
    StudyTopic("The Human Skeleton", listOf("Biology", "Health")),
    StudyTopic("Ancient Egyptian Civilizations", listOf("History", "Archaeology")),
    StudyTopic("Solving for X (Algebra Basics)", listOf("Math")),
    StudyTopic("Types of Clouds", listOf("Geography", "Earth Science")),
    StudyTopic("Writing a Thesis Statement", listOf("Writing", "Language Arts")),
    StudyTopic("The Constitution Explained", listOf("Civics", "History")),
    StudyTopic("Food Chains and Webs", listOf("Biology", "Ecology")),
    StudyTopic("Understanding Supply & Demand", listOf("Economics", "Social Studies")),
    StudyTopic("Literary Devices in Poetry", listOf("Literature", "English")),
    StudyTopic("Basic Spanish Verbs", listOf("Language", "Spanish")),
    StudyTopic("Introduction to Coding", listOf("Computer Science", "Technology")),
    StudyTopic("Earthquake Safety Basics", listOf("Geography", "Earth Science")),
    StudyTopic("Subject‑Verb Agreement", listOf("Grammar", "Language Arts")),
    StudyTopic("The Solar System Overview", listOf("Astronomy", "Science"))
)
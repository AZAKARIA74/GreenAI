package com.greenai.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.greenai.R




val DmSerifDisplay = FontFamily(
    Font(R.font.dm_serif_display_regular, FontWeight.Normal),
    Font(R.font.dm_serif_display_italic,  FontWeight.Normal, FontStyle.Italic),
)

val Poppins = FontFamily(
    Font(R.font.poppins_light,   FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium,  FontWeight.Medium),
)

// ─────────────────────────────────────────────
//  Typography scale
//  Mapped to Material 3 roles
// ─────────────────────────────────────────────
val GreenAITypography = Typography(

    // ── Display ─────────────────────────────
    // Hero headlines, splash screen, onboarding
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 45.sp,
        lineHeight = 52.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 36.sp,        // "Grow smarter."
        lineHeight = 44.sp,
    ),

    // ── Headline ────────────────────────────
    // Screen titles, section headers
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 32.sp,
        lineHeight = 40.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 28.sp,        // TopAppBar title
        lineHeight = 36.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 24.sp,        // Card section header
        lineHeight = 32.sp,
    ),

    // ── Title ───────────────────────────────
    // Card titles, list item primaries
    titleLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize   = 22.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize   = 16.sp,        // Card title, dialog title
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize   = 14.sp,        // List item heading
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    // ── Body ────────────────────────────────
    // Descriptions, paragraphs, result values
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 14.sp,        // Primary body text
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize   = 12.sp,        // Hints, secondary info
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),

    // ── Label ───────────────────────────────
    // Buttons, chips, input labels, badges
    labelLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize   = 14.sp,        // Button text
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize   = 12.sp,        // Chip label, tab label
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize   = 10.sp,        // Caption, timestamp, unit suffix
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
)

// ─────────────────────────────────────────────
//  Usage quick reference
// ─────────────────────────────────────────────
//
//  Splash / hero title     → displaySmall   (DmSerifDisplay 36sp)
//  TopAppBar title         → headlineMedium (DmSerifDisplay 28sp)
//  Screen section header   → headlineSmall  (DmSerifDisplay 24sp)
//  Card title              → titleMedium    (DmSans Medium  16sp)
//  Input label / row key   → titleSmall     (DmSans Medium  14sp)
//  Body / description      → bodyMedium     (DmSans Regular 14sp)
//  Result value text       → bodyLarge      (DmSans Regular 16sp)
//  Hint / timestamp        → bodySmall      (DmSans Regular 12sp)
//  Button text             → labelLarge     (DmSans Medium  14sp)
//  Badge / chip label      → labelMedium    (DmSans Medium  12sp)
//  Unit suffix (kg/ha, °C) → labelSmall     (DmSans Medium  10sp)
package com.example.greenai.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


// ─────────────────────────────────────────────
//  Primary Green
// ─────────────────────────────────────────────
val Green50  = Color(0xFFEEF7EE)
val Green100 = Color(0xFFC9E8C8)
val Green200 = Color(0xFF93CF90)
val Green300 = Color(0xFF61B379)
val Green400 = Color(0xFF3F9E3A)
val Green600 = Color(0xFF1F6B1B)
val Green800 = Color(0xFF0D4009)
val Green900 = Color(0xFF062604)

// ─────────────────────────────────────────────
//  Sprout (success / healthy state)
// ─────────────────────────────────────────────
val Sprout50  = Color(0xFFF0F9F1)
val Sprout100 = Color(0xFFD4F0D6)
val Sprout200 = Color(0xFFA8E1AC)
val Sprout400 = Color(0xFF52B95A)
val Sprout600 = Color(0xFF2A8030)
val Sprout800 = Color(0xFF155218)

// ─────────────────────────────────────────────
//  Earth Amber (warning / mild disease)
// ─────────────────────────────────────────────
val Amber50  = Color(0xFFFEF9EC)
val Amber100 = Color(0xFFFDECC0)
val Amber400 = Color(0xFFF0B429)
val Amber600 = Color(0xFFB07D0E)
val Amber800 = Color(0xFF6B4A08)

// ─────────────────────────────────────────────
//  Alert Rust (error / severe disease)
// ─────────────────────────────────────────────
val Rust50  = Color(0xFFFFF5F2)
val Rust100 = Color(0xFFFED2C7)
val Rust400 = Color(0xFFF0622E)
val Rust600 = Color(0xFFB03A15)
val Rust800 = Color(0xFF6B2008)

// ─────────────────────────────────────────────
//  Soil Neutral (surfaces / text / borders)
// ─────────────────────────────────────────────
val Neutral50  = Color(0xFFF4F6F3)
val Neutral100 = Color(0xFFDCE1D9)
val Neutral200 = Color(0xFFB8C1B4)
val Neutral400 = Color(0xFF7A8A74)
val Neutral600 = Color(0xFF4D5C48)
val Neutral800 = Color(0xFF2A3327)
val Neutral900 = Color(0xFF151C12)

// ─────────────────────────────────────────────
//  Semantic aliases  (use these in your code,
//  not the raw ramp values above)
// ─────────────────────────────────────────────
val ColorPrimary          = Green300
val ColorPrimaryContainer = Green50
val ColorOnPrimary        = Color.White
val ColorOnPrimaryContainer = Green800

val ColorSecondary          = Sprout600
val ColorSecondaryContainer = Sprout600
val ColorOnSecondary        = Color.White
val ColorOnSecondaryContainer = Sprout800

val ColorTertiary          = Amber600
val ColorTertiaryContainer = Amber50
val ColorOnTertiary        = Color.White
val ColorOnTertiaryContainer = Amber800

val ColorError          = Rust600
val ColorErrorContainer = Rust100
val ColorOnError        = Color.White
val ColorOnErrorContainer = Rust800

val ColorBackground   = Neutral50
val ColorSurface      = Sprout100
val ColorSurfaceVariant = Neutral100
val ColorOnBackground = Neutral800
val ColorOnSurface    = Neutral800
val ColorOnSurfaceVariant = Neutral600
val ColorOutline      = Neutral200
val ColorOutlineVariant = Neutral100

val ColorDisabled = Neutral400

// ─────────────────────────────────────────────
//  Dark theme equivalents
// ─────────────────────────────────────────────
val DarkColorPrimary          = Green400
val DarkColorPrimaryContainer = Green800
val DarkColorOnPrimary        = Green900
val DarkColorOnPrimaryContainer = Green100

val DarkColorSecondary          = Sprout400
val DarkColorSecondaryContainer = Sprout800
val DarkColorOnSecondary        = Sprout50
val DarkColorOnSecondaryContainer = Sprout100

val DarkColorTertiary          = Amber400
val DarkColorTertiaryContainer = Amber800
val DarkColorOnTertiary        = Amber50
val DarkColorOnTertiaryContainer = Amber100

val DarkColorError          = Rust400
val DarkColorErrorContainer = Rust800.copy(alpha = 0.5f)
val DarkColorOnError        = Rust50
val DarkColorOnErrorContainer = Rust100

val DarkColorBackground   = Neutral900
val DarkColorSurface      = Neutral800
val DarkColorSurfaceVariant = Neutral600
val DarkColorOnBackground = Neutral100
val DarkColorOnSurface    = Neutral100
val DarkColorOnSurfaceVariant = Neutral200
val DarkColorOutline      = Neutral600
val DarkColorOutlineVariant = Neutral800

val DarkColorDisabled = Neutral400

val LightAppBarButton = Sprout800
val DarkAppBarButton = Sprout600
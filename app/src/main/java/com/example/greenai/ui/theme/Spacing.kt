package com.example.greenai.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object GreenAISpacing {

    // Base unit = 4dp
    val xxxs: Dp = 2.dp
    val xxs:  Dp = 4.dp
    val xs:   Dp = 6.dp
    val sm:   Dp = 8.dp
    val md:   Dp = 12.dp
    val lg:   Dp = 16.dp
    val xl:   Dp = 20.dp
    val xxl:  Dp = 24.dp
    val xxxl: Dp = 32.dp
    val huge: Dp = 40.dp
    val giant:Dp = 48.dp
    val screen:Dp = 56.dp   // standard screen horizontal padding

    /** Padding inside small chips, badges, tags */
    val chipPaddingHorizontal: Dp = md      // 12dp
    val chipPaddingVertical:   Dp = xxs     //  4dp

    /** Padding inside cards */
    val cardPadding: Dp = lg                // 16dp

    /** Horizontal padding of the main screen edge */
    val screenHorizontal: Dp = lg          // 16dp

    /** Vertical gap between sections on a screen */
    val sectionGap: Dp = xxl               // 24dp

    /** Gap between items in a LazyColumn list */
    val listItemGap: Dp = sm               //  8dp

    /** Internal gap inside a card between rows */
    val cardRowGap: Dp = xs                //  6dp

    /** Gap inside a 2-col stat grid */
    val statGridGap: Dp = sm               //  8dp

    /** Height of dividers inside cards */
    val dividerThickness: Dp = 0.5.dp

    /** Space between label and input field */
    val inputLabelGap: Dp = xxs            //  4dp

    /** Minimum touch target size (Material 3 = 48dp) */
    val minTouchTarget: Dp = 48.dp

    // ── Icon sizes ──────────────────────────
    val iconSm:  Dp = 16.dp
    val iconMd:  Dp = 20.dp
    val iconLg:  Dp = 24.dp
    val iconXl:  Dp = 32.dp

    // ── Avatar / thumbnail sizes ────────────
    val avatarSm: Dp = 32.dp
    val avatarMd: Dp = 40.dp
    val avatarLg: Dp = 48.dp

    // ── Component heights ───────────────────
    val buttonHeight:      Dp = 48.dp
    val buttonHeightSm:    Dp = 36.dp
    val textFieldHeight:   Dp = 56.dp
    val bottomNavHeight:   Dp = 80.dp
    val topAppBarHeight:   Dp = 64.dp
    val confidenceBarHeight: Dp = 4.dp
}

val GreenAIShapes = Shapes(

    // ExtraSmall  — tags, small badges, confidence bar
    extraSmall = RoundedCornerShape(4.dp),

    // Small  — input fields, small buttons, chips
    small = RoundedCornerShape(8.dp),

    // Medium  — cards, dialogs (Material 3 default = 12dp)
    medium = RoundedCornerShape(12.dp),

    // Large  — bottom sheets, feature cards
    large = RoundedCornerShape(16.dp),

    // ExtraLarge  — hero sections, scan zone, modals
    extraLarge = RoundedCornerShape(24.dp),
)


object GreenAIShape {
    /** Fully rounded pill — FAB, filter chips, badges */
    val buttonRounded         = RoundedCornerShape(percent = 25)

    /** Top-only rounding — bottom sheet handle area */
    val topRounded   = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

    /** Circle — avatar, scan result icon dot */
    val circle       = RoundedCornerShape(percent = 50)

    /** Standard card */
    val card         = RoundedCornerShape(16.dp)

    /** Input field */
    val input        = RoundedCornerShape(8.dp)

    /** Small badge */
    val badge        = RoundedCornerShape(4.dp)
}

object GreenAIElevation {
    val none:     Dp = 0.dp
    val card:     Dp = 1.dp    // resting card surface
    val raised:   Dp = 2.dp    // hovered / pressed card
    val overlay:  Dp = 3.dp    // menus, dropdowns
    val modal:    Dp = 6.dp    // dialogs, bottom sheets
    val fab:      Dp = 6.dp    // floating action button
    val appBar:   Dp = 0.dp    // flat top app bar (M3 default)
    val navBar:   Dp = 0.dp    // flat bottom nav
}
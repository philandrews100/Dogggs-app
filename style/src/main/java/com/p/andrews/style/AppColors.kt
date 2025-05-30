package com.p.andrews.style

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val DarkColors
    get() =
        AppColors(
            primary = Primary(
                background = Color(0XFF12171F),
                containerColor = Color(0xFF1E1E2D),
                overlayColor = Color(0xFF1E1E2D),
                cardBackground = Color(0xFF1C2431)
            ),
            borders = Borders(
                avatarBorder = Color(0xFFFFFFFF),
                containerBorder = Color(0xFF2B2C30)
            ),
            textAndIcons = TextAndIcons(
                title = Color(0XFFFFFFFF),
                desc = Color(0XFFA5A5A7),
                subTitle = Color(0XFFA5A5A7),
                count = Color(0XFFA5A5A7),
                icon = Color(0XFFFFFFFF)
            ),
            buttons = Buttons(
                positive = Color(0XFFFFFFFF),
                negative = Color(0XFFFFFFFF),
                available = Color(0XFFFFFFFF),
                unavailable = Color(0XFFFFFFFF),
                delayed = Color(0XFFFFFFFF)
            ),
            misc = Misc(random = Color(0xFFFFFFFF))
        )

internal val LightColors
    get() =
        AppColors(
            primary = Primary(
                background = Color(0XFFF5F8FA),
                containerColor = Color(0xFFF4F8FF),
                overlayColor = Color(0xFF1E1E2D),
                cardBackground = Color(0xFFE3E7EC)
            ),
            borders = Borders(
                avatarBorder = Color(0xFF2A2B3D),
                containerBorder = Color(0x324C4C4C)
            ),
            textAndIcons = TextAndIcons(
                title = Color(0xff4C4C4C),
                desc = Color(0xff4A4A4A),
                subTitle = Color(0xff4A4A4A),
                count = Color(0xff4A4A4A),
                icon = Color(0xff4C4C4C)
            ),
            buttons = Buttons(
                positive = Color(0XFFFFFFFF),
                negative = Color(0XFFFFFFFF),
                available = Color(0XFFFFFFFF),
                unavailable = Color(0XFFFFFFFF),
                delayed = Color(0XFFFFFFFF)
            ),
            misc = Misc(random = Color(0xFFFFFFFF))
        )

data class AppColors(
    val primary: Primary,
    val borders: Borders,
    val textAndIcons: TextAndIcons,
    val buttons: Buttons,
    val misc: Misc
)

data class Primary(
    val background: Color,
    val containerColor: Color,
    val overlayColor: Color,
    val cardBackground: Color
)

data class Borders(
    val avatarBorder: Color,
    val containerBorder: Color
)

data class TextAndIcons(
    val title: Color,
    val desc: Color,
    val subTitle: Color,
    val count: Color,
    val icon: Color
)

data class Buttons(
    val positive: Color,
    val negative: Color,
    val available: Color,
    val delayed: Color,
    val unavailable: Color
)

data class Misc(
    val random: Color,
    val availableColor: Color = Color(0xFF2DAE60),
    val delayedColor: Color = Color(0xFFd7882b),
    val unavailableColor: Color = Color(0xFFd33b3b)
)

internal val LocalSLSColors = staticCompositionLocalOf {
    AppColors(
        primary = Primary(
            background = Color.Unspecified,
            containerColor = Color.Unspecified,
            overlayColor = Color.Unspecified,
            cardBackground = Color.Unspecified,
        ),
        borders = Borders(
            avatarBorder = Color.Unspecified,
            containerBorder = Color.Unspecified
        ),
        textAndIcons = TextAndIcons(
            title = Color.Unspecified,
            desc = Color.Unspecified,
            subTitle = Color.Unspecified,
            count = Color.Unspecified,
            icon = Color.Unspecified
        ),
        buttons = Buttons(
            positive = Color.Unspecified,
            negative = Color.Unspecified,
            available = Color.Unspecified,
            unavailable = Color.Unspecified,
            delayed = Color.Unspecified
        ),
        misc = Misc(random = Color.Unspecified)
    )
}
package com.theveloper.pixelplay.presentation.components.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import com.kyant.backdrop.components.Tab
import com.kyant.backdrop.components.Tabs
import com.theveloper.pixelplay.R
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun BottomToggleRow(
    modifier: Modifier,
    isShuffleEnabled: Boolean,
    repeatMode: Int,
    isFavoriteProvider: () -> Boolean,
    onShuffleToggle: () -> Unit,
    onRepeatToggle: () -> Unit,
    onFavoriteToggle: () -> Unit,
    activeColorMain: Color = MaterialTheme.colorScheme.primary,
    activeColorSecondary: Color = MaterialTheme.colorScheme.secondary,
    activeColorTertiary: Color = MaterialTheme.colorScheme.tertiary,
    onActiveColorMain: Color = MaterialTheme.colorScheme.onPrimary,
    onActiveColorSecondary: Color = MaterialTheme.colorScheme.onSecondary,
    onActiveColorTertiary: Color = MaterialTheme.colorScheme.onTertiary,
    inactiveColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
    inactiveContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer
) {
    val isFavorite = isFavoriteProvider()
    val rowCorners = 60.dp
    // Track the visual droplet position
    var selectedTabIndex by remember { mutableIntStateOf(1) }

    Box(
        modifier = modifier.background(
            color = containerColor,
            shape = AbsoluteSmoothCornerShape(
                cornerRadiusBL = rowCorners, smoothnessAsPercentTR = 60,
                cornerRadiusBR = rowCorners, smoothnessAsPercentBL = 60,
                cornerRadiusTL = rowCorners, smoothnessAsPercentBR = 60,
                cornerRadiusTR = rowCorners, smoothnessAsPercentTL = 60
            )
        )
    ) {
        Tabs(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth().height(66.dp)
        ) {
            // TAB 0: SHUFFLE
            Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0; onShuffleToggle() }, modifier = Modifier.weight(1f)) {
                Icon(painter = painterResource(R.drawable.rounded_shuffle_24), contentDescription = "Shuffle", tint = if (isShuffleEnabled) activeColorMain else inactiveContentColor)
            }
            // TAB 1: REPEAT
            Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1; onRepeatToggle() }, modifier = Modifier.weight(1f)) {
                val repeatActive = repeatMode != Player.REPEAT_MODE_OFF
                Icon(painter = painterResource(if (repeatMode == Player.REPEAT_MODE_ONE) R.drawable.rounded_repeat_one_24 else R.drawable.rounded_repeat_24), contentDescription = "Repeat", tint = if (repeatActive) activeColorSecondary else inactiveContentColor)
            }
            // TAB 2: FAVORITE
            Tab(selected = selectedTabIndex == 2, onClick = { selectedTabIndex = 2; onFavoriteToggle() }, modifier = Modifier.weight(1f)) {
                Icon(painter = painterResource(if (isFavorite) R.drawable.round_favorite_24 else R.drawable.rounded_favorite_24), contentDescription = "Favorite", tint = if (isFavorite) activeColorTertiary else inactiveContentColor)
            }
        }
    }
}

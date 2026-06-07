package com.theveloper.pixelplay.presentation.components.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.kyant.backdrop.components.Tabs
import com.theveloper.pixelplay.R
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun BottomToggleRow(
    modifier: Modifier = Modifier,
    isShuffleEnabled: Boolean,
    repeatMode: Int,
    isFavoriteProvider: () -> Boolean,
    onShuffleToggle: () -> Unit,
    onRepeatToggle: () -> Unit,
    onFavoriteToggle: () -> Unit,
    activeColorMain: Color = MaterialTheme.colorScheme.primary,
    activeColorSecondary: Color = MaterialTheme.colorScheme.secondary,
    activeColorTertiary: Color = MaterialTheme.colorScheme.tertiary,
    inactiveContentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer
) {
    val isFavorite by remember(isFavoriteProvider) { derivedStateOf(isFavoriteProvider) }
    val rowCorners = 60.dp
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
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { selectedTabIndex = 0; onShuffleToggle() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_shuffle_24),
                    contentDescription = "Shuffle",
                    tint = if (isShuffleEnabled) activeColorMain else inactiveContentColor
                )
            }
            
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { selectedTabIndex = 1; onRepeatToggle() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (repeatMode == Player.REPEAT_MODE_ONE) R.drawable.rounded_repeat_one_24 else R.drawable.rounded_repeat_24),
                    contentDescription = "Repeat",
                    tint = if (repeatMode != Player.REPEAT_MODE_OFF) activeColorSecondary else inactiveContentColor
                )
            }
            
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { selectedTabIndex = 2; onFavoriteToggle() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (isFavorite) R.drawable.round_favorite_24 else R.drawable.rounded_favorite_24),
                    contentDescription = "Favorite",
                    tint = if (isFavorite) activeColorTertiary else inactiveContentColor
                )
            }
        }
    }
}

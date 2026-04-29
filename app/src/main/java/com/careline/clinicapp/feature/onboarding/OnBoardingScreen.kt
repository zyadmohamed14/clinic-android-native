package com.careline.clinicapp.feature.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.careline.clinicapp.R
import com.careline.clinicapp.core.constants.DrawableResources
import com.careline.clinicapp.feature.onboarding.components.OnboardingPage
import com.careline.clinicapp.feature.onboarding.components.TopBar
import com.careline.clinicapp.feature.onboarding.model.OnboardingItem
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    val items = listOf(
        OnboardingItem(
            image = DrawableResources.lottieDiscoverTopClinics,
            titleRes = R.string.onboarding_page1_title,
            subtitleRes = R.string.onboarding_page1_subtitle
        ),
        OnboardingItem(
            image = DrawableResources.lottieArriveJustInTime,
            titleRes = R.string.onboarding_page2_title,
            subtitleRes = R.string.onboarding_page2_subtitle
        ),
        OnboardingItem(
            image = DrawableResources.lottieWaitingInQueue,
            titleRes = R.string.onboarding_page3_title,
            subtitleRes = R.string.onboarding_page3_subtitle
        )
    )

    val isLastPage = pagerState.currentPage == items.lastIndex
    val buttonText =
        if (isLastPage) stringResource(R.string.onboarding_start) else stringResource(R.string.onboarding_next)


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // ── Top Bar ─────────────────────────
            TopBar(
                current = pagerState.currentPage,
                count = items.size,
                onSkip = onFinish
            )

            // ── Pager ───────────────────────────
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPage(item = items[page])
            }

            // ── Button ──────────────────────────
            Button(
                onClick = {
                    if (!isLastPage) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(buttonText)
            }
        }
    }
}
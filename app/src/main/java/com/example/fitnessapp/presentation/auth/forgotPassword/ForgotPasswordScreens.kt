package com.example.fitnessapp.presentation.auth.forgotPassword

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnessapp.presentation.auth.forgotPassword.screens.EnterCodeScreen
import com.example.fitnessapp.presentation.auth.forgotPassword.screens.EnterEmailScreen
import com.example.fitnessapp.presentation.auth.forgotPassword.screens.ResetPasswordScreen
import com.example.fitnessapp.presentation.components.buttons.TurnBackButton
import com.example.fitnessapp.presentation.navHost.ScreenRoutes
import com.example.fitnessapp.ui.theme.responsiveLayout
import kotlinx.coroutines.launch

private val pagerTween: AnimationSpec<Float> = tween(
    durationMillis = 500,
    delayMillis = 50,
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ForgotPasswordScreens(
    authNavController: NavController,
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 3 }

    fun turnBack() {
        if(pagerState.currentPage == 0) {
            authNavController.navigateUp()
        } else {
            coroutineScope.launch {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage-1,
                    animationSpec = pagerTween
                )
            }
        }
    }

    BackHandler {
        turnBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth(),
                state = pagerState,
                userScrollEnabled = false,
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (page) {
                        0 -> {

                            EnterEmailScreen(
                                emailInput = forgotPasswordViewModel.email,
                                onEmailInputChange = { input ->
                                    forgotPasswordViewModel.updateEmailInput(
                                        input
                                    )
                                },
                                sendCode = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(
                                            page = pagerState.currentPage + 1,
                                            animationSpec = pagerTween
                                        )
                                    }
                                }
                            )
                        }
                        1 -> {

                            EnterCodeScreen(
                                email = forgotPasswordViewModel.email,
                                code = forgotPasswordViewModel.code,
                                onCodeInputChange = forgotPasswordViewModel::updateCodeInput,
                                verifyCode = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(
                                            page = pagerState.currentPage + 1,
                                            animationSpec = pagerTween
                                        )
                                    }
                                }
                            )
                        }
                        2 -> {
                            ResetPasswordScreen(
                                newPasswordInput = forgotPasswordViewModel.newPassword,
                                confirmPasswordInput = forgotPasswordViewModel.passwordConfirm,
                                onNewPasswordInputChange = forgotPasswordViewModel::updateNewPasswordInput,
                                onConfirmPasswordInputChange = forgotPasswordViewModel::updatePasswordConfirmInput,
                                resetPassword = {

                                }
                            )
                        }
                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = MaterialTheme.responsiveLayout.spacingExtraLarge),
                text = buildAnnotatedString {
                    append("Remember the password? ")
                    addStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                        ),
                        start = 0,
                        end = length
                    )
                    val startIndex = length

                    append("Sign in")
                    addStyle(
                        style = SpanStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
                        ),
                        start = startIndex,
                        end = length
                    )
                    val endIndex = length

                    addLink(
                        LinkAnnotation.Clickable(
                            "Sign in",
                            linkInteractionListener = {
                                authNavController.popBackStack(ScreenRoutes.AuthNavGraph.ForgotPasswordNavHostRoute,inclusive = true)
                            }
                        ),
                        start = startIndex,
                        end = endIndex
                    )
                }
            )
        }

        TurnBackButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = { turnBack() }
        )

    }
}
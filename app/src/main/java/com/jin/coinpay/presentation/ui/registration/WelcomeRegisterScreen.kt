package com.jin.coinpay.presentation.ui.registration

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.jin.coinpay.R
import com.jin.coinpay.core.base.theme.BluePrimary
import com.jin.coinpay.core.base.theme.CoinPayTheme
import com.jin.coinpay.core.base.theme.Typography
import com.jin.coinpay.core.base.theme.White
import com.jin.coinpay.core.base.theme.backgroundColor
import com.jin.coinpay.core.base.theme.customColorsPalette
import com.jin.coinpay.core.util.appendLinkAndStyle

@Composable
fun WelcomeRegisterScreen(onClickSignUp: () -> Unit, onClickLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            model = if (isSystemInDarkTheme()) {
                R.drawable.img_register_1_dark
            } else {
                R.drawable.img_register_1
            }, null, contentScale = ContentScale.Inside, modifier = Modifier.weight(1f)
        )

        Text(
            text = stringResource(R.string.txt_create_account),
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 60.dp),
            style = Typography.headlineLarge,
            fontSize = 24.sp,
            color = MaterialTheme.customColorsPalette.contentPrimary,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.txt_des_create_account),
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.customColorsPalette.contentPrimary,
            style = Typography.headlineSmall,
            fontSize = 14.sp
        )

        SignUpButton(onClickSignUp)

        LoginButton(onClickLogin)

        Footer(
            modifier = Modifier
                .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

private const val TERMS_OF_SERVICE_URL = "https://github.com/doanvu2000/CoinPay"
private const val PRIVACY_POLICY_URL = "https://github.com/doanvu2000/CoinPay"

@Composable
private fun Footer(modifier: Modifier) {
    val linkStyle = SpanStyle(
        color = BluePrimary, textDecoration = TextDecoration.Underline
    )

    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(stringResource(R.string.txt_privacy_part_1) + "\n")

            appendLinkAndStyle(
                spanStyle = linkStyle,
                text = stringResource(R.string.txt_term_of_service),
                url = TERMS_OF_SERVICE_URL
            )

            append(" " + stringResource(R.string.txt_and) + " ")

            appendLinkAndStyle(
                spanStyle = linkStyle,
                text = stringResource(R.string.txt_privacy_policy),
                url = PRIVACY_POLICY_URL
            )

//            withLink(LinkAnnotation.Url(PRIVACY_POLICY_URL)) {
//                withStyle(style = linkStyle) {
//                    append(stringResource(R.string.txt_privacy_policy))
//                }
//            }
        },
        color = MaterialTheme.customColorsPalette.contentPrimary,
        style = Typography.headlineSmall,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun LoginButton(onClickLogin: () -> Unit) {
    ElevatedButton(
        onClick = {
            onClickLogin()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(vertical = 16.dp),
        border = BorderStroke(1.dp, BluePrimary)
    ) {
        Text(
            text = stringResource(R.string.txt_log_in),
            style = Typography.headlineMedium,
            color = MaterialTheme.customColorsPalette.contentPrimary
        )
    }
}

@Composable
private fun SignUpButton(onClickSignUp: () -> Unit) {
    ElevatedButton(
        onClick = {
            onClickSignUp()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = BluePrimary),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.txt_sign_up),
            style = Typography.headlineMedium,
            color = White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeRegisterScreenPreviewLight() {
    CoinPayTheme(darkTheme = false) {
        WelcomeRegisterScreen({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeRegisterScreenPreviewDark() {
    CoinPayTheme(darkTheme = true) {
        WelcomeRegisterScreen({}, {})
    }
}
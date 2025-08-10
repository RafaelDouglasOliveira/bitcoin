package br.com.testemercadobitcoin.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.testemercadobitcoin.R
import br.com.testemercadobitcoin.navigation.NavRoutes
import br.com.testemercadobitcoin.utils.Constants.ANIMATION
import br.com.testemercadobitcoin.utils.Constants.DESCRIPTION_ANIMATION
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val animationBitcoin by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.bitcoin)
    )

    val progressBitcoin by animateLottieCompositionAsState(
        composition = animationBitcoin,
        iterations = LottieConstants.IterateForever
    )

    LaunchedEffect(Unit) {
        delay(ANIMATION)
        navController.popBackStack()
        navController.navigate(NavRoutes.LIST_EXCHANGES)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = animationBitcoin,
            progress = { progressBitcoin },
            modifier = Modifier.size(200.dp).semantics { contentDescription = DESCRIPTION_ANIMATION  }

        )
    }
}
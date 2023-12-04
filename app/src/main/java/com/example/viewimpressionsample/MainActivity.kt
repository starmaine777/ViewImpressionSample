package com.example.viewimpressionsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viewimpressionsample.ui.theme.ViewImpressionSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewImpressionSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestGrid()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestGrid(modifier: Modifier = Modifier) {
    val catList = listOf(
        R.drawable.cat1,
        R.drawable.cat2,
        R.drawable.cat3,
        R.drawable.cat4,
        R.drawable.cat5,
        R.drawable.cat6,
        R.drawable.cat7,
        R.drawable.cat8,
        R.drawable.cat9,
        R.drawable.cat10,
        R.drawable.cat11,
        R.drawable.cat12,
        R.drawable.cat13,
        R.drawable.cat14,
        R.drawable.cat15,
        R.drawable.cat16,
        ).apply { random() }

    val spacedArrangement = Arrangement.spacedBy(1.dp)
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier.fillMaxSize(),
        horizontalArrangement = spacedArrangement,
        verticalItemSpacing = 1.dp) {
        items(catList) {
            CatPhotoCell(drawableRes = it)
        }
    }
}

@Composable
fun CatPhotoCell(drawableRes: Int) {
    Image(painter = painterResource(id = drawableRes), contentDescription = "cat",
        contentScale = ContentScale.Crop,
        modifier = Modifier
        .fillMaxWidth()
        .height(100.dp + (100 * Math.random()).dp))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewImpressionSampleTheme {
//        TestGrid("Android")
    }
}

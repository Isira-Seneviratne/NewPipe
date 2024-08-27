package org.schabi.newpipe.ui.components.metadata

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import org.schabi.newpipe.R
import org.schabi.newpipe.ui.theme.AppTheme
import org.schabi.newpipe.util.NavigationHelper

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsSection(serviceId: Int, tags: List<String>) {
    val context = LocalContext.current
    val sortedTags = remember(tags) { tags.sortedWith(String.CASE_INSENSITIVE_ORDER) }

    Column(modifier = Modifier.padding(4.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            text = stringResource(R.string.metadata_tags),
            fontWeight = FontWeight.Bold
        )

        FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (tag in sortedTags) {
                SuggestionChip(
                    onClick = {
                        NavigationHelper.openSearchFragment(
                            (context as FragmentActivity).supportFragmentManager, serviceId, tag
                        )
                    },
                    label = { Text(text = tag) }
                )
            }
        }
    }
}

@Preview(name = "Light mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TagsSectionPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TagsSection(serviceId = 1, tags = listOf("Tag 1", "Tag 2"))
        }
    }
}
